package com.card.demo.dao;
import com.card.demo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
