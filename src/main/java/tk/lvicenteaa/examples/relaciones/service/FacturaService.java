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
        return ResponseEntity.ok(facturas);
        /*
        List<Factura> facturas = this.facturaRepository.findAll();
        if(facturas == null)
            return ResponseEntity.noContent().build();
        else{
            List<FacturaDTO> nuevaLista = new ArrayList<>();
            for(Factura fact: facturas){
                nuevaLista.add(this.convertirADTO(fact));
            }
            return ResponseEntity.ok(nuevaLista);
        }

         */
    }

    public ResponseEntity<Factura> buscarFactura(Long id){
        Optional<Factura> optFactura = this.facturaRepository.findById(id);
        if(optFactura.isPresent()){
            return ResponseEntity.ok(optFactura.get());
        }
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
        Factura facturaOpt = optionalFactura.get();
        facturaOpt.setCliente(factura.getCliente());
        facturaOpt.setNumeroFactura(factura.getNumeroFactura());
        Factura result = this.facturaRepository.save(facturaOpt);
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

    /*
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

        if (factura.getDetalles() != null){
            List<DetalleFacturaDTO> detallesDto = new ArrayList<>();
            for(DetalleFactura detalle : factura.getDetalles()){
                DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO();
                detalleFacturaDTO.setFactura(detalle.getFactura());
                detalleFacturaDTO.setCantidad(detalle.getCantidad());
                detalleFacturaDTO.setId(detalle.getId());
                detalleFacturaDTO.setProducto(detalle.getProducto());
                detalleFacturaDTO.setPrecio(detalle.getPrecio());

                detallesDto.add(detalleFacturaDTO);
            }

            facturaDTO.setDetalles(detallesDto);
        }
        return facturaDTO;
    }
    */

}
