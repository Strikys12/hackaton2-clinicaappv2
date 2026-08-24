package co.generation.clinica.model;

public class Paciente {
    public int getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCedula(String cedula) {
        if (cedula.trim() == "") {
            throw new IllegalArgumentException("Cedula no puede estar vacío!");
        }
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        if (nombre.trim() == "") {
            throw new IllegalArgumentException("Nombre no puede estar vacío!");
        }
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        if (apellido.trim() == "") {
            throw new IllegalArgumentException("Apellido no puede estar vacío!");
        }
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        if (telefono.trim() == "") {
            throw new IllegalArgumentException("Telefono no puede estar vacío!");
        } else if (telefono.matches("^[0-9]{7,10}$"))){
            throw new IllegalArgumentException("Telefono invalido!");

        }
        this.telefono = telefono;
    }

    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;

    public Paciente(int id, String cedula, String nombre, String apellido, String telefono) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Paciente(String cedula, String nombre, String apellido, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Paciente otro){
            if(this.cedula == otro.cedula){
                return  true;
            }
        }
            return false;
    }
}
