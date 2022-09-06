package com.equipo4.difs.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name="clientes")

public class Cliente {

    @Id
    @Column(name = "curp")
    private  String curp;

    @Column(name = "nombre")
    private  String nombre;

    @Column(name = "apellidos")
    private  String apellidos;

    @Column(name = "correo")
    private  String correo;

    @Column(name = "telefono")
    private  String telefono;

    @Column(name = "tarjetas_id")
    private  Integer targeta_id;

    @Column(name = "fecha_nacimiento")
    private  LocalDate fecha_nacimeinto;

    public Cliente(String curp, String nombre, String apellidos, String correo, String telefono, Integer targeta_id, LocalDate fecha_nacimeinto) {
        this.curp = curp;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.targeta_id = targeta_id;
        this.fecha_nacimeinto = fecha_nacimeinto;
    }

    public Cliente() {

    }
}
