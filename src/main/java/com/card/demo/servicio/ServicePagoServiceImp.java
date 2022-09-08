package com.card.demo.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.card.demo.dao.PagoServicioDao;
import com.card.demo.dao.TarjetaDao;
import com.card.demo.entity.PagoServicio;
import com.card.demo.entity.Tarjeta;

@Service
public class ServicePagoServiceImp implements ServicePagoServicio {

    @Autowired
    PagoServicioDao pagoServicioDao;
    @Autowired
    TarjetaDao tarjetaDao;

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

    public boolean saldoSuficiente(Tarjeta tarjeta, double monto_pago) {

        if (tarjeta.getSaldo() >= monto_pago) {
            return true; // La tarjeta cuenta con saldoSuficiente
        } else {
            return false;
        }

    }

    @Override
    @Transactional
    public boolean createPagoServicio(PagoServicio p) {

        Optional<Tarjeta> t = tarjetaDao.findTarjetaById((long) p.getTarjetaId());
        if (t.isPresent()) {
            Tarjeta tarjeta = t.get();
            if (saldoSuficiente(tarjeta, p.getMonto_pago())) { // Si la tarjeta tiene con suficiente saldo
                tarjeta.setSaldo(tarjeta.getSaldo() - p.getMonto_pago()); // Se descuenta el saldo en la tarjeta
                tarjetaDao.save(tarjeta);
                pagoServicioDao.save(p);
                return true; // si todo se cumplio retornar true
            }
        }
        return false; // si la tarjeta no tiene saldo
    }

}
