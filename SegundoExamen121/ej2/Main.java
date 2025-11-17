
package SegundoExamen121.ej2;
public class Main {
    public static void main(String[] args) {
        MiTeleferico teleferico = new MiTeleferico();

        Persona p1 = new Persona("Juan", 30, 70);
        Persona p2 = new Persona("Maria", 20, 55);
        Persona p3 = new Persona("Carlos", 65, 80);

        teleferico.agregarPersonaFila(p1, "Amarillo");
        teleferico.agregarPersonaFila(p2, "Amarillo");
        teleferico.agregarPersonaFila(p3, "Rojo");

        teleferico.agregarCabina("Amarillo");
        teleferico.agregarCabina("Amarillo");
        teleferico.agregarCabina("Rojo");
        teleferico.agregarCabina("Verde");

        // a)
        teleferico.agregarPrimeraPersonaACabina(1, "Amarillo");
        teleferico.agregarPrimeraPersonaACabina(1, "Rojo");

        // b)
        System.out.println("Todas las cabinas cumplen reglas: " +
                teleferico.verificarReglasTodasLineas());

        // c)
        System.out.println("Ingreso total: Bs. " + teleferico.calcularIngresoTotal());

        // d)
        teleferico.mostrarLineaConMasIngresoRegular();
    }
}

