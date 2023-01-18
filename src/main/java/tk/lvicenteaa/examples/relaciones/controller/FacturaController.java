package tk.lvicenteaa.examples.relaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.lvicenteaa.examples.relaciones.dto.FacturaDTO;
import tk.lvicenteaa.examples.relaciones.entities.Factura;
import tk.lvicenteaa.examples.relaciones.service.FacturaService;

import java.util.List;

@RestController
@RequestMapping("api/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> mostrarTodas(){
        return this.facturaService.mostrarFacturas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> buscarPorId(@PathVariable Long id){
        return this.facturaService.buscarFactura(id);
    }

    @PostMapping
    public ResponseEntity<FacturaDTO> crear(@RequestBody FacturaDTO factura){
        return this.facturaService.crearFactura(factura);
    }

    @PutMapping
    public ResponseEntity<FacturaDTO> actualizar(@RequestBody FacturaDTO factura){
        return this.facturaService.actualizar(factura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FacturaDTO> borrarPorId(@PathVariable Long id){
        return this.facturaService.borrar(id);
    }

    @DeleteMapping
    public ResponseEntity<FacturaDTO> borrarTodas(){
        return this.facturaService.borrarTodos();
    }

}
