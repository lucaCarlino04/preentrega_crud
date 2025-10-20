package javabackend;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javabackend.Clientes.Cliente;
import javabackend.Cruds.CrudCategoria;
import javabackend.Cruds.CrudCliente;
import javabackend.Cruds.CrudProducto;
import javabackend.Productos.Categoria;
import javabackend.Productos.Producto;

public class App {
    private static final ArrayList<Producto> productos = new ArrayList<>();
    private static final ArrayList<Categoria> categorias = new ArrayList<>();
    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Integer opcion = 0;

    public static void main(String[] args) {
    do {
        mostrarMenu();
        try {
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: CrudProducto.getInstance().crud(); break;
                case 2: CrudCategoria.getInstance().crud(); break;
                case 3: CrudCliente.getInstance().crud(); break;
                case 0: System.out.println("Hasta luego!"); break;
                default: System.out.println("Opción inválida."); break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Opcion inválida.");
            scanner.nextLine();
        }
        

    } while (opcion != 0);
}

    private static void mostrarMenu() {
        System.out.println("--- CRUD ---");
        System.out.println("1. Operaciones con artículos");
        System.out.println("2. Operaciones con categorías");
        System.out.println("3. Operaciones con clientes");
        System.out.println("0. Salir");
        System.out.println("Opción: ");
    }

    public static ArrayList<Producto> getProductos() {
        return productos;
    }

    public static ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    
}
