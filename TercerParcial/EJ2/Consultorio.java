package Persistencia.class1.ej1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Consultorio {
    private String nombre;
    private String diasAtencion;
    private String horaApertura;
    private String horaCierre;
    private ArrayList<Medico> medicos;
    private ArrayList<Consulta.Consulta> consultas;
    private ArrayList<Consulta.Paciente> pacientes;
    private static final String ARCHIVO_CONSULTORIO = "consultorio.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Consultorio(String nombre, String diasAtencion, String horaApertura, String horaCierre) {
        this.nombre = nombre;
        this.diasAtencion = diasAtencion;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.medicos = new ArrayList<>();
        this.consultas = new ArrayList<>();
        this.pacientes = new ArrayList<>();
        System.out.println(" Consultorio '" + nombre + "' creado con horario: " +
                diasAtencion + " de " + horaApertura + " a " + horaCierre);
    }

    // Método para cargar datos desde archivo
    public void cargarDatos() {
        try {
            File archivo = new File(ARCHIVO_CONSULTORIO);
            if (archivo.exists()) {
                Reader reader = Files.newBufferedReader(Paths.get(ARCHIVO_CONSULTORIO));
                Type tipoConsultorio = new TypeToken<Consultorio>(){}.getType();
                Consultorio consultorioCargado = gson.fromJson(reader, tipoConsultorio);

                // Copiar datos cargados
                this.medicos = consultorioCargado.medicos;
                this.consultas = consultorioCargado.consultas;
                this.pacientes = consultorioCargado.pacientes;

                reader.close();
                System.out.println("✓ Datos cargados exitosamente desde " + ARCHIVO_CONSULTORIO);
            }
        } catch (IOException e) {
            System.out.println("No se encontraron datos previos, se iniciará un nuevo consultorio");
        }
    }

    // Método para guardar datos en archivo
    public void guardarDatos() {
        try (FileWriter writer = new FileWriter(ARCHIVO_CONSULTORIO)) {
            gson.toJson(this, writer);
            System.out.println("✓ Datos guardados exitosamente en " + ARCHIVO_CONSULTORIO);
        } catch (IOException e) {
            System.out.println("✗ Error al guardar datos: " + e.getMessage());
        }
    }

    // Método para guardar copia de seguridad
    public void guardarCopiaSeguridad(String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            gson.toJson(this, writer);
            System.out.println("✓ Copia de seguridad guardada en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("✗ Error al guardar copia de seguridad: " + e.getMessage());
        }
    }

    public void agregarMedico(Medico medico) {
        medicos.add(medico);
        System.out.println(" Médico '" + medico.getNombreCompleto() + "' agregado al consultorio");
        guardarDatos();
    }

    public void agregarConsulta(Consulta.Consulta consulta) {
        consultas.add(consulta);
        System.out.println(" Consulta #" + consulta.getCi() + " agendada para " +
                consulta.getNombrePaciente() + " " + consulta.getApellidoPaciente());
        guardarDatos();
    }

    public void agregarPaciente(Consulta.Paciente paciente) {
        pacientes.add(paciente);
        System.out.println(" Paciente '" + paciente.getNombreCompleto() + "' registrado");
        guardarDatos();
    }

    public void mostrarEstado() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ESTADO DEL CONSULTORIO: " + nombre);
        System.out.println("=".repeat(60));

        System.out.println("Horario de atención: " + diasAtencion + " de " +
                horaApertura + " a " + horaCierre);

        System.out.println("\n--- Médicos registrados (" + medicos.size() + ") ---");
        for (Medico medico : medicos) {
            medico.mostrarInfo();
        }

        System.out.println("\n--- Pacientes registrados (" + pacientes.size() + ") ---");
        for (Consulta.Paciente paciente : pacientes) {
            paciente.mostrarInfo();
        }

        System.out.println("\n--- Consultas programadas (" + consultas.size() + ") ---");
        for (Consulta.Consulta consulta : consultas) {
            consulta.mostrarInfo();
        }
    }

    // Método para buscar médico por nombre y apellido (para ejercicio b)
    public Medico buscarMedico(String nombre, String apellido) {
        for (Medico medico : medicos) {
            if (medico.getNombreMed().equalsIgnoreCase(nombre) &&
                    medico.getApellidoMed().equalsIgnoreCase(apellido)) {
                return medico;
            }
        }
        return null;
    }

    // Método para dar de baja a un médico y sus consultas (ejercicio b)
    public void darBajaMedico(String nombre, String apellido) {
        Medico medico = buscarMedico(nombre, apellido);
        if (medico != null) {
            // Eliminar consultas asociadas
            consultas.removeIf(consulta -> consulta.getIdMed() == medico.getIdMed());
            // Eliminar médico
            medicos.remove(medico);
            System.out.println(" Médico '" + nombre + " " + apellido + "' y sus consultas eliminados");
            guardarDatos();
        } else {
            System.out.println(" No se encontró al médico " + nombre + " " + apellido);
        }
    }

    // Método para cambiar día de consultas en navidad o año nuevo (ejercicio c)
    public void cambiarConsultasFestivas() {
        int cambios = 0;
        for (Consulta.Consulta consulta : consultas) {
            if (consulta.getMes().equalsIgnoreCase("Diciembre") && consulta.getDia() == 25) {
                consulta.setDia(26); // Cambiar al 26 de Diciembre
                cambios++;
                System.out.println(" Consulta #" + consulta.getCi() + " reprogramada: Navidad → 26 Dic");
            } else if (consulta.getMes().equalsIgnoreCase("Enero") && consulta.getDia() == 1) {
                consulta.setDia(2); // Cambiar al 2 de Enero
                cambios++;
                System.out.println(" Consulta #" + consulta.getCi() + " reprogramada: Año Nuevo → 2 Ene");
            }
        }
        if (cambios > 0) {
            guardarDatos();
        }
        System.out.println(" Se reprogramaron " + cambios + " consultas festivas");
    }

    // Método para mostrar pacientes en día de cumpleaños (ejercicio d)
    public void mostrarPacientesEnCumpleaños(int dia, String mes) {
        System.out.println("\n--- Pacientes con consulta en " + dia + " de " + mes + " ---");
        boolean encontrados = false;
        for (Consulta.Consulta consulta : consultas) {
            if (consulta.getDia() == dia && consulta.getMes().equalsIgnoreCase(mes)) {
                System.out.println(" • " + consulta.getNombrePaciente() + " " +
                        consulta.getApellidoPaciente() + " con Dr. ID: " + consulta.getIdMed());
                encontrados = true;
            }
        }
        if (!encontrados) {
            System.out.println(" No hay consultas programadas para esta fecha");
        }
    }

    // Getters para Gson
    public String getNombre() { return nombre; }
    public String getDiasAtencion() { return diasAtencion; }
    public String getHoraApertura() { return horaApertura; }
    public String getHoraCierre() { return horaCierre; }
    public ArrayList<Medico> getMedicos() { return medicos; }
    public ArrayList<Consulta.Consulta> getConsultas() { return consultas; }
    public ArrayList<Consulta.Paciente> getPacientes() { return pacientes; }

    public void cerrarConsultorio() {
        System.out.println("\n Cerrando consultorio " + nombre + "...");
        guardarDatos();
    }
}
