package tk.lvicenteaa.examples.relaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.lvicenteaa.examples.relaciones.entities.DetalleFactura;
import tk.lvicenteaa.examples.relaciones.service.DetalleFacturaService;

import java.util.List;

@RestController
@RequestMapping("/api/detalle")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @GetMapping
    public ResponseEntity<List<DetalleFactura>> mostrarTodos(){
        return this.detalleFacturaService.mostrarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> buscarPorId(@PathVariable Long id){
        return this.detalleFacturaService.buscar(id);
    }

    @PostMapping
    public ResponseEntity<DetalleFactura> crear(@RequestBody DetalleFactura detalleFactura){
        return this.detalleFacturaService.crear(detalleFactura);
    }

    @PutMapping
    public ResponseEntity<DetalleFactura> actualizar(@RequestBody DetalleFactura detalleFactura){
        return this.detalleFacturaService.actualizar(detalleFactura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetalleFactura> borrarPorId(@PathVariable Long id){
        return this.detalleFacturaService.borrar(id);
    }

    @DeleteMapping
    public ResponseEntity<DetalleFactura> borrarTodos(){
        return this.detalleFacturaService.borrarTodos();
    }

}
