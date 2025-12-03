package practica6;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GestorJSON {
    private static final String ARCHIVO_BIBLIOTECA = "biblioteca.json";
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public static void guardarBiblioteca(Biblioteca biblioteca) {
        try (FileWriter writer = new FileWriter(ARCHIVO_BIBLIOTECA)) {
            gson.toJson(biblioteca, writer);
            System.out.println(" Biblioteca guardada en: " + ARCHIVO_BIBLIOTECA);
        } catch (IOException e) {
            System.out.println(" Error al guardar: " + e.getMessage());
        }
    }

    public static Biblioteca cargarBiblioteca() {
        File archivo = new File(ARCHIVO_BIBLIOTECA);
        if (!archivo.exists()) {
            System.out.println(" No existe archivo, creando nueva biblioteca...");
            return new Biblioteca("Biblioteca Central", "L-V", "08:00", "20:00");
        }
        
        try (Reader reader = new FileReader(ARCHIVO_BIBLIOTECA)) {
            Type tipoBiblioteca = new TypeToken<Biblioteca>(){}.getType();
            Biblioteca biblioteca = gson.fromJson(reader, tipoBiblioteca);
            System.out.println(" Biblioteca cargada desde: " + ARCHIVO_BIBLIOTECA);
            return biblioteca;
        } catch (IOException e) {
            System.out.println(" Error al cargar: " + e.getMessage());
            return new Biblioteca("Biblioteca Central", "L-V", "08:00", "20:00");
        }
    }

    public static void exportarObjeto(Object objeto, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            gson.toJson(objeto, writer);
            System.out.println(" Objeto exportado a: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }
    public static void crearBackupAutomatico(Biblioteca biblioteca) {
        String backupDir = "backups/";
        File directorio = new File(backupDir);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        
        String nombreBackup = backupDir + "backup_auto_" + 
                System.currentTimeMillis() + ".json";
        exportarObjeto(biblioteca, nombreBackup);
    }
}
