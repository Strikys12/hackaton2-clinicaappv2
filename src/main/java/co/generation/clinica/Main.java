package co.generation.clinica;
import co.generation.clinica.datos.DatosCSV;
import co.generation.clinica.model.*;
import co.generation.clinica.service.ClinicaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Crear ClinicaService
        ClinicaService cs = new ClinicaService();
        //Cargar datos
        DatosCSV.cargar(cs);
        boolean salir = false;
        do {
            System.out.println("++========================================++");
            System.out.println("||            CLINICAAPP — MENÚ           ||");
            System.out.println("||========================================||");
            System.out.println("|| 1. Registrar paciente                  ||");
            System.out.println("||========================================||");
            System.out.println("|| 2. Registrar médico                    ||");
            System.out.println("||========================================||");
            System.out.println("|| 3. Asignar turno                       ||");
            System.out.println("||========================================||");
            System.out.println("|| 4. Listar turnos del día               ||");
            System.out.println("||========================================||");
            System.out.println("|| 5. Cancelar turno                      ||");
            System.out.println("||========================================||");
            System.out.println("|| 6. Ver turnos por médico               ||");
            System.out.println("||========================================||");
            System.out.println("|| 7. Ver turnos por paciente             ||");
            System.out.println("||========================================||");
            System.out.println("|| 8. Cambiar estado de turno             ||");
            System.out.println("||========================================||");
            System.out.println("|| 9. Listar pacientes                    ||");
            System.out.println("||========================================||");
            System.out.println("|| 10. Listar médicos                     ||");
            System.out.println("||========================================||");
            System.out.println("|| 0. Salir                               ||");
            System.out.println("++========================================++");
            System.out.println("Ingresa una opcion");
            Scanner scanner = new Scanner(System.in);
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "0" -> {
                    salir = true;
                    System.out.println("Saliendo del programa");
                }
                case "1" -> {
                    System.out.println("Ingrese la cedula del paciente");
                    String cedula = scanner.nextLine();
                    System.out.println("Ingrese el nombre del paciente");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el apellido del paciente");
                    String apellido = scanner.nextLine();
                    System.out.println("Ingrese el telefono del paciente");
                    String telefono = scanner.nextLine();
                    new Paciente(cedula, nombre, apellido, telefono);
                    System.out.println("Guardado con exito!");
                    //registrar paciente
                }
                case "2" -> {
                    System.out.println("Ingrese el nombre del medico");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el apellido del medico");
                    String apellido = scanner.nextLine();
                    System.out.println("Ingrese el numero de la opcion de la especialidad del medico \n 1. General \n 2. Pediatria \n 3. Cardiologia \n 4. Urgencias");
                    int especialidad = scanner.nextInt();
                    switch (especialidad){
                        case 1 ->{cs.registrarMedico(new Medico(nombre, apellido, Especialidad.GENERAL));}
                        case 2 ->{cs.registrarMedico(new Medico(nombre, apellido, Especialidad.PEDIATRIA));}
                        case 3 ->{cs.registrarMedico(new Medico(nombre, apellido, Especialidad.CARDIOLOGIA));}
                        case 4 ->{cs.registrarMedico(new Medico(nombre, apellido, Especialidad.URGENCIAS));}
                        default -> System.out.println("Opcion Invalida!");
                    }
                    //registrar medico
                }
                case "3" -> {
                    System.out.println("Ingrese la cedula");
                    String cedula = scanner.nextLine();

                    System.out.println("Ingrese el Nombre del medico");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el apellido del medico");
                    String apellido = scanner.nextLine();
                    Paciente paciente = cs.buscarPorCedula(cedula);
                    Medico medico = cs.buscarPorNombreApellido(nombre, apellido);
                    Turno turno = new Turno(paciente, medico, LocalDateTime.now().plusHours(2));
                    cs.asignarTurno(turno);//asignar turno
                    System.out.println("Exito!");
                }
                case "4" -> {
                    List<Turno> turnos = cs.listarTurnosDelDia(LocalDate.now());
                    for (Turno turno : turnos) {
                        System.out.printf("Turno %d \n Paciente: %s \n Medico: %s \n+ Fecha y Hora: " + turno.getFechaHora(), turno.getId(), turno.getPaciente(), turno.getMedico());
                    }
                    //listar turnos del dia
                }
                case "5" -> {
                    System.out.println("Ingrese la cedula del paciente para cancelar su turno");
                    String cedula = scanner.nextLine();
                    int turnoId = cs.buscarPorCedula(cedula).getId();
                    cs.cancelarTurno(turnoId);
                    //Cancelar turno
                }
                case "6" -> {
                    System.out.println("Ingrese el nombre del medico");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el apellido del medico");
                    String apellido = scanner.nextLine();
                    Medico medico = cs.buscarPorNombreApellido(nombre, apellido);
                    List<Turno> turnos = cs.buscarPorMedico(medico);
                    for (Turno turno : turnos) {
                        System.out.printf("Turno %d \n Paciente: %s \n Medico: %s \n+ Fecha y Hora: " + turno.getFechaHora(), turno.getId(), turno.getPaciente(), turno.getMedico());
                    }

                }  //Ver turno por medico
                case "7" -> {
                    System.out.println("Ingrese la cedula del paciente");
                    String cedula = scanner.nextLine();
                    Paciente paciente = cs.buscarPorCedula(cedula);
                    List<Turno> turnos = cs.buscarPorPaciente(paciente);
                    for (Turno turno : turnos) {
                        System.out.printf("Turno %d \n Paciente: %s \n Medico: %s \n+ Fecha y Hora: " + turno.getFechaHora(), turno.getId(), turno.getPaciente(), turno.getMedico());
                    }
                }  //Ver turno por paciente
                case "8" -> {
                    System.out.println("Ingrese el ID de su turno");
                    int idTurno = scanner.nextInt();
                    System.out.println("Ingrese el numero de la opcion para cambiar el estado del turno \n 1. Pendiente \n 2.Atendido \n 3.Cancelado");
                    int estado = scanner.nextInt();
                    switch (estado) {
                        case 1 -> {
                            cs.cambiarEstadoTurno(idTurno, EstadoTurno.PENDIENTE);
                            System.out.println("Cambio realizado");
                        }
                        case 2 -> {
                            cs.cambiarEstadoTurno(idTurno, EstadoTurno.ATENDIDO);
                            System.out.println("Cambio realizado");
                        }
                        case 3 -> {
                            cs.cambiarEstadoTurno(idTurno, EstadoTurno.CANCELADO);
                            System.out.println("Cambio realizado");
                        }
                        default -> System.out.println("Opcion ingresada incorrecta o ID de turno no encontrado");
                    }


                } //Cambiar estado de turno
                case "9" -> {
                    List<Paciente> pacientes = cs.listarPacientes();
                    for (Paciente paciente : pacientes) {
                        System.out.printf("Nombre %s \n Apellido: %s \n Cedula %s \n+ Telefonp: %s ", paciente.getNombre(), paciente.getApellido(), paciente.getCedula(), paciente.getTelefono());
                    }
                }  //Listar pacientes
                case "10" -> {
                    List<Medico> medicos = cs.listarMedicos();
                    for (Medico medico : medicos) {
                        System.out.printf("Nombre %s \n Apellido: %s \n+ Especialidad %s ", medico.getNombre(), medico.getApellido(), medico.getEspecialidad().toString());
                    }
                }  //Listar medicos
                default -> {
                    System.out.println("La opcion ingresada es invalida!");
                    System.out.println("Por favor vuelva a intentar0");
                }


            }


        }
        while (!salir);
        DatosCSV.guardar(cs);

    }
}
