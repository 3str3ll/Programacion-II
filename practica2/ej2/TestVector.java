package practica2.ej2;

public class TestVector {
    public static void main(String[] args) {
            Vector a = new Vector(1, 2, 3);
            Vector b = new Vector(4, 5, 6);
            Vector c = new Vector(0, 0, 1);
            Vector d = new Vector(1, 0, 0);
            Vector e = new Vector(2, 4, 6);

            System.out.println("VECTORES DE PRUEBA" );
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            System.out.println("c = " + c);
            System.out.println("d = " + d);
            System.out.println("e = " + e);

            System.out.println("\nOPERACIONES BÁSICAS ");
            System.out.println("a + b = " + a.suma(b));
            System.out.println("a - b = " + a.resta(b));
            System.out.println("3 * a = " + a.multEscalar(3));
            System.out.println("Longitud de a: " + String.format("%.2f", a.longitud()));
            System.out.println("Pract2.Vector normalizado de a: " + a.normalizar());
            System.out.println("a · b = " + String.format("%.2f", a.productoPunto(b)));
            System.out.println("a × b = " + a.productoCruz(b));

            System.out.println("\n PROPIEDADES GEOMÉTRICAS ");
            System.out.println("¿a es perpendicular a b? " + a.perpendicular(b));
            System.out.println("¿c es perpendicular a d? " + c.perpendicular(d));
            System.out.println("¿a es paralelo a e? " + a.paralelo(e));
            System.out.println("¿a es paralelo a b? " + a.paralelo(b));

            System.out.println("\n PROYECCIÓN Y COMPONENTE ");
            Vector proyeccion = a.proyeccion(b);
            System.out.println("Proyección de a sobre b: " + proyeccion);
            System.out.println("Componente de a en b: " + String.format("%.2f", a.componente(b)));
            System.out.println("\n VERIFICACIÓN TEOREMA PITÁGORAS ");
            System.out.println("|c|² + |d|² = " + String.format("%.2f", Math.pow(c.longitud(), 2) + Math.pow(d.longitud(), 2)));
            System.out.println("|c + d|² = " + String.format("%.2f", Math.pow(c.suma(d).longitud(), 2)));
            System.out.println("¿Se cumple |c + d|² = |c|² + |d|²? " +
                    (Math.abs(Math.pow(c.suma(d).longitud(), 2) - (Math.pow(c.longitud(), 2) + Math.pow(d.longitud(), 2))) < 1e-10));
        }
      
}
