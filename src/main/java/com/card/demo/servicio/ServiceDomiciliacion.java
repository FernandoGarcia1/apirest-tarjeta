package com.card.demo.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.card.demo.entity.Domiciliacion;

@Service
public interface ServiceDomiciliacion {
    public List<Domiciliacion> findAll();
}