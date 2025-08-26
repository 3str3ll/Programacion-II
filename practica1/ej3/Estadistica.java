package ej3;

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

}
