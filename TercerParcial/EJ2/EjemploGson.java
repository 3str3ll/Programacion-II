package Persistencia.class1.ej1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
class EjemploGson {
        public static void main(String[] args) {
            System.out.println("SISTEMA MÉDICO - CONSULTORIO \n");

            Consultorio consultorio = new Consultorio("Consultorio Central", "Lunes a Viernes", "08:00", "18:00");

            consultorio.cargarDatos();

            // Si no hay datos cargados, crear datos iniciales según el ejercicio
            if (consultorio.getMedicos().isEmpty()) {
                System.out.println("\nCreando datos iniciales para el ejercicio...");

                // a) Dar de alta al menos 3 Médicos
                Medico medico1 = new Medico(1, "Ana", "García", 12);
                Medico medico2 = new Medico(2, "Luis", "Martínez", 8);
                Medico medico3 = new Medico(3, "Carmen", "Rodríguez", 20);

                consultorio.agregarMedico(medico1);
                consultorio.agregarMedico(medico2);
                consultorio.agregarMedico(medico3);

                // a) Dar de alta 9 Consultas
                Consulta[] consultas = {
                        new Consulta(1001, "Juan", "Pérez", 1, 24, "Diciembre", 2024),
                        new Consulta(1002, "María", "López", 1, 15, "Enero", 2025),
                        new Consulta(1003, "Carlos", "Sánchez", 1, 25, "Diciembre", 2024), // Navidad
                        new Consulta(1004, "Sofía", "Fernández", 2, 1, "Enero", 2025), // Año nuevo
                        new Consulta(1005, "Pedro", "Gómez", 2, 10, "Febrero", 2025),
                        new Consulta(1006, "Laura", "Díaz", 2, 31, "Diciembre", 2024),
                        new Consulta(1007, "Miguel", "Ruiz", 3, 25, "Diciembre", 2024), // Navidad
                        new Consulta(1008, "Elena", "Torres", 3, 1, "Enero", 2025), // Año nuevo
                        new Consulta(1009, "Jorge", "Hernández", 3, 14, "Marzo", 2025)
                };

                for (Consulta consulta : consultas) {
                    consultorio.agregarConsulta(consulta);
                }

                // Agregar algunos pacientes
                consultorio.agregarPaciente(new Paciente("1234567", "Juan", "Pérez", 35, "70012345"));
                consultorio.agregarPaciente(new Paciente("7654321", "María", "López", 28, "60098765"));
                consultorio.agregarPaciente(new Paciente("9876543", "Carlos", "Sánchez", 42, "77755544"));
            }

            // Mostrar estado inicial
            System.out.println("\n=== ESTADO INICIAL ===");
            consultorio.mostrarEstado();

            // EJERCICIO B: Dar de baja al médico de nombreX y apellidoY; y sus consultas
            System.out.println("\n=== EJERCICIO B ===");
            consultorio.darBajaMedico("Luis", "Martínez");

            // EJERCICIO C: Cambia el día de la consulta de los pacientes que agendaron en navidad o año nuevo
            System.out.println("\n=== EJERCICIO C ===");
            consultorio.cambiarConsultasFestivas();

            // EJERCICIO D (OPCIONAL): Muestra a los pacientes atendidos en el día de tu cumpleaños
            System.out.println("\n=== EJERCICIO D (OPCIONAL) ===");
            consultorio.mostrarPacientesEnCumpleaños(14, "Marzo"); // Ejemplo: 14 de Marzo

            // Mostrar estado final
            System.out.println("\n=== ESTADO FINAL ===");
            consultorio.mostrarEstado();

            // Exportar datos individuales
            exportarDatosIndividuales(consultorio);

            // Guardar copia de seguridad
            consultorio.guardarCopiaSeguridad("consultorio_backup_" + System.currentTimeMillis() + ".json");

            consultorio.cerrarConsultorio();
        }

        // Método para exportar datos a archivos JSON individuales
        private static void exportarDatosIndividuales(Consultorio consultorio) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            System.out.println("\n=== EXPORTANDO DATOS INDIVIDUALES ===");

            // Exportar médicos
            for (Medico medico : consultorio.getMedicos()) {
                String nombreArchivo = "medico_" + medico.getIdMed() + ".json";
                try (FileWriter writer = new FileWriter(nombreArchivo)) {
                    gson.toJson(medico, writer);
                    System.out.println("✓ Médico exportado: " + nombreArchivo);
                } catch (IOException e) {
                    System.out.println("✗ Error al exportar médico: " + e.getMessage());
                }
            }

            // Exportar consultas
            for (Consulta consulta : consultorio.getConsultas()) {
                String nombreArchivo = "consulta_" + consulta.getCi() + ".json";
                try (FileWriter writer = new FileWriter(nombreArchivo)) {
                    gson.toJson(consulta, writer);
                    System.out.println("✓ Consulta exportada: " + nombreArchivo);
                } catch (IOException e) {
                    System.out.println("✗ Error al exportar consulta: " + e.getMessage());
                }
            }

            // Exportar pacientes
            for (Paciente paciente : consultorio.getPacientes()) {
                String nombreArchivo = "paciente_" + paciente.getCi() + ".json";
                try (FileWriter writer = new FileWriter(nombreArchivo)) {
                    gson.toJson(paciente, writer);
                    System.out.println("✓ Paciente exportado: " + nombreArchivo);
                } catch (IOException e) {
                    System.out.println("✗ Error al exportar paciente: " + e.getMessage());
                }
            }
        }
    }











}


/*public static void main(String[]args){
    try {
        Gson gson =new Gson();
        FileWriter writer =new FileWriter("libro.json");

        Libro libro=new Libro("CIEN AÑOS DE SOLEDAD","GABRIEL GARCIA MARQUEZ");

        String cadena= gson.toJson(libro);
        writer.write(cadena);
        writer.close();
    }catch (IOException e){
        e.printStackTrace();
    }
}

 */
    /*
    public String nombre;
    public String direccion;
    public Libro(String nombre, String direccion){
        this.nombre=nombre;
        this.direccion=direccion;
    }

    @Override
    public String toString(){
        return"[nombre= "  + nombre + " , direccion= " + direccion +"]";
    }
    }

public class EjemploGson {
    public static void main(String[]args) {
        try {
            Gson gson = new Gson();
    /*Persona persona1=new Persona("Juan","aaa");
    String cadena=gson.toJson(persona1);
    FileWriter archivo=new FileWriter("persona.json");
    archivo.write(cadena);
    archivo.close();*/


            /*Reader archivo2 = Files.newBufferedReader(
                    Paths.get("persona.json"));
            Libro persona2 = gson.fromJson(archivo2, Libro.class);
            System.out.println(persona2);
            archivo2.close();

        } catch(IOException e){
            e.printStackTrace();

        }
    }}
*/