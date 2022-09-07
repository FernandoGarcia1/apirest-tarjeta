package com.card.demo.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.card.demo.entity.Domiciliacion;

@Service
public interface ServiceDomiciliacion {
    public List<Domiciliacion> findAll();

    public List<Domiciliacion> activeByTarjeta(Long idTarjeta);

    public boolean createDomiciliacion(Domiciliacion d);

    public boolean cancelDomiciliacion(long d); // cancelar domiciliacion
}
