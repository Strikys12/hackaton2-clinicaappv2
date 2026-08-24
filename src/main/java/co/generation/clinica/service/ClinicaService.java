package co.generation.clinica.service;

import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.*;
import java.util.*;

public class ClinicaService {




    public void registrarPaciente(Paciente p) {
        if (!p.esValido()) {
            System.out.println("Error: El paciente no es válido.");
            return;
        }

        if (pacientes.contains(p)) {
            System.out.println("Error: Ya existe un paciente registrado conla cédula " + p.getCedula());
            return;
        }

        int nuevoId = pacientes.isEmpty() ? 1 : pacientes.get(pacientes.size() - 1).getId() + 1;
        p.setId(nuevoId);
        pacientes.add(p);
        System.out.println("¡Paciente registrado con éxito! " + p);
    }

    public Paciente buscarPorCedula(String cedula) {
        for (Paciente p : pacientes) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }

    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados en el sistema.");
            return;
        }

        List<Paciente> copiaLista = new ArrayList<>(pacientes);

        copiaLista.sort(Comparator.comparing(Paciente::getApellido)
                .thenComparing(Paciente::getNombre));

        System.out.println("--- LISTA DE PACIENTES ---");
        for (Paciente p : copiaLista) {
            System.out.println(p);
        }
    }

}
