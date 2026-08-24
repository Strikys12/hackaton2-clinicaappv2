package co.generation.clinica;

import co.generation.clinica.datos.DatosCSV;
import co.generation.clinica.model.Especialidad;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.service.ClinicaService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Crear ClinicaService
        ClinicaService cs = new ClinicaService();
        //Cargar datos
//        DatosCSV.cargar(cs);
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
                    String cedula = scanner.nextLine();
                    String nombre = scanner.nextLine();
                    String apellido = scanner.nextLine();
                    String telefono = scanner.nextLine();
                    new Paciente(cedula, nombre, apellido, telefono);
                    //registrar paciente
                }
                case "2" -> {
                    String nombre = scanner.nextLine();
                    String apellido = scanner.nextLine();
                    int especialidad = scanner.nextInt();

                }  //registrar medico
                case "3" -> System.out.println(cs.getTurnos());  //asignar turno
                case "4" -> System.out.println(cs.getTurnos());  //listar turnos del dia
                case "5" -> System.out.println(cs.getTurnos());  //Cancelar turno
                case "6" -> System.out.println(cs.getTurnos());  //Ver turno por medico
                case "7" -> System.out.println(cs.getTurnos());  //Ver turno por paciente
                case "8" -> System.out.println(cs.getTurnos());  //Cambiar estado de turno
                case "9" -> System.out.println(cs.getTurnos());  //Listar pacientes
                case "10" -> System.out.println(cs.getTurnos());  //Listar medicos
                default -> {
                    System.out.println("La opcion ingresada es invalida!");
                    System.out.println("Por favor vuelva a intentar0");
                }


            }


        }
        while (!salir);


    }
}
