package tk.lvicenteaa.examples.relaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.lvicenteaa.examples.relaciones.dto.DetalleFacturaDTO;
import tk.lvicenteaa.examples.relaciones.entities.DetalleFactura;
import tk.lvicenteaa.examples.relaciones.service.DetalleFacturaService;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @GetMapping
    public ResponseEntity<List<DetalleFacturaDTO>> mostrarTodos(){
        return this.detalleFacturaService.mostrarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFacturaDTO> buscarPorId(@PathVariable Long id){
        return this.detalleFacturaService.buscar(id);
    }

    @PostMapping
    public ResponseEntity<DetalleFacturaDTO> crear(@RequestBody DetalleFacturaDTO detalleFactura){
        return this.detalleFacturaService.crear(detalleFactura);
    }

    @PutMapping
    public ResponseEntity<DetalleFacturaDTO> actualizar(@RequestBody DetalleFacturaDTO detalleFactura){
        return this.detalleFacturaService.actualizar(detalleFactura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetalleFacturaDTO> borrarPorId(@PathVariable Long id){
        return this.detalleFacturaService.borrar(id);
    }

    @DeleteMapping
    public ResponseEntity<DetalleFacturaDTO> borrarTodos(){
        return this.detalleFacturaService.borrarTodos();
    }

}
