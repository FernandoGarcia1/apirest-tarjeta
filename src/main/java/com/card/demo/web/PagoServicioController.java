package com.card.demo.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.card.demo.entity.Domiciliacion;
import com.card.demo.entity.PagoServicio;
import com.card.demo.servicio.ServiceDomiciliacion;
import com.card.demo.servicio.ServicePagoServicio;

@RestController
public class PagoServicioController {

    @Autowired
    private ServiceDomiciliacion domiciliacionService;
    @Autowired
    private ServicePagoServicio pagoServicioService;

    // MUestra todas las domiciliaciones
    @GetMapping("view/all/domiciliaciones")
    public ResponseEntity<?> getDomiciliaciones() {
        List<Domiciliacion> domiciliaciones = domiciliacionService.findAll();

        if (domiciliaciones.size() == 0) {
            return new ResponseEntity<>("Recurso no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(domiciliacionService.findAll(), HttpStatus.OK);
    }

    // MUestra todas las domiciliaciones activas por cliente
    @GetMapping("view/domiciliaciones")
    public ResponseEntity<?> domiciliacionesByTarjeta(@RequestParam(value = "t") Long idTarjeta) {
        // muestra las domiciliaciones activas filtradas por tarjeta
        List<Domiciliacion> domiciliaciones = domiciliacionService.activeByTarjeta(idTarjeta);

        if (domiciliaciones.size() == 0) {
            return new ResponseEntity<>("No tiene domiciliaciones activas en estos momentos.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(domiciliaciones, HttpStatus.OK);
    }

    // Muestra todos los pagoServicio
    @GetMapping("view/all/pagosservicios")
    public ResponseEntity<?> getPagosServicio() {
        List<PagoServicio> pagoServicio = pagoServicioService.findAll();
        if (pagoServicio.size() == 0) {
            return new ResponseEntity<>("No tiene domiciliaciones activas en estos momentos.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pagoServicio, HttpStatus.OK);
    }

    @GetMapping("view/pagosservicios")
    // muestras los pagos de servicios filtradas por tarjeta
    public ResponseEntity<?> findPagosServicioByTarjetaId(@RequestParam(value = "t") int idTarjeta) {
        List<PagoServicio> pagoServicio = pagoServicioService.findbyTarjeta(idTarjeta);
        if (pagoServicio.size() == 0) {
            return new ResponseEntity<>("Aun no has realizado ningun pago de servicios.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pagoServicio, HttpStatus.OK);
    }

    @GetMapping("view/pagosservicios/mes")
    public ResponseEntity<?> findPagosServicioByMes(@RequestParam(value = "fecha") String d,
            @RequestParam(value = "t") int idTarjeta) {

        LocalDate date = LocalDate.parse(d);
        List<PagoServicio> pagoServicio = pagoServicioService.findBetweenDates(1, date);
        if (pagoServicio.size() == 0) {
            return new ResponseEntity<>("No tienes pagos de servicios registrados en este mes.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pagoServicio, HttpStatus.OK);
    }

    @PostMapping("/pagarservicio")
    public ResponseEntity<?> payService(@RequestBody PagoServicio pagoServicio) {
        // Un pago de servicio no se puede domiciliar
        if (pagoServicioService.createPagoServicio(pagoServicio)) {
            return new ResponseEntity<>("Pago de servicio realizado.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    "El pago de servicio no se pudo concretar. Favor de revisar su saldo disponible",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/domiciliarpago")
    public ResponseEntity<?> createDomiciliacion(@RequestBody Domiciliacion domiciliar) {
        if (domiciliacionService.createDomiciliacion(domiciliar)) {
            return new ResponseEntity<>("Domiciliacion creada. Y cobro realizado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("La domiciliacion no se ha podido concretar.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cancelardomiciliacion/{id}")
    public ResponseEntity<?> cancelDomiciliacion(@PathVariable long id) {
        if (domiciliacionService.cancelDomiciliacion(id)) {
            return new ResponseEntity<>("Domiciliacion cancelada.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ha ocurrido un fallo al cancelar.", HttpStatus.BAD_REQUEST);
        }
    }
}
