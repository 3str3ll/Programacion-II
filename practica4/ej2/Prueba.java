package practica4.ej2;

import java.util.Random;

public class Prueba {
    public static void main(String[] args) {
        Random random = new Random();
        Figura[] figuras = new Figura[5];

        String[] colores = {"Rojo", "Azul", "Verde", "Amarillo", "Negro", "Blanco", "Morado"};

        System.out.println("GENERANDO FIGURAS ALEATORIAS \n");
        for (int i = 0; i < figuras.length; i++) {
            int tipoFigura = random.nextInt(2) + 1;
            String color = colores[random.nextInt(colores.length)];

            if (tipoFigura == 1) {
                double lado = 1 + random.nextDouble() * 9;
                figuras[i] = new Cuadrado(color, lado);
                System.out.println("Creado Cuadrado #" + (i+1) + " - Color: " + color + ", Lado: " + String.format("%.2f", lado));
            } else {
                double radio = 1 + random.nextDouble() * 9;
                figuras[i] = new Circulo(color, radio);
                System.out.println("Creado Círculo #" + (i+1) + " - Color: " + color + ", Radio: " + String.format("%.2f", radio));
            }
        }

        System.out.println("\n INFORMACIÓN DE FIGURAS ");

        for (int i = 0; i < figuras.length; i++) {
            Figura figura = figuras[i];

            System.out.println("Figura #" + (i+1) + ":");
            System.out.println("Tipo: " + figura.getClass().getSimpleName());
            System.out.println("Color: " + figura.getColor());

            if (figura instanceof Cuadrado) {
                Cuadrado cuadrado = (Cuadrado) figura;
                System.out.println("Lado: " + String.format("%.2f", cuadrado.getLado()));
            } else if (figura instanceof Circulo) {
                Circulo circulo = (Circulo) figura;
                System.out.println("Radio: " + String.format("%.2f", circulo.getRadio()));
            }

            System.out.printf("Área: %.2f%n", figura.area());
            System.out.printf("Perímetro: %.2f%n", figura.perimetro());
            if (figura instanceof Coloreado) {
                Coloreado coloreado = (Coloreado) figura;
                System.out.println("Método colorear: " + coloreado.comoColorear());
            } else {
                System.out.println("Método colorear: No implementado");
            }

        }


    }
}
