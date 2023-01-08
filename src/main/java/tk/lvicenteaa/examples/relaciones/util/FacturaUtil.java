package tk.lvicenteaa.examples.relaciones.util;

import tk.lvicenteaa.examples.relaciones.entities.DetalleFactura;
import tk.lvicenteaa.examples.relaciones.entities.Factura;

public class FacturaUtil {

    public static Factura calcularPrecioTotal(Factura factura){
        Double precioTotal = 0.0;
        for(DetalleFactura detalleFactura: factura.getDetalles()){
            precioTotal += detalleFactura.getPrecio() * detalleFactura.getCantidad();
        }
        factura.setCostoTotal(precioTotal);
        return factura;
    }


}
