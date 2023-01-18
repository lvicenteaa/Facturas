package tk.lvicenteaa.examples.relaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tk.lvicenteaa.examples.relaciones.dto.DetalleFacturaDTO;
import tk.lvicenteaa.examples.relaciones.entities.DetalleFactura;
import tk.lvicenteaa.examples.relaciones.entities.Factura;
import tk.lvicenteaa.examples.relaciones.repository.DetalleFacturaRepository;
import tk.lvicenteaa.examples.relaciones.repository.FacturaRepository;
import tk.lvicenteaa.examples.relaciones.util.DetalleFacturaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    public ResponseEntity<List<DetalleFacturaDTO>> mostrarTodos(){
        List<DetalleFactura> detallesFacturas = this.detalleFacturaRepository.findAll();
        if(detallesFacturas.isEmpty())
            return ResponseEntity.noContent().build();
        List<DetalleFacturaDTO> detalleFacturasDTO = new ArrayList<>();
        for(DetalleFactura det : detallesFacturas) {
            DetalleFacturaDTO detalleDTO = this.convertirADTO(det);
            detalleFacturasDTO.add(detalleDTO);
        }
        return ResponseEntity.ok(detalleFacturasDTO);
    }

    public ResponseEntity<DetalleFacturaDTO> buscar(Long id){
        Optional<DetalleFactura> detalleFacturaOptional = this.detalleFacturaRepository.findById(id);
        if(detalleFacturaOptional.isPresent()){
            DetalleFactura detalleFactura = detalleFacturaOptional.get();
            DetalleFacturaDTO detalleFacturaDTO = this.convertirADTO(detalleFactura);
            return ResponseEntity.ok(detalleFacturaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<DetalleFacturaDTO> crear(DetalleFacturaDTO detalleFacturaDTO){
        DetalleFactura detalleFactura = this.convertirDeDTO(detalleFacturaDTO);
        if(detalleFactura.getId() != null)
            return ResponseEntity.badRequest().build();
        Optional<Factura> optionalFactura= this.facturaRepository.findById(detalleFactura.getFactura().getId());
        if(optionalFactura.isEmpty())
            return ResponseEntity.notFound().build();
        detalleFactura.setFactura(optionalFactura.get());
        DetalleFactura result = this.detalleFacturaRepository.save(detalleFactura);
        DetalleFacturaDTO detalleFacturaDTO1 = this.convertirADTO(result);
        return ResponseEntity.ok(detalleFacturaDTO1);
    }

    public ResponseEntity<DetalleFacturaDTO> actualizar(DetalleFacturaDTO detalleFacturaDTO){
        DetalleFactura detalleFactura = this.convertirDeDTO(detalleFacturaDTO);
        if(detalleFactura.getId() == null)
            return ResponseEntity.badRequest().build();
        Optional<DetalleFactura> detalleFactura1 = this.detalleFacturaRepository.findById(detalleFactura.getId());
        if(!detalleFactura1.isPresent())
            return ResponseEntity.notFound().build();

        DetalleFactura detalleFactura2 = detalleFactura1.get();
        detalleFactura2.setCantidad(detalleFactura.getCantidad());
        detalleFactura2.setPrecio(detalleFactura.getPrecio());

        DetalleFactura result = this.detalleFacturaRepository.save(detalleFactura2);
        DetalleFacturaDTO resultDTO = this.convertirADTO(result);
        return ResponseEntity.ok(resultDTO);
    }

    public ResponseEntity<DetalleFacturaDTO> borrar(Long id){
        if(!this.detalleFacturaRepository.existsById(id))
            return ResponseEntity.notFound().build();
        this.detalleFacturaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<DetalleFacturaDTO> borrarTodos(){
        this.detalleFacturaRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    private DetalleFacturaDTO convertirADTO(DetalleFactura detalleFactura){
        DetalleFacturaDTO facturaDTO = new DetalleFacturaDTO();
        facturaDTO.setFactura(detalleFactura.getFactura());
        facturaDTO.setCantidad(detalleFactura.getCantidad());
        facturaDTO.setId(detalleFactura.getId());
        facturaDTO.setProducto(detalleFactura.getProducto());
        facturaDTO.setPrecio(detalleFactura.getPrecio());

        return facturaDTO;
    }

    private DetalleFactura convertirDeDTO(DetalleFacturaDTO detalleFacturaDTO){
        DetalleFactura factura = new DetalleFactura();
        factura.setFactura(detalleFacturaDTO.getFactura());
        factura.setCantidad(detalleFacturaDTO.getCantidad());
        factura.setId(detalleFacturaDTO.getId());
        factura.setProducto(detalleFacturaDTO.getProducto());
        factura.setPrecio(detalleFacturaDTO.getPrecio());

        return factura;
    }

}
