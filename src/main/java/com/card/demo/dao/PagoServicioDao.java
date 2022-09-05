package com.card.demo.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.card.demo.entity.PagoServicio;

public interface PagoServicioDao extends CrudRepository<PagoServicio, Long> {

    List<PagoServicio> findPagosServicioByTarjetaId(int idTarjeta);

    @Query(value = "Select * from pago_servicios where fecha_pago between ? and ? and tarjetas_id = ? ", nativeQuery = true)
    List<PagoServicio> findByTarjetaIdandDates(LocalDate dateStart, LocalDate dateEnd, int idTarjeta);
}