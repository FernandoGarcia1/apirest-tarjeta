package com.card.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.card.demo.entity.Domiciliacion;

@Repository
public interface DomiciliacionDao extends CrudRepository<Domiciliacion, Long> {

}