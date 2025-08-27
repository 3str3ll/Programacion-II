package ej1;

public class EcuacionLineal {
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    public EcuacionLineal(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public boolean tieneSolucion() {
        return (this.a * this.d - this.b * this.c) != 0;
    }

    public double getX() {
        return (this.e * this.d - this.b * this.f) / (this.a * this.d - this.b * this.c);
    }

    public double getY() {
        return (this.a * this.f - this.e * this.c) / (this.a * this.d - this.b * this.c);
    }


}