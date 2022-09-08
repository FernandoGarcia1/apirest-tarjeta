package com.card.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.card.demo.entity.Domiciliacion;

@Repository
public interface DomiciliacionDao extends CrudRepository<Domiciliacion, Long> {
    @Query(value = "Select * from domiciliaciones where tarjetas_id = ? and estatus = true", nativeQuery = true)
    List<Domiciliacion> activeByTarjeta(Long idTarjeta);

    @Query(value = "Select * from domiciliaciones where estatus = true", nativeQuery = true)
    List<Domiciliacion> allActives();

    Boolean existsDomiciliacionById(Long id);

    Domiciliacion findDomiciliacionById(Long id);
}