package practica2.ej2;
public class Vector {
    private double x;
    private double y;
    private double z;
    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
     public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getZ() {
        return this.z;
    }
    public Vector suma(Vector b) {
        return new Vector(this.x + b.getX(), this.y + b.getY(), this.z + b.getZ());
    }
    public Vector multEscalar(double r) {
        return new Vector(this.x * r, this.y * r, this.z * r);
    }
    public double longitud() {
        return Math.sqrt(x*x + y*y + z*z);
    }
    public Vector normalizar() {
        double magnitud = this.longitud();
        if (magnitud > 0) {
            return new Vector(this.x / magnitud, this.y / magnitud, this.z / magnitud);
        }
        return new Vector(0, 0, 0);
    }
    public double productoPunto(Vector b) {
        return this.x * b.getX() + this.y * b.getY() + this.z * b.getZ();
    }
    public Vector productoCruz(Vector b) {
        double cx = this.y * b.getZ() - this.z * b.getY();
        double cy = this.z * b.getX() - this.x * b.getZ();
        double cz = this.x * b.getY() - this.y * b.getX();
        return new Vector(cx, cy, cz);
    }
    public Vector resta(Vector b) {
        return new Vector(this.x - b.getX(), this.y - b.getY(), this.z - b.getZ());
    }
    public boolean perpendicular(Vector b) {
        Vector suma = this.suma(b);
        Vector resta = this.resta(b);
        return Math.abs(suma.longitud() - resta.longitud()) < 1e-10;
    }
    public boolean paralelo(Vector b) {
        Vector cruz = this.productoCruz(b);
        return cruz.longitud() < 1e-10;
    }
    public Vector proyeccion(Vector b) {
        double factor = this.productoPunto(b) / Math.pow(b.longitud(), 2);
        return b.multEscalar(factor);
    }
    public double componente(Vector b) {
        return this.productoPunto(b) / b.longitud();
    }
    public String toString() {
        return "(" + String.format("%.2f", x) + ", " + String.format("%.2f", y) + ", " + String.format("%.2f", z) + ")";
    }
}