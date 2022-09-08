package com.card.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="transferencias")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private long id;
    private double monto;
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name="id",insertable = false, updatable = false)
    private Tarjeta receptor;
    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    private Tarjeta emisor;

}
