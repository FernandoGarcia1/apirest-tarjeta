package com.card.demo.servicio;

import com.card.demo.dao.TarjetaDao;
import com.card.demo.dao.TransferenciaDao;
import com.card.demo.entity.Tarjeta;
import com.card.demo.entity.Transferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiceTransferencia {
    @Autowired
    TransferenciaDao transferenciaDao;
    @Autowired
    TarjetaDao tarjetaDao;

    public Transferencia obtenerTransferencia (long id){
        return transferenciaDao.findById(id).orElse(null);
    }

    public ArrayList<Transferencia> transferenciasRealizadas (String emisor){
        return (ArrayList<Transferencia>) transferenciaDao.findTransferenciaByEmisor(emisor);
    }
    public ArrayList<Transferencia> transferenciasRecibidas (String receptor){
        return (ArrayList<Transferencia>) transferenciaDao.findTransferenciaByReceptor(receptor);
    }
    public boolean existeTarjeta (Transferencia transferencia){
        if (tarjetaDao.existsTarjetaByNumTarjeta(transferencia.getReceptor().getNumTarjeta()))
        return true;
        else return false;
    }
    public boolean saldoSuficiente (Transferencia transferencia){
        Tarjeta tarjeta = tarjetaDao.findTarjetaByNumTarjeta(transferencia.getEmisor().getNumTarjeta());
        if (tarjeta.getSaldo()>=transferencia.getMonto()) return true;
        else return false;
    }
    public Transferencia postTransferencia (Transferencia transferencia){
        return transferenciaDao.save(transferencia);
    }
}
