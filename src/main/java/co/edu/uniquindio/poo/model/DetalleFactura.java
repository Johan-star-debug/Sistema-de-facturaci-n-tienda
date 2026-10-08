package co.edu.uniquindio.poo.model;

public class DetalleFactura {

    private Producto producto;
    private int cantidad;
    private double precioAplicado;

    public DetalleFactura(Producto producto, int cantidad,
                          double precioAplicado) {

        this.producto = producto;
        this.cantidad = cantidad;
        this.precioAplicado = precioAplicado;
    }

    public double calcularSubtotal() {
        return cantidad * precioAplicado;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioAplicado() {
        return precioAplicado;
    }

    @Override
    public String toString() {
        return "DetalleFactura{" +
                "producto=" + producto.getNombre() +
                ", cantidad=" + cantidad +
                ", precioAplicado=" + precioAplicado +
                ", subtotal=" + calcularSubtotal() +
                '}';
    }
}