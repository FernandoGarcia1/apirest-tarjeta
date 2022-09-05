package com.card.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.demo.dao.DomiciliacionDao;
import com.card.demo.entity.Domiciliacion;

@Service
public class ServiceDomiciliacionImp implements ServiceDomiciliacion {

    @Autowired
    private DomiciliacionDao domiciliacionDao;

    @Override
    public List<Domiciliacion> findAll() {
        // TODO Auto-generated method stub
        return (List<Domiciliacion>) domiciliacionDao.findAll();
    }

    @Override
    public List<Domiciliacion> activeByTarjeta(Long idTarjeta) {
        return (List<Domiciliacion>) domiciliacionDao.activeByTarjeta(idTarjeta);
    }

}
