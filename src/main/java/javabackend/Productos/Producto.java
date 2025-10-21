package javabackend.Productos;

public class Producto implements Vendible {
    private static Integer contador = 1;
    private final Integer id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private Categoria categoria; // Es opcional, aunque en el CRUD siempre pide una

    // Si tiene categoría
    public Producto(String nombre, Double precio, Integer stock, Categoria categoria) {
        if (precio < 0 || stock < 0) {
            throw new IllegalArgumentException("El precio o stock no pueden ser negativos.");
        } else if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar en blanco.");
        }
        this.categoria = categoria;
        this.id = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Si no tiene categoría
    public Producto(String nombre, Double precio, Integer stock) {
        if (precio < 0 || stock < 0) {
            throw new IllegalArgumentException("El precio o stock no pueden ser negativos.");
        } else if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar en blanco.");
        }
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
            System.out.println("(sin categoría)");
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
        if (categoria != null) {
            return "ID: " + id + " | Nombre: " + nombre + " | Precio: " + precio + " | Stock disponible: " + stock + " | Categoría: " + getCategoria().getNombre();
        } else {
            return "ID: " + id + " | Nombre: " + nombre + " | Precio: " + precio + " | Categoría: (sin categoría)";
        }
    }

    @Override
    public Boolean estaDisponible() {
        return stock > 0;
    }

    @Override
    public void aplicarDescuento(Double porcentaje) {
        this.precio *= (1 - porcentaje / 100);
    }    
}

// precio *= (1 - porcentaje / 100);
