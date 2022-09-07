package com.card.demo.servicio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.card.demo.entity.PagoServicio;
import com.card.demo.entity.Tarjeta;

@Service
public interface ServicePagoServicio {

    public List<PagoServicio> findAll();

    public List<PagoServicio> findbyTarjeta(int idTarjeta);

    public List<PagoServicio> findBetweenDates(int idTarjeta, LocalDate date);

    public boolean saldoSuficiente(Tarjeta tarjeta, double monto_pago);

    public boolean createPagoServicio(PagoServicio p);

}
