package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tienda {

    private List<Cliente> clientes;
    private List<Producto> productos;
    private List<Factura> facturas;

    public Tienda() {
        clientes = new ArrayList<>();
        productos = new ArrayList<>();
        facturas = new ArrayList<>();
    }

    // =========================
    // CLIENTES
    // =========================

    public void agregarCliente(Cliente cliente) {

        if (buscarCliente(cliente.getDocumento()) != null) {
            throw new IllegalArgumentException(
                    "El cliente ya existe."
            );
        }

        clientes.add(cliente);
    }

    public Cliente buscarCliente(String documento) {

        for (Cliente cliente : clientes) {

            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }

        return null;
    }

    // =========================
    // PRODUCTOS
    // =========================

    public void agregarProducto(Producto producto) {

        if (buscarProducto(producto.getCodigo()) != null) {
            throw new IllegalArgumentException(
                    "El producto ya existe."
            );
        }

        productos.add(producto);
    }

    public Producto buscarProducto(String codigo) {

        for (Producto producto : productos) {

            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }

        return null;
    }

    // =========================
    // FACTURAS
    // =========================

    public void registrarFactura(Factura factura) {

        facturas.add(factura);

        factura.getCliente().agregarFactura(factura);
    }

    public Factura buscarFactura(String codigo) {

        for (Factura factura : facturas) {

            if (factura.getCodigo().equals(codigo)) {
                return factura;
            }
        }

        return null;
    }

    // =========================
    // CONFIRMAR VENTA
    // =========================

    public void confirmarVenta(Factura factura) {

        if (factura.getEstado() == EstadoFactura.CANCELADA) {
            throw new IllegalStateException(
                    "No se puede confirmar una factura cancelada."
            );
        }

        // Descontar productos del inventario
        for (DetalleFactura detalle : factura.getDetalles()) {

            Producto producto = detalle.getProducto();

            producto.disminuirStock(
                    detalle.getCantidad()
            );
        }

        factura.pagar();
    }

    // =========================
    // FACTURAS POR CLIENTE
    // =========================

    public List<Factura> facturasDeCliente(String documento) {

        List<Factura> resultado = new ArrayList<>();

        for (Factura factura : facturas) {

            if (factura.getCliente()
                    .getDocumento()
                    .equals(documento)) {

                resultado.add(factura);
            }
        }

        return resultado;
    }

    // =========================
    // TOTAL VENDIDO EN UNA FECHA
    // =========================

    public double totalVendidoEnFecha(LocalDate fecha) {

        double total = 0;

        for (Factura factura : facturas) {

            if (factura.getFecha().equals(fecha)
                    && factura.getEstado() == EstadoFactura.PAGADA) {

                total += factura.calcularTotal();
            }
        }

        return total;
    }

    // =========================
    // PRODUCTOS CON BAJO INVENTARIO
    // =========================

    public List<Producto> productosBajoInventario(int limite) {

        List<Producto> resultado = new ArrayList<>();

        for (Producto producto : productos) {

            if (producto.getCantidadDisponible() <= limite) {
                resultado.add(producto);
            }
        }

        return resultado;
    }

    // =========================
    // REPORTE DE VENTAS
    // =========================

    public void generarReporteVentas() {

        System.out.println("===== REPORTE DE VENTAS =====");

        double total = 0;

        for (Factura factura : facturas) {

            if (factura.getEstado() == EstadoFactura.PAGADA) {

                System.out.println(
                        "Factura: " + factura.getCodigo()
                );

                System.out.println(
                        "Cliente: " +
                                factura.getCliente().getNombre()
                );

                System.out.println(
                        "Fecha: " + factura.getFecha()
                );

                System.out.println(
                        "Total: $" + factura.calcularTotal()
                );

                System.out.println("----------------------------");

                total += factura.calcularTotal();
            }
        }

        System.out.println(
                "TOTAL VENDIDO: $" + total
        );
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }
}