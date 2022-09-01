package com.card.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.card.demo.entity.Domiciliacion;
import com.card.demo.servicio.ServiceDomiciliacion;

@RestController
public class PagoServicioController {

    @Autowired
    private ServiceDomiciliacion domiciliacionService;

    @GetMapping("view/domiciliaciones")
    public List<Domiciliacion> getDomiciliaciones() {
        System.out.println("entra");
        return domiciliacionService.findAll();
    }
}
