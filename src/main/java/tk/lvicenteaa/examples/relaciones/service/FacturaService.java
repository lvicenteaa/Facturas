package tk.lvicenteaa.examples.relaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tk.lvicenteaa.examples.relaciones.entities.Factura;
import tk.lvicenteaa.examples.relaciones.repository.FacturaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public ResponseEntity<List<Factura>> mostrarFacturas(){
        List<Factura> facturas = this.facturaRepository.findAll();
        if(facturas.isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(facturas);
    }

    public ResponseEntity<Factura> buscarFactura(Long id){
        Optional<Factura> optFactura = this.facturaRepository.findById(id);
        if(optFactura.isPresent())
            return ResponseEntity.ok(optFactura.get());
        else
            return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Factura> crearFactura(Factura factura){
        if(factura.getId() != null)
            return ResponseEntity.badRequest().build();
        Factura result = this.facturaRepository.save(factura);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Factura> actualizar(Factura factura){
        if(factura.getId() == null)
            return ResponseEntity.badRequest().build();
        Optional<Factura> optionalFactura = this.facturaRepository.findById(factura.getId());
        if(!optionalFactura.isPresent())
            return ResponseEntity.notFound().build();
        Factura factura1 = optionalFactura.get();
        factura1.setCliente(factura.getCliente());
        factura1.setNumeroFactura(factura.getNumeroFactura());
        factura1.setFecha(factura.getFecha());
        Factura result = this.facturaRepository.save(factura1);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Factura> borrar(Long id){
        if(!this.facturaRepository.existsById(id))
            return ResponseEntity.notFound().build();
        this.facturaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Factura> borrarTodos(){
        this.facturaRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
