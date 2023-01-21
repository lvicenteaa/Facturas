package tk.lvicenteaa.examples.relaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.lvicenteaa.examples.relaciones.entities.Factura;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

    public List<Factura> findByCliente(String cliente);

    //public List<Factura> findByFecha(LocalDateTime fecha);

}
