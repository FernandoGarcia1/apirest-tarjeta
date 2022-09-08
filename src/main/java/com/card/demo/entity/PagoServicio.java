package com.card.demo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pago_servicios")
public class PagoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double monto_pago;
    private LocalDate fecha_pago;

    @Column(name = "tarjetas_id")
    private int tarjetaId;
    private int tipo_servicios_id;
    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH,
            CascadeType.MERGE })
    @JoinColumn(name = "domiciliaciones_id")
    private Domiciliacion domiciliaciones;

}
