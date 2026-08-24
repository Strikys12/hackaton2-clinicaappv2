
package co.generation.clinica.service;
import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.EstadoTurno;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;
import java.util.Comparator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


    public void asignarTurno(Turno t) {
        if (buscarPorCedula(t.getPaciente().getCedula()) == null) {
            System.out.println("El paciente no está registrado");
            return;
        }
        if (buscarPorNombreApellido(t.getMedico().getNombre(), t.getMedico().getApellido()) == null) {
            System.out.println("El médico no se encuentra registrado.");
            return;
        }

        if (turnos.contains(t)) {
            System.out.println("El médico ya tiene un turno asignado para ese horario");
            return;
        }

        int maxId = 0;
        for (Turno turno : turnos) {
            if (turno.getId() > maxId) {
                maxId = turno.getId();
            }
        }
        t.setId(maxId + 1);
        t.setEstado(EstadoTurno.PENDIENTE);

        turnos.add(t);
        System.out.println("Turno asignado con éxito!\n ID: " + t.getId() + t.toString());
    }

    public void cancelarTurno(int id) {
        Turno turnoEncontrado = null;

        for (Turno t : turnos) {
            if (t.getId() == id) {
                turnoEncontrado = t;
                break;
            }
        }

        if (turnoEncontrado == null) {
            System.out.println("El turno no existe");
            return;
        }

        if (turnoEncontrado.getEstado() == EstadoTurno.CANCELADO || turnoEncontrado.getEstado() == EstadoTurno.ATENDIDO) {
            System.out.println("No se puede cancelar el turno");
            return;
        }


        turnoEncontrado.setEstado(EstadoTurno.CANCELADO);
        System.out.println("Turno #" + id + " cancelado exitosamente.");
    }

    public void cambiarEstadoTurno(int id, EstadoTurno nuevo) {
        Turno turnoEncontrado = null;

        for (Turno t : turnos) {
            if (t.getId() == id) {
                turnoEncontrado = t;
                break;
            }
        }

        if (turnoEncontrado == null) {
            System.out.println("Turno no encontrado");
            return;
        }

        turnoEncontrado.setEstado(nuevo);
        System.out.println("El turno " + id + " Se ha cambiado");


    }

    //Métodos interfaz
    @Override
    public List<Turno> listarTurnosDelDia(LocalDate fecha) {
        List<Turno> filtrados = new ArrayList<>();
        if (fecha == null) return filtrados;

        for (Turno t : turnos) {
            if (t.getFechaHora().toLocalDate().equals(fecha)) {
                filtrados.add(t);
            }
        }

        filtrados.sort(Comparator.comparing(Turno::getFechaHora));
        return filtrados;
    }

    @Override
    public List<Turno> buscarPorPaciente(Paciente paciente) {
        List<Turno> filtrados = new ArrayList<>();
        if (paciente == null) return filtrados;

        for (Turno t : turnos) {
            if (t.getPaciente().equals(paciente)) {
                filtrados.add(t);
            }
        }
        return filtrados;
    }

    @Override
    public List<Turno> buscarPorMedico(Medico medico) {
        List<Turno> filtrados = new ArrayList<>();
        if (medico == null) return filtrados;

        for (Turno t : turnos) {
            if (t.getMedico().equals(medico)) {
                filtrados.add(t);
            }
        }
        return filtrados;
    }
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
