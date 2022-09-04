package com.card.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name="tarjetas")
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private long id;
    @Column(name="num_tarjeta")
    private String numTarjeta;
    private int cvv;
    private LocalDate vencimiento;
    private double saldo;
}
