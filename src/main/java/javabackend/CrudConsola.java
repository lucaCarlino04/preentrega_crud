package javabackend;

import java.util.Scanner;

public abstract class CrudConsola {
    protected final Scanner scanner = new Scanner(System.in);

    protected abstract void crear();
    protected abstract void listar();
    protected abstract void modificar();
    protected abstract void eliminar();

    protected void mostrarMenu() {
        System.out.println("1) Crear");
        System.out.println("2) Listar");
        System.out.println("3) Modificar");
        System.out.println("4) Eliminar");
        System.out.println("0) Salir");
    }
    
    protected Integer leerEntero(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextInt();
    }

    protected Double leerDouble(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextDouble();
    }

    protected String leerTexto(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }


    // Idea: Hacer este método abstracto para poner diferentes opciones en cada CRUD, 
    // sobre todo para poder poner "Listar producto por ID"
    public void crud() {
        Integer opcionCrud = -1;
        do {
            mostrarMenu();
            opcionCrud = scanner.nextInt();
            scanner.nextLine();

            switch (opcionCrud) {
                case 1: crear(); break;
                case 2: listar(); break;
                case 3: modificar(); break;
                case 4: eliminar(); break;
                case 0: break;
                default: 
                    System.out.println("Opción no válida."); 
                    break;
            }
        } while (opcionCrud != 0);
    }
}
