package javabackend;

import javabackend.Excepciones.CategoriaNoEncontradaException;
import javabackend.Excepciones.ProductoNoEncontradoException;

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
        // System.out.println("3) Listar producto por ID"); MAS TARDE LO IMPLEMENTO
        System.out.println("3) Modificar producto");
        System.out.println("4) Eliminar producto");
        System.out.println("0) Atrás");
        System.out.println("Opción: ");
    }
    @Override
    public void crear() {
        if (App.getCategorias().isEmpty()) {
            System.out.println("No hay categorías. Primero, cree una.");
            CrudCategoria.getInstance().crear();
            return;
        }
        try {
            System.out.println("Nombre del producto: ");
            String nombre = scanner.nextLine();

            System.out.println("Precio: ");
            Double precio = scanner.nextDouble();

            System.out.println("Stock disponible: ");
            Integer stock = scanner.nextInt();

            CrudCategoria.getInstance().listar();
            Categoria nueva = CrudCategoria.getInstance().buscarCategoria();

            Producto productoNuevo = new Producto(nombre, precio, stock, nueva);
            App.getProductos().add(productoNuevo);
            System.out.println("Se creó con éxito el producto: " + productoNuevo);
        } catch (CategoriaNoEncontradaException e) {
            System.out.println("Error: " + e);
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
            Producto aModificar = buscarProductoId();
            
            do {
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
            Producto aEliminar = buscarProductoId();
            App.getProductos().remove(aEliminar);
            System.out.println("Producto eliminado: " + aEliminar);
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e);
        }
        
    }

    private Producto buscarProductoId() throws ProductoNoEncontradoException {
        System.out.println("ID del producto: ");
        Integer idBuscado = scanner.nextInt();
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
        System.out.println("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();

        p.setNombre(nuevoNombre);
        System.out.println("Nombre cambiado a " + nuevoNombre);
    }

    private void cambiarPrecio(Producto p) {
        System.out.println("Nuevo precio: ");
        Double nuevoPrecio = scanner.nextDouble();

        p.setPrecio(nuevoPrecio);
        System.out.println("Precio cambiado a " + nuevoPrecio);
    }

    private void cambiarStock(Producto p) {
        System.out.println("Nuevo stock: ");
        Integer nuevoStock = scanner.nextInt();

        p.setStock(nuevoStock);
        System.out.println("Stock cambiado a " + nuevoStock);
    }

    private void cambiarCategoria(Producto p) {
        CrudCategoria.getInstance().listar();
        try {
            Categoria catNueva = CrudCategoria.getInstance().buscarCategoria();
            p.setCategoria(catNueva);
            System.out.println("Categoria cambiada a " + catNueva.getNombre());
        } catch (CategoriaNoEncontradaException e) {
            System.out.println("Error: " + e);
        }
    }
}
