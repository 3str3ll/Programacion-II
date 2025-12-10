package TercerParcial.EJ2;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class LeeArchivoJson {
    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("consulta_ejemplo.json"));
            Consulta consulta = gson.fromJson(reader, Consulta.class);

            System.out.println("=== LECTURA DE ARCHIVO JSON ===");
            System.out.println("Paciente: " + consulta.getNombrePaciente() + " " + consulta.getApellidoPaciente());
            System.out.println("Fecha: " + consulta.getDia() + "/" + consulta.getMes() + "/" + consulta.getAnio());
            System.out.println("Médico ID: " + consulta.getIdMed());

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}













/*import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
public class LeeArchivoJson {
    public static void main(String[]args){
        try {
            Gson gson=new Gson();
            Reader reader=Files.newBufferedReader(Paths.get("libro.json"));
            Libro libro=gson.fromJson(reader,Libro.class);
            System.out.println(libro.titulo +" " + libro.autor);
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}*/

