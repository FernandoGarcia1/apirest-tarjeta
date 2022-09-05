package com.card.demo.servicio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.demo.dao.PagoServicioDao;
import com.card.demo.entity.PagoServicio;

@Service
public class ServicePagoServiceImp implements ServicePagoServicio {

    @Autowired
    PagoServicioDao pagoServicioDao;

    @Override
    public List<PagoServicio> findAll() {
        // TODO Auto-generated method stub
        return (List<PagoServicio>) pagoServicioDao.findAll();
    }

    @Override
    public List<PagoServicio> findbyTarjeta(int idTarjeta) {
        return (List<PagoServicio>) pagoServicioDao.findPagosServicioByTarjetaId(idTarjeta);
    }

    @Override
    public List<PagoServicio> findBetweenDates(int idTarjeta, LocalDate date) {
        // TODO Auto-generated method stub

        LocalDate startDate = date.withDayOfMonth(1);
        LocalDate endDate = date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
        return (List<PagoServicio>) pagoServicioDao.findByTarjetaIdandDates(startDate, endDate, idTarjeta);
    }

}
