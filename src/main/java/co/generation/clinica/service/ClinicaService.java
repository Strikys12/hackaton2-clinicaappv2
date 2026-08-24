package co.generation.clinica.service;

import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;
import co.generation.clinica.model.EstadoTurno;
import co.generation.clinica.model.Especialidad;



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

//Métodos turnos

    public Paciente buscarPorCedula(String cedula){
        if (cedula == null) return  null;

        for(Paciente paciente : pacientes){
            if(paciente.getCedula().equalsIgnoreCase(cedula.trim())){
                return paciente;
            }
        }return null;
    }


    public Medico buscarPorNombreApellido(String nombre, String apellido) {
        if (nombre == null || apellido == null) return null;

        for (Medico medicos : this.medicos) {
            if (medicos.getNombre().equalsIgnoreCase(nombre.trim()) &&
                    medicos.getApellido().equalsIgnoreCase(apellido.trim())) {
                return medicos;
            }
        }
        return null;
    }





    public void asignarTurno(Turno t){
        if(buscarPorCedula(t.getPaciente().getCedula()) == null){
            System.out.println("El paciente no está registrado");
            return;
        }
        if (buscarPorNombreApellido(t.getMedico().getNombre(), t.getMedico().getApellido()) == null) {
            System.out.println("El médico no se encuentra registrado.");
            return;
        }

        if(turnos.contains(t)){
            System.out.println("El médico ya tiene un turno asignado para ese horario");
            return;
        }

        int maxId = 0;
        for (Turno turno : turnos){
            if(turno.getId() > maxId){
                maxId = turno.getId();
            }
        }
        t.setId(maxId + 1);

        turnos.add(t);
        System.out.println("Turno asignado con éxito!\n ID: " + t.getId() + toString());
    }

    public void cancelarTurno(int id){
        Turno turnoEncontrado = null;

        for(Turno t : turnos){
            if(t.getId() == id){
                turnoEncontrado = t;
                break;
            }
        }

        if(turnoEncontrado == null){
            System.out.println("El turno no existe");
            return;
        }

        if(turnoEncontrado.getEstado() == EstadoTurno.CANCELADO || turnoEncontrado.getEstado() == EstadoTurno.ATENDIDO ){
            System.out.println("No se puede cancelar el turno");
            return;
        }



        turnoEncontrado.setEstado(EstadoTurno.CANCELADO);
        System.out.println("Turno #" + id + " cancelado exitosamente.");
    }

    public void cambiarEstadoTurno(int id, EstadoTurno nuevo){
        Turno turnoEncontrado = null;

        for(Turno t: turnos){
            if(t.getId() == id){
                turnoEncontrado = t;
                break;
            }
        }

        if(turnoEncontrado == null){
            System.out.println("Turno no encontrado");
            return;
        }

        turnoEncontrado.setEstado(nuevo);
        System.out.println("El turno " + id + " Se ha cambiado");



    }

}
