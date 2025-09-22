package practica3.ej1;

import java.util.Random;
import java.util.Scanner;

public class JuegoAdivinaNumero extends Juego {
    protected int numeroAdivinar;
    protected int vidasIniciales;

    public JuegoAdivinaNumero(int numeroDeVidas) {
        super();
        this.vidasIniciales = numeroDeVidas;
        setNumeroDeVidas(numeroDeVidas);
    }

    @Override
    public void reiniciaPartida() {
        setNumeroDeVidas(vidasIniciales);
    }

    public boolean validaNumero(int numero) {
        return numero >= 0 && numero <= 10;
    }

    public void juega() {
        reiniciaPartida();
        Random random = new Random();
        numeroAdivinar = random.nextInt(11);

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n=== JUEGO ADIVINA NÚMERO ===");
        System.out.println("Adivina un número entre 0 y 10");
        System.out.println("Tienes " + getNumeroDeVidas() + " vidas");

        while (getNumeroDeVidas() > 0) {
            System.out.print("Introduce tu número: ");
            int numero = scanner.nextInt();

            if (!validaNumero(numero)) {
                System.out.println("Error: El número debe estar entre 0 y 10");
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