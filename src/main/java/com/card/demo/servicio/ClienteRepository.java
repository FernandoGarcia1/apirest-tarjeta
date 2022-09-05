package com.equipo4.difs.repositorios;
import com.equipo4.difs.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
