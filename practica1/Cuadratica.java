public class Cuadratica {
    private double a;
    private double b;
    private double c;
    public Cuadratica(double a, double b, double c) {
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
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Ingrese a, b, c: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        Cuadratica ecuacion = new Cuadratica(a, b, c);
        double discriminante = ecuacion.getDiscriminante();

        if (discriminante > 0) {
            System.out.println("La ecuación tiene dos raíces:");
            System.out.println("r1 = " + ecuacion.getRaiz1());
            System.out.println("r2 = " + ecuacion.getRaiz2());
        } else if (discriminante == 0) {
            System.out.println("La ecuación tiene una raíz:");
            System.out.println("r = " + ecuacion.getRaiz1());
        } else {
            System.out.println("La ecuación no tiene raíces reales");
        }
    }
}