package com.card.demo.web;

import com.card.demo.entity.Cliente;
import com.card.demo.servicio.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cliente")
public class ControlUsuarios {
    private final ClienteService clienteService;

    public ControlUsuarios(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // listar a los clientes
    @GetMapping()
    public ResponseEntity<?> getClientes() {

        List <Cliente> clientes = clienteService.getAllClientes();

         // Se valida que existan clientes.

        if (clientes.size() == 0) {
            return new ResponseEntity<>("No existen clientes dados de alta", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    //Buscar cliente por curp

    @GetMapping("{curp}")
    public ResponseEntity<?> getClientesId(@PathVariable String curp) {

        Cliente cliente = clienteService.getAllClientesById(curp);

        if (cliente == null ) {
            return new ResponseEntity<>("No existen clientes dado de alta con curp: " + curp, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

 // Dar de alta un nuevo cliente

    @PostMapping()
    public ResponseEntity<?> createCliente (@RequestBody Cliente cliente) {
        //Validamos los datos, lo primero es verificar que la llave primaria no se repitan
        if (clienteService.existeLlave(cliente.getCurp())==false){
            clienteService.createCliente(cliente);
            return new ResponseEntity<>(cliente,HttpStatus.OK);
        }
        else return new ResponseEntity<>("Datos del cliente no validos", HttpStatus.BAD_REQUEST);
    }

    //Eliminar un cliente

    @DeleteMapping("{curp}")
    public ResponseEntity<?> deleteCliente (@PathVariable String curp) {
        // Se verifica que el cliente a eliminar si exista
        System.out.println(clienteService.existeLlave(curp));
        if (clienteService.existeLlave(curp)==true){
            clienteService.deleteCliente(curp);
            return new ResponseEntity<>("Cliente con curp " + curp + " eliminado",HttpStatus.OK);
        }
        else return new ResponseEntity<>("El Curp del cliente no existe", HttpStatus.BAD_REQUEST);
    }

    // editar cliente aun estoy editando esto
    @PutMapping()
    public ResponseEntity<?> editarCliente (@RequestBody Cliente cliente) {
        // Se verifica que el cliente a actualizar si exista
        if (clienteService.existeLlave(cliente.getCurp())==true){
            clienteService.setCliente(cliente);
            return new ResponseEntity<>("Cliente con curp " + cliente.getCurp() + " eliminado",HttpStatus.OK);
        }
        else return new ResponseEntity<>("El cliente con curp: " +  cliente.getCurp()+ " no existe", HttpStatus.BAD_REQUEST);
    }


}
