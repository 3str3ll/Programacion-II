package practica4.ej1;

import java.util.Scanner;

public class Aplicacion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Empleado[] empleados = new Empleado[5];
        System.out.println(" REGISTRO DE EMPLEADOS ");
        System.out.println("\nEMPLEADOS DE TIEMPO COMPLETO ");
        for (int i = 0; i < 3; i++) {
            System.out.println("Empleado de Tiempo Completo #" + (i + 1));
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Salario anual: $");
            double salarioAnual = scanner.nextDouble();
            scanner.nextLine();
            empleados[i] = new EmpleadoTiempoCompleto(nombre, salarioAnual);
        }
        System.out.println("\nEMPLEADOS DE TIEMPO HORARIO ");
        for (int i = 3; i < 5; i++) {
            System.out.println("Empleado de Tiempo Horario #" + (i - 2));
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Horas trabajadas: ");
            double horas = scanner.nextDouble();
            System.out.print("Tarifa por hora: $");
            double tarifa = scanner.nextDouble();
            scanner.nextLine(); // Limpiar buffer

            empleados[i] = new EmpleadoTiempoHorario(nombre, horas, tarifa);
        }
        System.out.println("\n INFORMACIÓN DE EMPLEADOS ");
       for (int i = 0; i < empleados.length; i++) {
            Empleado emp = empleados[i];
            System.out.println("\nEmpleado #" + (i + 1) + ":");
            System.out.println(emp.toString());
            System.out.printf("Salario mensual: $%.2f%n", emp.calcularSalarioMensual());
            System.out.println("Tipo: " + emp.getClass().getSimpleName());
        }
    }
}