package Tema1;

import java.io.Serializable;

public class Empleado implements Serializable {
    private int identificacion;
    private String nombre;
    private String contrasena;
    private String cargo;

    public Empleado(int identificacion, String nombre, String contrasena, String cargo) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        setContrasena(contrasena);
        setCargo(cargo);
        this.nombre = nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if (contrasena.length() >= 5 && contrasena.length() <= 7) {
            this.contrasena = contrasena;
        } else {
            throw new IllegalArgumentException("La contraseña debe tener entre 5 y 7 caracteres.");
        }
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if (cargo.equalsIgnoreCase("vendedor") || cargo.equalsIgnoreCase("gestor")) {
            this.cargo = cargo.toLowerCase();
        } else {
            throw new IllegalArgumentException("El cargo debe ser 'vendedor' o 'gestor'.");
        }
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "identificacion=" + identificacion +
                ", nombre='" + nombre + '\'' +
                ", contraseña=" + contrasena + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}