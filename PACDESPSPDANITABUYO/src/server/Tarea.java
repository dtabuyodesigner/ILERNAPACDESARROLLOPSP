package server;

public class Tarea {
    private String descripcion;
    private String estado;

    //Constructor para la clase Tarea
    public Tarea(String descripcion, String estado) {
        this.descripcion = descripcion;
        this.estado = estado;
    }

    //Getters y Setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    //Método toString para la clase Tarea
    @Override
    public String toString() {
        return "Tarea: " +
                "descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'';
    }
}

