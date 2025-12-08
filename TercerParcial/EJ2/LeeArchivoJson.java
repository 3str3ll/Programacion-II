package TercerParcial.EJ2;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LeeArchivoJson {
        private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        public static void guardarObjeto(String archivo, Object objeto) {
            try (FileWriter writer = new FileWriter(archivo)) {
                gson.toJson(objeto, writer);
                System.out.println("✓ Objeto guardado en " + archivo);
            } catch (IOException e) {
                System.out.println("✗ Error al guardar: " + e.getMessage());
            }
        }

        public static <T> T cargarObjeto(String archivo, Class<T> clase) {
            try {
                Reader reader = Files.newBufferedReader(Paths.get(archivo));
                T objeto = gson.fromJson(reader, clase);
                reader.close();
                System.out.println("✓ Objeto cargado desde " + archivo);
                return objeto;
            } catch (IOException e) {
                System.out.println("✗ Error al cargar: " + e.getMessage());
                return null;
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

