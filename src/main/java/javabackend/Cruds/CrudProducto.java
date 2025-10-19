package javabackend.Cruds;

import javabackend.App;
import javabackend.Excepciones.CategoriaNoEncontradaException;
import javabackend.Excepciones.ProductoNoEncontradoException;
import javabackend.Productos.Categoria;
import javabackend.Productos.Producto;

public class CrudProducto extends CrudConsola {
    private static final CrudProducto instance = new CrudProducto();
    private CrudProducto(){}
    public static CrudProducto getInstance() {
        return instance;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("1) Crear producto");
        System.out.println("2) Listar productos");
        // System.out.println("#) Listar producto por ID"); MAS TARDE LO IMPLEMENTO
        System.out.println("3) Modificar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("0) Atrás");
        System.out.println("Opción: ");
    }

    @Override
    public void crear() {
        // Mensaje por si no tengo categorias creadas todavía
        if (App.getCategorias().isEmpty()) {
            System.out.println("No hay categorías. Primero, cree una.");
            CrudCategoria.getInstance().crear();
            return;
        }
        try {
            String nombre = leerTexto("Nombre del producto: ");
            Double precio = leerDouble("Precio: ");
            Integer stock = leerEntero("Stock disponible: ");

            CrudCategoria.getInstance().listar();
            Categoria nueva = CrudCategoria.getInstance().buscarCategoria();

            Producto productoNuevo = new Producto(nombre, precio, stock, nueva);
            App.getProductos().add(productoNuevo);
            System.out.println("Producto creado: " + productoNuevo);
        } catch (CategoriaNoEncontradaException e) {
            System.out.println("Error: " + e);
            return;
        }
    }

    @Override
    public void listar() {
        if (App.getProductos().isEmpty()) {
            System.out.println("(sin productos)");
        } else {
            System.out.println("Productos disponibles: ");
            App.getProductos().forEach(p -> System.out.println(p));
        }
    }

    @Override
    public void modificar() {
        Integer opcion = -1;
        try {
            listar();
            Producto aModificar = buscarProductoId();
            
            do {
                System.out.println(aModificar);
                menuModificar();
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        cambiarNombre(aModificar);
                        break;
                    case 2:
                        cambiarPrecio(aModificar);
                        break;
                    case 3:
                        cambiarStock(aModificar);
                        break;
                    case 4:
                        cambiarCategoria(aModificar);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } while (opcion != 0);
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void eliminar() {
        try {
            listar();
            Producto aEliminar = buscarProductoId();
            App.getProductos().remove(aEliminar);
            System.out.println("Producto eliminado: " + aEliminar);
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e);
        }
        
    }

    private Producto buscarProductoId() throws ProductoNoEncontradoException {
        Integer idBuscado = leerEntero("ID del producto: ");
        scanner.nextLine();

        Producto resultado = App.getProductos().stream().filter(p -> p.getId() == idBuscado).findFirst().orElse(null);
        if (resultado == null) {
            throw new ProductoNoEncontradoException("No se encontró el producto con código: " + idBuscado);
        }
        return resultado;
    }

    private void menuModificar() {
        System.out.println("1) Cambiar nombre");
        System.out.println("2) Cambiar precio");
        System.out.println("3) Cambiar stock");
        System.out.println("4) Cambiar categoría");
        System.out.println("0) Listo");
        System.out.println("");
        System.out.println("Ingrese opción: ");
    }

    private void cambiarNombre(Producto p) {
        String viejoNombre = p.getNombre();
        String nuevoNombre = leerTexto("Nuevo nombre: ");
        p.setNombre(nuevoNombre);
        System.out.println("Nombre cambiado: " + viejoNombre + " -> " + nuevoNombre);
    }

    private void cambiarPrecio(Producto p) {
        Double viejoPrecio = p.getPrecio();
        Double nuevoPrecio = leerDouble("Nuevo precio: ");
        p.setPrecio(nuevoPrecio);
        System.out.println("Precio cambiado: " + viejoPrecio + " -> " + nuevoPrecio);
    }

    private void cambiarStock(Producto p) {
        Integer viejoStock = p.getStock();
        Integer nuevoStock = leerEntero("Nuevo stock: ");
        p.setStock(nuevoStock);
        System.out.println("Stock cambiado:" + viejoStock + " -> " + nuevoStock);
    }

    private void cambiarCategoria(Producto p) {
        CrudCategoria.getInstance().listar();
        try {
            Categoria catVieja = p.getCategoria();
            Categoria catNueva = CrudCategoria.getInstance().buscarCategoria();
            p.setCategoria(catNueva);
            System.out.println("Categoria cambiada: " + catVieja.getNombre() + " -> " + catNueva.getNombre());
        } catch (CategoriaNoEncontradaException e) {
            System.out.println("Error: " + e);
        }
    }
}
