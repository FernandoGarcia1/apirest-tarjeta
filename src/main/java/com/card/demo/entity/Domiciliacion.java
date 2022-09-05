package com.card.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "domiciliaciones")
public class Domiciliacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private LocalDate fecha_inicio;
    private Boolean estatus;
    private Double monto;
    private int tarjetas_id;

    // @OneToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH,
    // CascadeType.MERGE, CascadeType.PERSIST })
    // @JoinColumn(name = "pago_servicios_id")
    // private PagoServicio pagoServicio;

}
