package javabackend.Clientes;

import java.util.ArrayList;

import javabackend.Productos.Producto;

public class Pedido {
    private static Integer contador = 1;
    private final Integer id;
    private final ArrayList<Producto> contenido = new ArrayList<>();
    private final Cliente cliente;
    public Pedido(Cliente cliente) {
        this.id = contador++;
        this.cliente = cliente;
    }
    public Integer getId() {
        return id;
    }
    public ArrayList<Producto> getContenido() {
        return contenido;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void agregarProducto(Producto p) {
        contenido.add(p);
    }

    public void descartarProducto(Producto p) {
        contenido.remove(p);
    }

    public Double total() {
        Double t = 0.0;
        for (Producto p : contenido) {
            t += p.getPrecio();
        }
        return t;
        //Otra forma:
        // return contenido.stream().mapToDouble(p -> p.getPrecio()).sum();
    }
}
