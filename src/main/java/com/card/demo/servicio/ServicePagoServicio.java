package com.card.demo.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.card.demo.entity.PagoServicio;

@Service
public interface ServicePagoServicio {

    public List<PagoServicio> findAll();
}
