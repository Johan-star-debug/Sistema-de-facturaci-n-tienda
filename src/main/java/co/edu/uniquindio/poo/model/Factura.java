package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {

    private String codigo;
    private LocalDate fecha;
    private Cliente cliente;
    private EstadoFactura estado;
    private MetodoPago metodoPago;

    private List<DetalleFactura> detalles;

    public Factura(String codigo, LocalDate fecha,
                   Cliente cliente,
                   MetodoPago metodoPago) {

        this.codigo = codigo;
        this.fecha = fecha;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
        this.estado = EstadoFactura.GENERADA;

        this.detalles = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {

        if (!producto.hayStock(cantidad)) {
            throw new IllegalArgumentException(
                    "No hay suficiente stock de " + producto.getNombre()
            );
        }

        DetalleFactura detalle = new DetalleFactura(
                producto,
                cantidad,
                producto.getPrecio()
        );

        detalles.add(detalle);
    }

    public double calcularTotal() {

        double total = 0;

        for (DetalleFactura detalle : detalles) {
            total += detalle.calcularSubtotal();
        }

        return total;
    }

    public void pagar() {
        estado = EstadoFactura.PAGADA;
    }

    public void cancelar() {
        estado = EstadoFactura.CANCELADA;
    }

    public void enviar() {
        estado = EstadoFactura.ENVIADA;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                ", cliente=" + cliente.getNombre() +
                ", estado=" + estado +
                ", metodoPago=" + metodoPago +
                ", total=" + calcularTotal() +
                '}';
    }
}