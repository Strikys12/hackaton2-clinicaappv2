package co.generation.clinica.service;

import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;

import java.util.ArrayList;
import java.util.List;

public class ClinicaService implements Consultable {

    List<Paciente> pacientes;
    List <Medico> medicos;
    List <Turno> turnos;

    public ClinicaService(){
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




}
