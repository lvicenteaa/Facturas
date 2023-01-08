package tk.lvicenteaa.examples.relaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tk.lvicenteaa.examples.relaciones.entities.DetalleFactura;
import tk.lvicenteaa.examples.relaciones.entities.Factura;
import tk.lvicenteaa.examples.relaciones.repository.DetalleFacturaRepository;
import tk.lvicenteaa.examples.relaciones.repository.FacturaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    public ResponseEntity<List<DetalleFactura>> mostrarTodos(){
        List<DetalleFactura> detallesFacturas = this.detalleFacturaRepository.findAll();
        if(detallesFacturas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(detallesFacturas);
    }

    public ResponseEntity<DetalleFactura> buscar(Long id){
        Optional<DetalleFactura> detalleFacturaOptional = this.detalleFacturaRepository.findById(id);
        if(detalleFacturaOptional.isPresent())
            return ResponseEntity.ok(detalleFacturaOptional.get());
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<DetalleFactura> crear(DetalleFactura detalleFactura){
        if(detalleFactura.getId() != null)
            return ResponseEntity.badRequest().build();
        Optional<Factura> optionalFactura= this.facturaRepository.findById(detalleFactura.getFactura().getId());
        if(optionalFactura.isEmpty())
            return ResponseEntity.notFound().build();
        detalleFactura.setFactura(optionalFactura.get());
        DetalleFactura result = this.detalleFacturaRepository.save(detalleFactura);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<DetalleFactura> actualizar(DetalleFactura detalleFactura){
        if(detalleFactura.getId() == null)
            return ResponseEntity.badRequest().build();
        Optional<DetalleFactura> detalleFactura1 = this.detalleFacturaRepository.findById(detalleFactura.getId());
        if(!detalleFactura1.isPresent())
            return ResponseEntity.notFound().build();
        DetalleFactura detalleFactura2 = detalleFactura1.get();
        detalleFactura2.setCantidad(detalleFactura.getCantidad());
        detalleFactura2.setPrecio(detalleFactura.getPrecio());

        DetalleFactura result = this.detalleFacturaRepository.save(detalleFactura2);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<DetalleFactura> borrar(Long id){
        if(!this.detalleFacturaRepository.existsById(id))
            return ResponseEntity.notFound().build();
        this.detalleFacturaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<DetalleFactura> borrarTodos(){
        this.detalleFacturaRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
