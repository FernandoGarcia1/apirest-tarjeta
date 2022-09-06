package com.card.demo.servicio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.card.demo.entity.PagoServicio;

@Service
public interface ServicePagoServicio {

    public List<PagoServicio> findAll();

    public List<PagoServicio> findbyTarjeta(int idTarjeta);

    public List<PagoServicio> findBetweenDates(int idTarjeta, LocalDate date);

    public boolean createPagoServicio(PagoServicio p);
}
