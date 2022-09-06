package com.card.demo.servicio;

import com.card.demo.dao.ClienteRepository;
import com.card.demo.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }


    public void createCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public boolean existeLlave(String curp) {
        return clienteRepository.existsById(curp);
    }

    public void deleteCliente(String curp) {
        clienteRepository.deleteById(curp);
    }

    public Cliente getAllClientesById(String curp) {
        return clienteRepository.getReferenceById(curp);
    }

    public void setCliente(Cliente cliente) {
        Cliente clienteTemp = clienteRepository.getReferenceById(cliente.getCurp());
        clienteTemp.setNombre(cliente.getNombre());
        clienteTemp.setApellidos(cliente.getApellidos());
        clienteTemp.setCorreo(cliente.getCorreo());
        clienteTemp.setTelefono(cliente.getTelefono());
        clienteTemp.setTargeta_id(clienteTemp.getTargeta_id());
        clienteTemp.setFecha_nacimeinto(cliente.getFecha_nacimeinto());
        clienteRepository.save(clienteTemp);
    }
}
