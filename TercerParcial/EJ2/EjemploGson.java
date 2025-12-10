package Persistencia.class1.ej1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class EjemploGson {
    public static void main(String[] args) {

        Consulta consulta = new Consulta(9999, "Ejemplo", "Gson", 1, 25, "Diciembre", 2024);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(consulta);
        try (FileWriter writer = new FileWriter("consulta_ejemplo.json")) {
            gson.toJson(consulta, writer);
            System.out.println("\nArchivo  creado exitosamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Consulta nuevaConsulta = gson.fromJson(json, Consulta.class);
        System.out.println("JSON convertido de vuelta a objeto:");
        System.out.println(nuevaConsulta);
    }
}