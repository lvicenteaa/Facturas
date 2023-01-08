package tk.lvicenteaa.examples.relaciones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroFactura;
    private String cliente;

    @Transient
    private Double costoTotal;
    private LocalDateTime fecha;

    @JsonIgnore
    @OneToMany(mappedBy = "factura")
    private List<DetalleFactura> detalles;


    public Factura(Long id) {
        this.id = id;
    }

    public Factura() {
    }

    public Factura(Long id, String numeroFactura, String cliente, Double costoTotal, LocalDateTime fecha) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.costoTotal = costoTotal;
        this.fecha = fecha;
    }

    public Factura(Long id, String numeroFactura, String cliente, Double costoTotal, LocalDateTime fecha, List<DetalleFactura> detalles) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.costoTotal = costoTotal;
        this.fecha = fecha;
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }
}
