package tk.lvicenteaa.examples.relaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.lvicenteaa.examples.relaciones.entities.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
