package practica4.ej2;

public abstract class Figura {
    protected String color;

    public Figura(String color) {
        this.color = color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Figura [color=" + color + ", tipo=" + getClass().getSimpleName() + "]";
    }
    public abstract double area();
    public abstract double perimetro();
}