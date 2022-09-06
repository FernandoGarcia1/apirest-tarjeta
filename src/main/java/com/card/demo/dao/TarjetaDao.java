package com.card.demo.dao;

import com.card.demo.entity.Tarjeta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaDao extends JpaRepository<Tarjeta, Long> {
    Boolean existsTarjetaByNumTarjeta(String numTarjeta);

    Tarjeta findTarjetaByNumTarjeta(String numTarjeta);

    Optional<Tarjeta> findTarjetaById(Long id);
}
