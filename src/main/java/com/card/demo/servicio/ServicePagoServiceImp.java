package com.card.demo.servicio;

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

}
