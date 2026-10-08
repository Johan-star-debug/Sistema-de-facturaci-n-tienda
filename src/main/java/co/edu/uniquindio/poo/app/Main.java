package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.service.Tienda;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Tienda tienda = new Tienda();

        // =====================================
        // CREAR CLIENTES
        // =====================================

        Cliente cliente1 = new Cliente(
                "1001",
                "Juan Perez",
                "3001234567",
                "juan@gmail.com",
                "La Tebaida"
        );

        Cliente cliente2 = new Cliente(
                "1002",
                "Maria Gomez",
                "3019876543",
                "maria@gmail.com",
                "Armenia"
        );

        tienda.agregarCliente(cliente1);
        tienda.agregarCliente(cliente2);


        // =====================================
        // CREAR PRODUCTOS
        // =====================================

        Producto computador = new Producto(
                "P001",
                "Computador Lenovo",
                "Computador portátil Lenovo",
                2500000,
                10,
                Categoria.COMPUTADORES
        );

        Producto celular = new Producto(
                "P002",
                "Samsung Galaxy",
                "Celular Samsung",
                1500000,
                8,
                Categoria.CELULARES
        );

        Producto mouse = new Producto(
                "P003",
                "Mouse Logitech",
                "Mouse inalámbrico",
                80000,
                3,
                Categoria.ACCESORIOS
        );

        tienda.agregarProducto(computador);
        tienda.agregarProducto(celular);
        tienda.agregarProducto(mouse);


        // =====================================
        // CREAR FACTURA
        // =====================================

        Factura factura1 = new Factura(
                "F001",
                LocalDate.now(),
                cliente1,
                MetodoPago.TARJETA_CREDITO
        );


        // =====================================
        // AGREGAR PRODUCTOS
        // =====================================

        factura1.agregarProducto(
                computador,
                1
        );

        factura1.agregarProducto(
                mouse,
                2
        );


        // =====================================
        // MOSTRAR TOTAL
        // =====================================

        System.out.println(
                "Total factura: $" +
                        factura1.calcularTotal()
        );


        // =====================================
        // REGISTRAR FACTURA
        // =====================================

        tienda.registrarFactura(factura1);


        // =====================================
        // CONFIRMAR VENTA
        // =====================================

        tienda.confirmarVenta(factura1);


        // =====================================
        // MOSTRAR FACTURA
        // =====================================

        System.out.println();
        System.out.println(factura1);


        // =====================================
        // MOSTRAR INVENTARIO
        // =====================================

        System.out.println();
        System.out.println("===== INVENTARIO =====");

        for (Producto producto : tienda.getProductos()) {
            System.out.println(
                    producto.getNombre() +
                            " | Stock: " +
                            producto.getCantidadDisponible()
            );
        }


        // =====================================
        // FACTURAS DEL CLIENTE
        // =====================================

        System.out.println();
        System.out.println("===== FACTURAS DE JUAN =====");

        for (Factura factura :
                tienda.facturasDeCliente("1001")) {

            System.out.println(factura);
        }


        // =====================================
        // TOTAL VENDIDO HOY
        // =====================================

        double totalHoy =
                tienda.totalVendidoEnFecha(
                        LocalDate.now()
                );

        System.out.println();
        System.out.println(
                "Total vendido hoy: $" + totalHoy
        );


        // =====================================
        // PRODUCTOS CON BAJO INVENTARIO
        // =====================================

        System.out.println();
        System.out.println(
                "===== PRODUCTOS CON BAJO INVENTARIO ====="
        );

        for (Producto producto :
                tienda.productosBajoInventario(3)) {

            System.out.println(
                    producto.getNombre() +
                            " | Stock: " +
                            producto.getCantidadDisponible()
            );
        }


        // =====================================
        // REPORTE
        // =====================================

        System.out.println();

        tienda.generarReporteVentas();
    }
}