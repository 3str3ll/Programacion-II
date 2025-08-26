import java.util.Scanner;

public class ModularEstadistica {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] numeros = new double[10];

        System.out.println("Ingrese 10 números:");
        for (int i = 0; i < 10; i++) {
            System.out.print("num " + (i + 1) + ": ");
            numeros[i] = sc.nextDouble();
        }

        double promedio = calcularPromedio(numeros);
        double desviacion = calcularDesviacion(numeros, promedio);

        System.out.println("El promedio es: " + promedio);
        System.out.println("La desviación estándar es: " + desviacion);
    }

    private static double calcularPromedio(double[] numeros) {
        double sum = 0;
        for (double numero : numeros) {
            sum += numero;
        }
        return sum / numeros.length;
    }

    private static double calcularDesviacion(double[] numeros, double promedio) {
        double sumaCuadrados = 0;
        for (double numero : numeros) {
            sumaCuadrados += (numero - promedio) * (numero - promedio);
        }
        return Math.sqrt(sumaCuadrados / (numeros.length - 1));
    }
}
