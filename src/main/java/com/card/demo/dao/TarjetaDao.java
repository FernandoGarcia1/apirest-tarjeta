package com.card.demo.dao;

import com.card.demo.entity.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaDao extends JpaRepository<Tarjeta,Long> {
    Boolean existsTarjetaByNumTarjeta(String numTarjeta);
    Tarjeta findTarjetaByNumTarjeta(String numTarjeta);
}
