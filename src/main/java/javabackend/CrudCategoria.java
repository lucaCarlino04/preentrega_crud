package javabackend;

import javabackend.Excepciones.CategoriaNoEncontradaException;

public class CrudCategoria extends CrudConsola {
    private static final CrudCategoria instance = new CrudCategoria();
    private CrudCategoria(){}
    public static CrudCategoria getInstance() {
        return instance;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("1) Crear categoría");
        System.out.println("2) Listar categorías");
        System.out.println("3) Modificar categoría");
        System.out.println("4) Eliminar categoría");
        System.out.println("0) Atrás");
    }


    @Override
    public void crear() {
        
        try {
            System.out.println("Nombre de la categoría: ");
            String nombre = scanner.nextLine();

            Categoria nuevaCategoria = new Categoria(nombre);
            App.getCategorias().add(nuevaCategoria);
            System.out.println("Categoría creada: " + nuevaCategoria);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void listar() {
        if (App.getCategorias().isEmpty()) {
            System.out.println("(Sin categorías)");
        } else {
            System.err.println("Categorías disponibles: ");
            for (Categoria c : App.getCategorias()) {
                System.out.println(c.getId() + ") " + c.getNombre());
            }
        }
    }

    @Override
    public void modificar() {
        listar();
        try {
            Categoria catBuscada = buscarCategoria();
            System.out.println("Nuevo nombre: ");
            String nombreNuevo = scanner.nextLine();
            catBuscada.setNombre(nombreNuevo);
            System.out.println("Nombre cambiado correctamente a " + nombreNuevo);
        } catch (CategoriaNoEncontradaException e) {
            System.out.println("Error: " + e);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void eliminar() {
        listar();
        try {
            Categoria catBuscada = buscarCategoria();
            App.getCategorias().remove(catBuscada);
            System.out.println("Categoría " + catBuscada + " eliminada con éxito.");
        } catch (CategoriaNoEncontradaException e) {
            System.out.println("Error: " + e);
        }
    }

    public Categoria buscarCategoria() throws CategoriaNoEncontradaException {
        System.out.println("ID de la categoría: ");
        Integer idCat = scanner.nextInt();
        scanner.nextLine();

        Categoria categoria = App.getCategorias().stream().filter(cat -> cat.getId() == idCat).findFirst().orElse(null);
        if (categoria == null) {
            throw new CategoriaNoEncontradaException("No se encontró categoría con código: " + idCat);
        }
        return categoria;
    }

}
