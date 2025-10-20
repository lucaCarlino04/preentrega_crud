package javabackend.Clientes;

public class Cliente extends Persona {
    private String correo;
    private final Pedido pedido = new Pedido(this);
    public Cliente(String nombre, String correo) {
        super(nombre);
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido.");
        }
        this.correo = correo;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido.");
        }
        this.correo = correo;
    }
    public Pedido getPedido() {
        return pedido;
    }   

    @Override
    public String toString() {
        return "Cliente (ID:  " + id + " | Nombre: " + nombre + " | Correo: " + correo + ")";
    }
}
