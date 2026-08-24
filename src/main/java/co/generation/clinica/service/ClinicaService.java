package co.generation.clinica.service;

import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class ClinicaService implements Consultable {

    List<Paciente> pacientes;
    List<Medico> medicos;
    List<Turno> turnos;

    public ClinicaService() {
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.turnos = new ArrayList<>();
    }


    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }


    // METODO MÉDICO

    public void registrarMedico(Medico m) {
        if (!m.esValido()) {
            throw new IllegalArgumentException("Los datos del médico no son válidos");
        }

        if (medicos.contains(m)) {
            throw new IllegalArgumentException("El médico ya se encuentra registrado");
        }

        int nuevoId = 1;
        for (Medico medico : medicos) {
            if (medico.getId() >= nuevoId) {
                nuevoId = medico.getId() + 1;
            }
        }
        m.setId(nuevoId);

        medicos.add(m);

        System.out.println("Médico registrado correctamente");

    }

    public Medico buscarPorNombreApellido(String nombre, String apellido) {
        for (Medico medico : medicos) {

            if (medico.getNombre().equalsIgnoreCase(nombre)
                    && medico.getApellido().equalsIgnoreCase(apellido)) {
                return medico;
            }

        }
        return null;
    }

    public void listarMedicos() {
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
            return;
        }
        List<Medico> copia = new ArrayList<>(medicos);
        copia.sort(
                Comparator.comparing(Medico::getEspecialidad)
                        .thenComparing(Medico::getApellido, String.CASE_INSENSITIVE_ORDER));
        for (Medico medico : copia) {
            System.out.println(medico);
        }
    }
}
