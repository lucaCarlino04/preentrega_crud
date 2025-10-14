package javabackend.Excepciones;

public class NoExistenCategoriasException extends Exception {
    public NoExistenCategoriasException(String mensaje) {
        super(mensaje);
    }
}
