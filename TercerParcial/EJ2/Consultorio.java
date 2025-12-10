package Persistencia.class1.ej1;
import java.io.File;
import java.util.ArrayList;
public class Consultorio {
    private String consultas;  
    private String medicos;    

    public Consultorio(String consultas, String medicos) {
        this.consultas = consultas;
        this.medicos = medicos;
        
        new File(consultas).mkdirs();
        new File(medicos).mkdirs();
    }
    public void darAlta() {

        Medico m1 = new Medico(1, "Ana", "García", 12);
        Medico m2 = new Medico(2, "Luis", "Martínez", 8);
        Medico m3 = new Medico(3, "Carmen", "Rodríguez", 20);

        m1.alta(medicos);
        m2.alta(medicos);
        m3.alta(medicos);

        int ci = 1001;
        new Consulta(ci++, "Juan", "Pérez", 1, 24, "Diciembre", 2024).alta(consultas);
        new Consulta(ci++, "María", "López", 1, 15, "Enero", 2025).alta(consultas);
        new Consulta(ci++, "Carlos", "Sánchez", 1, 25, "Diciembre", 2024).alta(consultas);
        new Consulta(ci++, "Sofía", "Fernández", 2, 1, "Enero", 2025).alta(consultas);
        new Consulta(ci++, "Pedro", "Gómez", 2, 10, "Febrero", 2025).alta(consultas);
        new Consulta(ci++, "Laura", "Díaz", 2, 31, "Diciembre", 2024).alta(consultas);
        new Consulta(ci++, "Miguel", "Ruiz", 3, 25, "Diciembre", 2024).alta(consultas);
        new Consulta(ci++, "Elena", "Torres", 3, 1, "Enero", 2025).alta(consultas);
        new Consulta(ci++, "Jorge", "Hernández", 3, 14, "Marzo", 2025).alta(consultas);
    }
    public void darBaja(String nombreX, String apellidoY) {
        System.out.println("\n=== b) DAR DE BAJA MÉDICO: " + nombreX + " " + apellidoY + " ===");

        ArrayList<Medico> listaMedicos = cargarMedicos();
        int idBuscado = -1;

        for (Medico m : listaMedicos) {
            if (m.getNombreMed().equals(nombreX) && m.getApellidoMed().equals(apellidoY)) {
                idBuscado = m.getIdMed();
                m.baja(medicos);
                System.out.println("Médico eliminado: " + m);
                break;
            }
        }
        if (idBuscado != -1) {
            ArrayList<Consulta> listaConsultas = cargarConsultas();
            for (Consulta c : listaConsultas) {
                if (c.getIdMed() == idBuscado) {
                    c.baja(consultas);
                }
            }
            System.out.println("Consultas del médico también eliminadas");
        } else {
            System.out.println("Médico no encontrado");
        }
    }
    public void cambioConsulta() {

        ArrayList<Consulta> listaConsultas = cargarConsultas();

        for (Consulta c : listaConsultas) {
            boolean esNavidad = c.getDia() == 25 && c.getMes().equals("Diciembre");
            boolean esAnoNuevo = c.getDia() == 1 && c.getMes().equals("Enero");

            if (esNavidad || esAnoNuevo) {
                int ciOriginal = c.getCi();
                int diaOriginal = c.getDia();
                c.setDia(c.getDia() + 1);

                String rutaVieja = consultas + "/consulta_" + ciOriginal + ".json";
                new File(rutaVieja).delete();

                c.alta(consultas);

                System.out.println("Consulta #" + ciOriginal + " reprogramada: " +
                        diaOriginal + " → " + c.getDia());
            }
        }
    }
    public void ejercicioD(int diaCumple, String mesCumple) {
        System.out.println("\n=== d) PACIENTES EN CUMPLEAÑOS (" + diaCumple + " " + mesCumple + ") ===");

        ArrayList<Consulta> listaConsultas = cargarConsultas();
        boolean encontrados = false;

        for (Consulta c : listaConsultas) {
            if (c.getDia() == diaCumple && c.getMes().equals(mesCumple)) {
                System.out.println("• " + c.getNombrePaciente() + " " + c.getApellidoPaciente());
                encontrados = true;
            }
        }

        if (!encontrados) {
            System.out.println("No hay consultas en esa fecha");
        }
    }
    private ArrayList<Medico> cargarMedicos() {
        ArrayList<Medico> lista = new ArrayList<>();
        File carpeta = new File(medicos);
        File[] archivos = carpeta.listFiles();

        if (archivos != null) {
            for (File f : archivos) {
                Medico m = new Medico();
                m.cargar(f.getPath());
                lista.add(m);
            }
        }
        return lista;
    }

    private ArrayList<Consulta> cargarConsultas() {
        ArrayList<Consulta> lista = new ArrayList<>();
        File carpeta = new File(consultas);
        File[] archivos = carpeta.listFiles();

        if (archivos != null) {
            for (File f : archivos) {
                Consulta c = new Consulta();
                c.cargar(f.getPath());
                lista.add(c);
            }
        }
        return lista;
    }
    public void mostrarTodo() {
        System.out.println("\n=== MÉDICOS ===");
        for (Medico m : cargarMedicos()) {
            System.out.println(m);
        }

        System.out.println("\n=== CONSULTAS ===");
        for (Consulta c : cargarConsultas()) {
            System.out.println(c);
        }
    }

    public String getConsultas() { return consultas; }
    public String getMedicos() { return medicos; }
}
