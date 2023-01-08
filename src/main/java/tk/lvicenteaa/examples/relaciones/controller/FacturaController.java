package tk.lvicenteaa.examples.relaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.lvicenteaa.examples.relaciones.entities.Factura;
import tk.lvicenteaa.examples.relaciones.service.FacturaService;

import java.util.List;

@RestController
@RequestMapping("api/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<Factura>> mostrarTodas(){
        return this.facturaService.mostrarFacturas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> buscarPorId(@PathVariable Long id){
        return this.facturaService.buscarFactura(id);
    }

    @PostMapping
    public ResponseEntity<Factura> crear(@RequestBody Factura factura){
        return this.facturaService.crearFactura(factura);
    }

    @PutMapping
    public ResponseEntity<Factura> actualizar(@RequestBody Factura factura){
        return this.facturaService.actualizar(factura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Factura> borrarPorId(@PathVariable Long id){
        return this.facturaService.borrar(id);
    }

    @DeleteMapping
    public ResponseEntity<Factura> borrarTodas(){
        return this.facturaService.borrarTodos();
    }

}
