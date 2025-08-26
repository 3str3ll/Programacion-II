package ej3;

public class TestEstadistica {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        double[] numeros = new double[10];
        System.out.println("Ingrese 10 números:");
        for (int i = 0; i < 10; i++) {
            System.out.print("num " + (i + 1) + ": ");
            numeros[i] = scanner.nextDouble();
        }
        Estadistica est= new Estadistica(numeros);
        double prom = est.promedio();
        double dev = est.desviacion();
        System.out.println("El promedio es: " + prom);
        System.out.println("La desviación estándar es: " + dev);
    }
}

