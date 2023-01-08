package tk.lvicenteaa.examples.relaciones.util;

import tk.lvicenteaa.examples.relaciones.entities.DetalleFactura;

public class DetalleFacturaUtil {

    public static DetalleFactura calcularPrecio(DetalleFactura detalleFactura){
        Double precio = detalleFactura.getPrecio() * detalleFactura.getCantidad();
        detalleFactura.setPrecioTotal(precio);
        return detalleFactura;
    }

}
