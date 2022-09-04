package com.card.demo.web;

import com.card.demo.entity.Transferencia;
import com.card.demo.servicio.ServiceTransferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.net.http.HttpRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {
    @Autowired
    ServiceTransferencia serviceTransferencia;

    @GetMapping("/{id}")
    public ResponseEntity<?> verTransferencia (@PathVariable long id){
        //Verifica si la transferencia existe mediante su ID
        if (serviceTransferencia.obtenerTransferencia(id)==null)
            return new ResponseEntity<>("Recurso no encontrado",HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(serviceTransferencia.obtenerTransferencia(id),HttpStatus.OK);
    }
    @GetMapping("/recibidas") // recibidas?cuenta=numtarjeta
    public ResponseEntity<?>recibidas(@RequestParam (value = "cuenta") String cuenta){
        ArrayList<Transferencia> recibidas = serviceTransferencia.transferenciasRecibidas(cuenta);
        if (recibidas != null)
            return new ResponseEntity<>(recibidas,HttpStatus.OK);
        else return new ResponseEntity<>("Recurso no encontrado",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/realizadas") // realizadas?cuenta=numtarjeta
    public ResponseEntity<?>realizadas(@RequestParam (value = "cuenta") String cuenta){
        ArrayList<Transferencia> realizadas = serviceTransferencia.transferenciasRealizadas(cuenta);
        if (realizadas != null)
            return new ResponseEntity<>(realizadas,HttpStatus.OK);
        else return new ResponseEntity<>("Recurso no encontrado",HttpStatus.NOT_FOUND);
    }
    @PostMapping("/Transferir")
    public ResponseEntity<?> hacerTransferencia (@RequestBody Transferencia transferencia){
        //Comprobando si la tarjeta receptora existe y si cuenta con el saldo suficientepara transferir
        if (serviceTransferencia.existeTarjeta(transferencia) && serviceTransferencia.saldoSuficiente(transferencia))
            return new ResponseEntity<>(serviceTransferencia.postTransferencia(transferencia), HttpStatus.CREATED);
        else return new ResponseEntity<>("Operacion no realizada", HttpStatus.UNAUTHORIZED);
    }
}
