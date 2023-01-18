package tk.lvicenteaa.examples.relaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.lvicenteaa.examples.relaciones.entities.DetalleFactura;
import tk.lvicenteaa.examples.relaciones.entities.Factura;

import java.util.List;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

    public List<DetalleFactura> findByProducto(String producto);

    public List<DetalleFactura> findByFactura(Factura factura);


}
