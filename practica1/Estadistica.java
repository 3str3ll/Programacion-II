public class Estadistica {
    private double[] numeros;

    public Estadistica(double[] numeros) {
        this.numeros = numeros;
    }
    public double promedio() {
        double sum = 0;
        for (double numero : this.numeros) {
            sum += numero;
        }
        return sum / this.numeros.length;
    }
    public double desviacion() {
        double prom = promedio();
        double sumCuadrados = 0;
        for (double numero : this.numeros) {
            sumCuadrados += (numero - prom) * (numero - prom);
        }
        return Math.sqrt(sumCuadrados / (this.numeros.length - 1));
    }
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