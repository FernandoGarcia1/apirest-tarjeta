package com.card.demo.dao;

import com.card.demo.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TransferenciaDao extends JpaRepository<Transferencia,Long> {
    ArrayList<Transferencia> findTransferenciaByEmisor(String tarjetaEmisor);
    ArrayList<Transferencia> findTransferenciaByReceptor(String tarjetaReceptor);
}
