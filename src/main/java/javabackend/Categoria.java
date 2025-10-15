package javabackend;

class Categoria {
    private static Integer contador = 1;
    private final Integer id;
    private String nombre;

    public Categoria(String nombre) {
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
        if (!nombre.isEmpty()) {
            this.nombre = nombre;
        } else{
            System.out.println("El nombre no puede estar en blanco.");
        }
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " - " + nombre;
    }
}