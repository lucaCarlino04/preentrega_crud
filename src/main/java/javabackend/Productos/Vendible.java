package javabackend.Productos;

public interface Vendible {
    String getNombre();
    Double getPrecio();
    Boolean estaDisponible();
    void aplicarDescuento(Double porcentaje);
}
