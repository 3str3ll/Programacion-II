package ej1;
import java.util.Scanner;

public class ModularLineal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese a, b, c, d, e, f: ");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();
        double d = sc.nextDouble();
        double e = sc.nextDouble();
        double f = sc.nextDouble();

        if (tieneSolucion(a, b, c, d)) {
            double x = getX(a, b, c, d, e, f);
            double y = getY(a, b, c, d, e, f);
            System.out.println("x = " + x + ", y = " + y);
        } else {
            System.out.println("La ecuación no tiene solución");
        }
    }

    private static boolean tieneSolucion(double a, double b, double c, double d) {
        return (a * d - b * c) != 0;
    }

    private static double getX(double a, double b, double c, double d, double e, double f) {
        return (e * d - b * f) / (a * d - b * c);
    }

    private static double getY(double a, double b, double c, double d, double e, double f) {
        return (a * f - e * c) / (a * d - b * c);
    }
}