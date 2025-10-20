package javabackend.Clientes;

public abstract class Persona {
    protected static Integer contador = 1;
    protected final Integer id;
    protected String nombre;
    public Persona(String nombre) {
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar en blanco.");
        }
        this.id = contador++;
        this.nombre = nombre;
    }
    public Integer getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar en blanco.");
        }
        this.nombre = nombre;
    }
}
