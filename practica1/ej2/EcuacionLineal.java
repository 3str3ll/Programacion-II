package ej2;
public class EcuacionLineal {
    private double a;
    private double b;
    private double c;
    public EcuacionLineal(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public double getDiscriminante() {
        return this.b * this.b - 4 * this.a * this.c;
    }
    public double getRaiz1() {
        double discriminante = getDiscriminante();
        if (discriminante >= 0) {
            return (-this.b + Math.sqrt(discriminante)) / (2 * this.a);
        } else {
            return 0;
        }
    }
    public double getRaiz2() {
        double discriminante = getDiscriminante();
        if (discriminante >= 0) {
            return (-this.b - Math.sqrt(discriminante)) / (2 * this.a);
        } else {
            return 0;
        }
    }
}