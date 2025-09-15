package practica2.ej1;

public class TestVectorial {
    public static void main(String[] args) {
        AlgebraVectorial v1 = new AlgebraVectorial(1, 2, 3);
        AlgebraVectorial v2 = new AlgebraVectorial(4, 5, 6);
        AlgebraVectorial v3 = new AlgebraVectorial(0, 0, 1); 
        AlgebraVectorial v4 = new AlgebraVectorial(1, 0, 0); 
        AlgebraVectorial v5 = new AlgebraVectorial(2, 4, 6); 

        System.out.println("Vectores de prueba");
        System.out.println("v1 = (" + v1.getX() + ", " + v1.getY() + ", " + v1.getZ() + ")");
        System.out.println("v2 = (" + v2.getX() + ", " + v2.getY() + ", " + v2.getZ() + ")");
        System.out.println("v3 = (" + v3.getX() + ", " + v3.getY() + ", " + v3.getZ() + ")");
        System.out.println("v4 = (" + v4.getX() + ", " + v4.getY() + ", " + v4.getZ() + ")");
        System.out.println("v5 = (" + v5.getX() + ", " + v5.getY() + ", " + v5.getZ() + ")");
        System.out.println("\n-Perpendicularidad-");
        System.out.println("¿v3 es perpendicular a v4 (método a)? " + v3.perpendicular(v4));
        System.out.println("¿v3 es perpendicular a v4 (método b)? " + v3.perpendicular(v4, 2));
        System.out.println("¿v3 es perpendicular a v4 (método c)? " + v3.perpendicular(v4, 3));
        System.out.println("¿v3 es perpendicular a v4 (método d)? " + v3.perpendicular(v4, 4));
        System.out.println("\n-Paralelismo-");
        System.out.println("¿v1 es paralelo a v5 (método e)? " + v1.paralela(v5));
        System.out.println("¿v1 es paralelo a v5 (método f)? " + v1.paralela(v5, 2));
        System.out.println("\n-Proyección y Componente-");
        AlgebraVectorial proyeccion = v1.proyeccion(v2);
        System.out.println("Proyección de v1 sobre v2: (" +
                proyeccion.getX() + ", " + proyeccion.getY() + ", " + proyeccion.getZ() + ")");
        double componente = v1.componente(v2);
        System.out.println("Componente de v1 en v2: " + componente);
    }
}
