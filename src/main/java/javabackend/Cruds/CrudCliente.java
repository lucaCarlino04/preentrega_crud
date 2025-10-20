package javabackend.Cruds;

import javabackend.App;
import javabackend.Clientes.Cliente;
import javabackend.Excepciones.ClienteNoEncontradoException;
import javabackend.Excepciones.ProductoNoEncontradoException;
import javabackend.Productos.Producto;

public class CrudCliente extends CrudConsola {
    private static final CrudCliente instance = new CrudCliente();
    private CrudCliente(){}
    public static CrudCliente getInstance() {
        return instance;
    }

    @Override
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
                case 4: hacerPedido(); break;
                case 5: verPedido(); break;
                case 6: eliminar(); break;
                case 0: break;
                default: 
                    System.out.println("Opción no válida."); 
                    break;
            }
        } while (opcionCrud != 0);
    }

    @Override
    public void mostrarMenu() {
        System.out.println("1) Crear usuario");
        System.out.println("2) Listar usuarios");
        System.out.println("3) Modificar usuarios");
        System.out.println("4) Hacer un pedido a un usuario");
        System.out.println("5) Ver los pedidos de un usuario");
        System.out.println("6) Eliminar usuario");
        System.out.println("0) Salir");
    }

    @Override
    public void crear() {
        try {
            String nombre = leerTexto("Nombre de usuario: ");
            String email = leerTexto("Correo: ");
            Cliente nuevo = new Cliente(nombre, email);
            App.getClientes().add(nuevo);
            System.out.println("Cliente añadido: " + nuevo);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void listar() {
        if (App.getClientes().isEmpty()) {
            System.out.println("(sin clientes)");
            return;
        } else {
            System.out.println("Lista de clientes: ");
            for (Cliente c : App.getClientes()) {
                System.out.println(c.getId() + ") " + c.getNombre());
            }
            System.out.println("");
        }
    }

    @Override
    public void modificar() {
        Integer opcion = -1;
        try {
            listar();
            Cliente aModificar = buscarCliente();
            
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
                        cambiarCorreo(aModificar);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } while (opcion != 0);
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error: " + e);
        }
    }

    public void hacerPedido() {
        if (App.getProductos().isEmpty()) {
            System.out.println("(sin productos)");
            return;
        }
        if (App.getClientes().isEmpty()) {
            System.out.println("(sin clientes)");
            return;
        }

        listar();
        try {
            Integer opcion = -1;
            Cliente cliente = buscarCliente();
            do {
                CrudProducto.getInstance().listar();
                System.out.println("\n0) Salir");
                Producto producto = CrudProducto.getInstance().buscarProductoId();
                if (opcion == 0) {
                    break;
                }
                cliente.getPedido().agregarProducto(producto);
                System.out.println("Producto " + producto.getNombre() + " añadido a cliente " + cliente.getNombre() + "(ID " + cliente.getId() + ")");
            } while (true);

        } catch (ClienteNoEncontradoException | ProductoNoEncontradoException e) {
            System.out.println("Error: " + e);
        }
    }

    public void verPedido() {
        if (App.getClientes().isEmpty()){
            System.out.println("(sin clientes)");
            return;
        }

        listar();
        try {
            Cliente cliente = buscarCliente();
            if (cliente.getPedido().getContenido().isEmpty()) {
                System.out.println("(sin pedidos)");
            } else {
                System.out.println("Productos de " + cliente.getNombre() + " (ID " + cliente.getId() + ")");
                for (Producto p : cliente.getPedido().getContenido()) {
                    System.out.println("ID: " + p.getId() + " | Nombre:  " + p.getNombre());
                }
            }
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void eliminar() {
        if (App.getClientes().isEmpty()){
            System.out.println("(sin clientes)");
            return;
        }

        listar();
        try {
            Cliente aEliminar = buscarCliente();
            App.getClientes().remove(aEliminar);
            System.out.println("Cliente eliminado: " + aEliminar);
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error: " + e);
        }
    }

    public Cliente buscarCliente() throws ClienteNoEncontradoException {
        Integer idBuscado = leerEntero("ID del cliente: ");
        scanner.nextLine();

        Cliente resultado = App.getClientes().stream().filter(c -> c.getId() == idBuscado).findFirst().orElse(null);
        if (resultado == null) {
            throw new ClienteNoEncontradoException("No se encontró el cliente con ID: " + idBuscado);
        }
        return resultado;
    }

    private void menuModificar() {
        System.out.println("1) Cambiar nombre");
        System.out.println("2) Cambiar email");
        System.out.println("0) Listo");
        System.out.println("");
        System.out.println("Ingrese opción: ");
    }

    private void cambiarNombre(Cliente c) {
        try {
            String nombreNuevo = leerTexto("Nuevo nombre: ");
            String nombreViejo = c.getNombre();
            c.setNombre(nombreNuevo);
            System.out.println("Nombre cambiado: " + nombreViejo + " -> " + nombreNuevo);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e);
        }
    }

    private void cambiarCorreo(Cliente c) {
        try {
            String correoNuevo = leerTexto("Nuevo correo: ");
            String correoViejo = c.getCorreo();
            c.setCorreo(correoNuevo);
            System.out.println("Correo cambiado: " + correoViejo + " -> " + correoNuevo);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e);
        }
    }
    
}

