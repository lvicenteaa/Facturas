package tk.lvicenteaa.examples.relaciones.dto;

import tk.lvicenteaa.examples.relaciones.entities.Factura;

import java.io.Serializable;

public class DetalleFacturaDTO implements Serializable {

    private Long id;
    private String producto;
    private Integer cantidad;
    private Double precio;

    private Factura factura;

    public DetalleFacturaDTO(Long id) {
        this.id = id;
    }

    public DetalleFacturaDTO() {
    }

    public DetalleFacturaDTO(Long id, String producto, Integer cantidad, Double precio, Factura factura) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.factura = factura;
    }

    public DetalleFacturaDTO(Long id, String producto, Integer cantidad, Double precio) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
