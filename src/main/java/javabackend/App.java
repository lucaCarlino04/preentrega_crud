package javabackend;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javabackend.Excepciones.CategoriaNoEncontradaException;
import javabackend.Excepciones.ProductoNoEncontradoException;

public class App {
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static ArrayList<Categoria> categorias = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Integer opcion = 0;

    public static void main(String[] args) {
    do {
        mostrarMenu();
        try {
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: crearProducto(); break;
                case 2: modificarProducto(); break;
                case 3: eliminarProducto(); break;
                case 4: listarProductoId(); break;
                case 5: listarProductoNombre(); break;
                case 6: listarTodosProductos(); break;
                case 7: crearCategoria(); break;
                case 8: modificarCategoria(); break; 
                case 9: eliminarCategoria(); break;
                case 10: listarCategorias(); break;
                case 11: System.out.println("Hasta luego!"); break;
                default: System.out.println("Opción inválida."); break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Opcion inválida.");
            scanner.nextLine();
        }
        

    } while (opcion != 11);
}

    private static void mostrarMenu() {
        System.out.println("--- CRUD Artículos ---");
        System.out.println("1. Crear producto");
        System.out.println("2. Modificar producto"); // Modificar producto
        System.out.println("3. Eliminar producto"); // Eliminar producto
        System.out.println("4. Listar producto por ID"); // Listar producto por ID
        System.out.println("5. Listar producto por nombre"); // Listar producto por nombre
        System.out.println("6. Listar todos los productos"); // Listar todos los productos
        System.out.println("7. Crear categoria"); // Crear categoria
        System.out.println("8. Modificar categoria"); // Modificar categoria
        System.out.println("9. Eliminar categoria"); // Eliminar categoria
        System.out.println("10. Listar categorias");
        System.out.println("11. Salir"); // Listar categorias
        System.out.println("Opción: ");
    }

    private static void crearProducto() {
    if (categorias.isEmpty()) { 
            System.out.println("No hay categorías disponibles. Primero, cree una."); 
            crearCategoria();
        } else { 
            try {
                System.out.println("Nombre del producto: "); 
                String nombre = scanner.nextLine(); 
                
                System.out.println("Precio del producto: "); 
                Double precio = scanner.nextDouble(); 
                scanner.nextLine(); 
                System.out.println("Stock disponible: ");
                Integer stock = scanner.nextInt();
                
                listarCategorias();
                Categoria nueva = buscarCategoria();
                Producto nuevo = new Producto(nombre, precio, stock, nueva); 
                productos.add(nuevo); 
                System.out.println("Producto creado con éxito: " + nuevo); 
            } catch (CategoriaNoEncontradaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void crearCategoria() {
        System.out.println("Nombre de la categoría: ");
        String nombre = scanner.nextLine();

        Categoria nueva = new Categoria(nombre);
        categorias.add(nueva);
        System.out.println("Categoría creada con éxito: " + nueva);
    }

    private static void modificarProducto() {
        try {
            Producto prodBuscado = buscarProductoId();
            System.out.println("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.println("Nuevo precio (actual es " + prodBuscado.getPrecio() + "): ");
            Double precio = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Nuevo stock disponible (actual es " + prodBuscado.getStock() + "): ");
            Integer stock = scanner.nextInt();
            scanner.nextLine();

            try {
                Categoria catBuscada = buscarCategoria();
                prodBuscado.setCategoria(catBuscada);
                prodBuscado.setNombre(nombre);
                prodBuscado.setPrecio(precio);
                prodBuscado.setStock(stock);
                System.out.println("Producto modificado con éxito: " + prodBuscado);

            } catch (CategoriaNoEncontradaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void modificarCategoria() {
        try {
            Categoria buscada = buscarCategoria();
            System.out.println("Nuevo nombre: ");
            String nombre = scanner.next();
            scanner.nextLine();

            buscada.setNombre(nombre);
            System.out.println("Categoría modificada con éxito: " + buscada);
        } catch (CategoriaNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private static void eliminarProducto() {
        try {
            Producto buscado = buscarProductoId();
            productos.remove(buscado);
            System.out.println("Producto " + buscado + " eliminado con éxito.");
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private static void eliminarCategoria() {
        try {
            Categoria buscada = buscarCategoria();
            categorias.remove(buscada);
            System.out.println("Categoria " + buscada.getNombre() + " eliminada con éxito.");
        } catch (CategoriaNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private static void listarCategorias() {
        if (!categorias.isEmpty()) {
            System.out.println("Categorías disponibles: ");
            for (Categoria c : categorias) {
                System.out.println(c.getId() + ") " + c.getNombre());
            }
        } else {
            System.out.println("No existen categorías aún.");
        }
    }

    private static void listarProductoId() {
        try {
            Producto buscado = buscarProductoId();
            System.out.println(buscado);
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarProductoNombre() {
        try {
            List<Producto> buscados = buscarProductoNombre();
            System.out.println("Resultados: ");
            buscados.forEach(p -> System.out.println(p));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarTodosProductos() {
        if (!productos.isEmpty()) {
            System.out.println("Productos disponibles: ");
            productos.forEach(p -> System.out.println(p));
        } else {
            System.out.println("No exiten productos aun.");
        }
    }

    // Otros metodos utiles
    private static Producto buscarProductoId() throws ProductoNoEncontradoException {
        System.out.println("ID del producto: ");
        Integer idBuscado = scanner.nextInt();
        scanner.nextLine();

        Producto resultado = productos.stream().filter(p -> p.getId() == idBuscado).findFirst().orElse(null);
        if (resultado == null) {
            throw new ProductoNoEncontradoException("No se encontró el producto con código: " + idBuscado);
        }
        return resultado;
    }

    private static List<Producto> buscarProductoNombre() throws ProductoNoEncontradoException {
        System.out.println("Nombre del producto: ");
        String nombre = scanner.nextLine();
        
        List<Producto> resultados = productos.stream().filter(p -> p.getNombre().equalsIgnoreCase(nombre)).collect(Collectors.toList());
        if (resultados.isEmpty()) {
            throw new ProductoNoEncontradoException("No se encontraron productos con nombre: " + nombre);
        }
        return resultados;
    }

    private static Categoria buscarCategoria() throws CategoriaNoEncontradaException {
        System.out.println("ID de la categoría: ");
        Integer idCat = scanner.nextInt();
        scanner.nextLine();

        Categoria categoria = categorias.stream().filter(cat -> cat.getId() == idCat).findFirst().orElse(null);
        if (categoria == null) {
            throw new CategoriaNoEncontradaException("No se encontró categoría con código: " + idCat);
        }
        return categoria;
    }
}
