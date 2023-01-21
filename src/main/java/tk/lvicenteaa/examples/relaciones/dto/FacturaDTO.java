package tk.lvicenteaa.examples.relaciones.dto;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class FacturaDTO implements Serializable {



    private Long id;
    private String numeroFactura;
    private String cliente;
    private Double costoTotal;
    private LocalDateTime fecha;
    private List<DetalleFacturaDTO> detalles;

    public FacturaDTO() {
    }

    public FacturaDTO(Long id) {
        this.id = id;
    }

    public FacturaDTO(Long id, String numeroFactura, String cliente, Double costoTotal, LocalDateTime fecha, List<DetalleFacturaDTO> detalles) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.costoTotal = costoTotal;
        this.fecha = fecha;
        this.detalles = detalles;
    }

    public FacturaDTO(Long id, String numeroFactura, String cliente, Double costoTotal, LocalDateTime fecha) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.costoTotal = costoTotal;
        this.fecha = fecha;
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
        if (this.detalles == null){
            return 0.0;
        }
        Double costo = 0.0;
        for(DetalleFacturaDTO detalle: this.getDetalles()){
            costo += detalle.getPrecio() * detalle.getCantidad();
        }
        return costo;
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

    public List<DetalleFacturaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFacturaDTO> detalles) {
        this.detalles = detalles;
    }
}
