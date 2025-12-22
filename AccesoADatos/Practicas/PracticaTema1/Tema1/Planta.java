package Tema1;

public class Planta {
    private int codigo;
    private String nombre;
    private String foto;
    private String descripcion;

    public Planta(int codigo, String nombre, String foto, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Planta{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", foto='" + foto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
