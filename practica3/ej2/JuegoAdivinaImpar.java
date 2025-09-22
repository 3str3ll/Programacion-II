package practica3.ej2;

import java.util.Random;
import java.util.Scanner;

public class JuegoAdivinaImpar extends JuegoAdivinaNumero {

    public JuegoAdivinaImpar(int numeroDeVidas) {
        super(numeroDeVidas);
    }

    @Override
    public boolean validaNumero(int numero) {
        if (numero < 0 || numero > 10) {
            System.out.println("Error: El número debe estar entre 0 y 10");
            return false;
        }

        if (numero % 2 != 0) {
            return true;
        } else {
            System.out.println("Error: Debes introducir un número IMPAR");
            return false;
        }
    }

    @Override
    public void juega() {
        reiniciaPartida();
        Random random = new Random();
        // Aseguramos que el número a adivinar sea impar
        numeroAdivinar = random.nextInt(5) * 2 + 1; // 1, 3, 5, 7, 9

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== JUEGO ADIVINA IMPAR ===");
        System.out.println("Adivina un número IMPAR entre 0 y 10");
        System.out.println("Tienes " + getNumeroDeVidas() + " vidas");

        while (getNumeroDeVidas() > 0) {
            System.out.print("Introduce tu número IMPAR: ");
            int numero = scanner.nextInt();

            if (!validaNumero(numero)) {
                continue;
            }

            if (numero == numeroAdivinar) {
                System.out.println("¡Acertaste!");
                actualizaRecord();
                break;
            } else {
                System.out.println("Fallaste...");
                boolean quedanVidas = quitaVida();

                if (!quedanVidas) {
                    System.out.println("¡Perdiste! El número era: " + numeroAdivinar);
                    break;
                } else {
                    System.out.println("Te quedan " + getNumeroDeVidas() + " vidas");
                    if (numero < numeroAdivinar) {
                        System.out.println("El número a adivinar es MAYOR");
                    } else {
                        System.out.println("El número a adivinar es MENOR");
                    }
                    System.out.println("Intenta de nuevo:");
                }
            }
        }
    }
}
