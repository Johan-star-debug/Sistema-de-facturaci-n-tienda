package co.edu.uniquindio.poo.model;

public class Producto {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadDisponible;
    private Categoria categoria;

    public Producto(String codigo, String nombre, String descripcion,
                    double precio, int cantidadDisponible,
                    Categoria categoria) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.categoria = categoria;
    }

    public boolean hayStock(int cantidad) {
        return cantidadDisponible >= cantidad;
    }

    public void disminuirStock(int cantidad) {
        if (cantidad > cantidadDisponible) {
            throw new IllegalArgumentException(
                    "No hay suficiente inventario para el producto: " + nombre
            );
        }

        cantidadDisponible -= cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidadDisponible=" + cantidadDisponible +
                ", categoria=" + categoria +
                '}';
    }
}