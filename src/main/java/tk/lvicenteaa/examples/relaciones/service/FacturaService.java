package tk.lvicenteaa.examples.relaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tk.lvicenteaa.examples.relaciones.dto.FacturaDTO;
import tk.lvicenteaa.examples.relaciones.entities.Factura;
import tk.lvicenteaa.examples.relaciones.repository.FacturaRepository;
import tk.lvicenteaa.examples.relaciones.util.FacturaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public ResponseEntity<List<FacturaDTO>> mostrarFacturas(){
        List<Factura> facturas = this.facturaRepository.findAll();
        if(facturas.isEmpty())
            return ResponseEntity.noContent().build();
        else{
            List<FacturaDTO> nuevaLista = new ArrayList<>();
            for(Factura fact: facturas){
                nuevaLista.add(this.convertirADTO(fact));
            }
            List<FacturaDTO> facturaDTO = nuevaLista;
            return ResponseEntity.ok(facturaDTO);
        }
    }

    public ResponseEntity<FacturaDTO> buscarFactura(Long id){
        Optional<Factura> optFactura = this.facturaRepository.findById(id);
        if(optFactura.isPresent()){
            FacturaDTO factura = this.convertirADTO(optFactura.get());
            return ResponseEntity.ok(factura);
        }
        else
            return ResponseEntity.notFound().build();
    }

    public ResponseEntity<FacturaDTO> crearFactura(FacturaDTO facturaDTO){
        Factura factura = this.convertirDeDTO(facturaDTO);
        if(factura.getId() != null)
            return ResponseEntity.badRequest().build();
        Factura result = this.facturaRepository.save(factura);
        FacturaDTO resultDTO = this.convertirADTO(result);
        return ResponseEntity.ok(resultDTO);
    }

    public ResponseEntity<FacturaDTO> actualizar(FacturaDTO facturaDTO){
        Factura factura = this.convertirDeDTO(facturaDTO);
        if(factura.getId() == null)
            return ResponseEntity.badRequest().build();
        Optional<Factura> optionalFactura = this.facturaRepository.findById(factura.getId());
        if(!optionalFactura.isPresent())
            return ResponseEntity.notFound().build();
        Factura facturaOpt = optionalFactura.get();
        Factura result = this.facturaRepository.save(facturaOpt);
        FacturaDTO facturaDTO1 = this.convertirADTO(result);
        return ResponseEntity.ok(facturaDTO1);
    }

    public ResponseEntity<FacturaDTO> borrar(Long id){
        if(!this.facturaRepository.existsById(id))
            return ResponseEntity.notFound().build();
        this.facturaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<FacturaDTO> borrarTodos(){
        this.facturaRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    private Factura convertirDeDTO(FacturaDTO facturaDTO){
        Factura factura = new Factura();
        factura.setCliente(facturaDTO.getCliente());
        factura.setNumeroFactura(facturaDTO.getNumeroFactura());
        factura.setFecha(facturaDTO.getFecha());
        //factura.setId(facturaDTO.getId());
        //factura.setDetalles(facturaDTO.getDetalles());
        return factura;
    }

    private FacturaDTO convertirADTO(Factura factura){
        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setCliente(factura.getCliente());
        facturaDTO.setNumeroFactura(factura.getNumeroFactura());
        facturaDTO.setFecha(factura.getFecha());
        facturaDTO.setId(factura.getId());
        //factura.setDetalles(facturaDTO.getDetalles());
        return facturaDTO;
    }

}
