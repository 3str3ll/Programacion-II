package practica2.ej1;

public class AlgebraVectorial {
    private double x;
    private double y;
    private double z;
    public AlgebraVectorial(double x, double y, double z) {
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
    public double magnitud() {
        return Math.sqrt(x*x + y*y + z*z);
    }
    public double productoPunto(AlgebraVectorial b) {
        return this.x * b.getX() + this.y * b.getY() + this.z * b.getZ();
    }
    public AlgebraVectorial productoCruz(AlgebraVectorial b) {
        double cx = this.y * b.getZ() - this.z * b.getY();
        double cy = this.z * b.getX() - this.x * b.getZ();
        double cz = this.x * b.getY() - this.y * b.getX();
        return new AlgebraVectorial(cx, cy, cz);
    }
    public AlgebraVectorial suma(AlgebraVectorial b) {
        return new AlgebraVectorial(this.x + b.getX(), this.y + b.getY(), this.z + b.getZ());
    }
    public AlgebraVectorial resta(AlgebraVectorial b) {
        return new AlgebraVectorial(this.x - b.getX(), this.y - b.getY(), this.z - b.getZ());
    }
    public AlgebraVectorial multiplicarEscalar(double r) {
        return new AlgebraVectorial(this.x * r, this.y * r, this.z * r);
    }
    public boolean perpendicular(AlgebraVectorial b) {
        AlgebraVectorial suma = this.suma(b);
        AlgebraVectorial resta = this.resta(b);
        return Math.abs(suma.magnitud() - resta.magnitud()) < 1e-10;
    }

    public boolean perpendicular(AlgebraVectorial b, int metodo) {
        if (metodo == 2) {
            AlgebraVectorial restaAB = this.resta(b);
            AlgebraVectorial restaBA = b.resta(this);
            return Math.abs(restaAB.magnitud() - restaBA.magnitud()) < 1e-10;
        } else if (metodo == 3) {
            return Math.abs(this.productoPunto(b)) < 1e-10;
        } else if (metodo == 4) {
            AlgebraVectorial suma = this.suma(b);
            double ladoIzq = Math.pow(suma.magnitud(), 2);
            double ladoDer = Math.pow(this.magnitud(), 2) + Math.pow(b.magnitud(), 2);
            return Math.abs(ladoIzq - ladoDer) < 1e-10;
        }
        return false;
    }
    public boolean paralela(AlgebraVectorial b) {
        if (Math.abs(b.getX()) > 1e-10 && Math.abs(b.getY()) > 1e-10 && Math.abs(b.getZ()) > 1e-10) {
            double r = this.x / b.getX();
            return Math.abs(this.y - r * b.getY()) < 1e-10 && Math.abs(this.z - r * b.getZ()) < 1e-10;
        }
        return false;
    }
    public boolean paralela(AlgebraVectorial b, int metodo) {
        AlgebraVectorial cruz = this.productoCruz(b);
        return cruz.magnitud() < 1e-10;
    }
    public AlgebraVectorial proyeccion(AlgebraVectorial b) {
        double factor = this.productoPunto(b) / Math.pow(b.magnitud(), 2);
        return b.multiplicarEscalar(factor);
    }
    public double componente(AlgebraVectorial b) {
        return this.productoPunto(b) / b.magnitud();
    }
}
