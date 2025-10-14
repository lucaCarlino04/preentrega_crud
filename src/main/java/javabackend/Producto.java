package javabackend;

public class Producto {
    private static Integer contador = 1;
    private final Integer id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private Categoria categoria = null;

    // Si tiene categoria
    public Producto(String nombre, Double precio, Integer stock, Categoria categoria) {
        if (precio < 0 || stock < 0) {
            throw new IllegalArgumentException("El precio o stock no pueden ser negativos.");
        }

        this.categoria = categoria;
        this.id = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        } 
        this.stock = stock;    
    }

    public Categoria getCategoria() {
        if (categoria != null) {
            return categoria;
        }
        else {
            System.out.println("Este producto no tiene una categoría vínculada.");
            return null;
        }
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        } 

        this.precio = precio;
    }

    @Override
    public String toString() {
        if (categoria == null) {
            return "Producto(Nombre: " + getNombre() + " | Precio: " + getPrecio();
        } else {
            return "ID: "+ id + " | Nombre: " + getNombre() + " | Precio: " + getPrecio() + " | Categoría: " + getCategoria().getNombre();
        }
    }    
}