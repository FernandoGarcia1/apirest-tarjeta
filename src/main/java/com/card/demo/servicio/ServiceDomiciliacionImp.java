package com.card.demo.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.card.demo.dao.DomiciliacionDao;
import com.card.demo.dao.TarjetaDao;
import com.card.demo.entity.Domiciliacion;
import com.card.demo.entity.PagoServicio;
import com.card.demo.entity.Tarjeta;

@Service
public class ServiceDomiciliacionImp implements ServiceDomiciliacion {

    @Autowired
    private DomiciliacionDao domiciliacionDao;
    @Autowired
    private TarjetaDao tarjetaDao;
    @Autowired
    private ServicePagoServicio servicePagoServicio;

    @Override
    public List<Domiciliacion> findAll() {
        // TODO Auto-generated method stub
        return (List<Domiciliacion>) domiciliacionDao.findAll();
    }

    @Override
    public List<Domiciliacion> activeByTarjeta(Long idTarjeta) {
        return (List<Domiciliacion>) domiciliacionDao.activeByTarjeta(idTarjeta);
    }

    @Override
    @Transactional
    public boolean createDomiciliacion(Domiciliacion d) {
        Optional<Tarjeta> t = tarjetaDao.findTarjetaById((long) d.getTarjetas_id());
        if (t.isPresent()) {
            Tarjeta tarjeta = t.get();
            if (servicePagoServicio.saldoSuficiente(tarjeta, d.getMonto())) {
                PagoServicio pagoServicio = new PagoServicio();
                pagoServicio.setTarjetaId(d.getTarjetas_id());
                pagoServicio.setFecha_pago(d.getFecha_inicio());
                pagoServicio.setMonto_pago(d.getMonto());
                pagoServicio.setTipo_servicios_id(d.getTipo_servicios_id());
                pagoServicio.setDomiciliaciones(d);
                domiciliacionDao.save(d);
                servicePagoServicio.createPagoServicio(pagoServicio);
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean cancelDomiciliacion(long id) {

        if (domiciliacionDao.existsById(id)) {
            System.out.println("Existe la domiciliacion");
            Domiciliacion d = domiciliacionDao.findDomiciliacionById(id);
            System.out.println(d);

            d.setEstatus(false);
            d = domiciliacionDao.save(d);
            if (d.getEstatus() == false) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
