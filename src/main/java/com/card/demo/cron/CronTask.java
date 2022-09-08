package com.card.demo.cron;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.card.demo.entity.Domiciliacion;
import com.card.demo.entity.PagoServicio;
import com.card.demo.servicio.ServiceDomiciliacion;
import com.card.demo.servicio.ServicePagoServicio;

@Component
public class CronTask {

    @Autowired
    private ServiceDomiciliacion domiciliacionService;
    @Autowired
    private ServicePagoServicio servicePagoServicio;

    @Scheduled(cron = "0 1 1 * * * ") // Cron que se ejecuta todos los dias a la una de la 01:01
    public void reportCurrentTime() {

        LocalDate fechaActual = LocalDate.now();

        List<Domiciliacion> domiciliaciones = domiciliacionService.findAllActives();

        for (Domiciliacion domiciliacion : domiciliaciones) {

            if (domiciliacion.getFecha_inicio().isBefore(fechaActual)) { // La fecha actual es posterior a la fecha de
                                                                         // la generacion de la domiciliacion

                //// ->>>>>>>>>>
                // System.out.println("La fecha actual es posterior a la fecha " +
                //// domiciliacion.getFecha_inicio());
                int diaDomiciliacion = domiciliacion.getFecha_inicio().getDayOfMonth();
                int diaHoy = fechaActual.getDayOfMonth();

                // fechaActual.isLeapYear() año biciesto

                if (fechaActual.getDayOfMonth() == 30) { // Caso especial dia 31
                    domiciliacion = casoEspecial31(fechaActual, domiciliacion);
                    domiciliacionService.saveDomiciliacion(domiciliacion);
                }

                if (fechaActual.getDayOfMonth() == 1 && fechaActual.getMonthValue() == 3) {
                    System.out.println("Hoy es 1 de Marzo");
                    casoFebrero(fechaActual, domiciliacion);
                    domiciliacionService.saveDomiciliacion(domiciliacion);
                }

                if (diaDomiciliacion == diaHoy) {
                    System.out.println("Crea Pago");
                    if (createPagoServicio(domiciliacion, fechaActual)) {
                        System.out.println("Pago creado");
                    } else {
                        System.out.println("Fallo al realiazar el pago");
                        domiciliacion.setEstatus(false);
                        domiciliacionService.saveDomiciliacion(domiciliacion);
                    }
                }
            }

            System.out.println("Fecha de domiciliacion: " + domiciliacion.getFecha_inicio());
            System.out.println("______________________________________________________-");
        }

    }

    private Boolean createPagoServicio(Domiciliacion domiciliacion, LocalDate fechaActual) {

        PagoServicio pagoServicio = new PagoServicio();
        pagoServicio.setTarjetaId(domiciliacion.getTarjetas_id());
        pagoServicio.setFecha_pago(fechaActual);
        pagoServicio.setMonto_pago(domiciliacion.getMonto());
        pagoServicio.setTipo_servicios_id(domiciliacion.getTipo_servicios_id());
        pagoServicio.setDomiciliaciones(domiciliacion);
        return servicePagoServicio.createPagoServicio(pagoServicio);

    }

    private Domiciliacion casoEspecial31(LocalDate fechaActual, Domiciliacion domiciliacion) {
        // Efectua el pago el dia 30 si el mes cuenta solo con 30 dias y el pago es el
        // dia 31.
        System.out.println("Hoy es 30");
        LocalDate lastDay = YearMonth.of(fechaActual.getYear(), fechaActual.getMonth()).atEndOfMonth(); // Ultimo dia //
                                                                                                        // del mes
        if (lastDay.getDayOfMonth() == 30) { // si el ultimo dia del mes es 30
            System.out.println("...**********************" + domiciliacion.getFecha_inicio().getDayOfMonth());
            if (domiciliacion.getFecha_inicio().getDayOfMonth() == 31) { // si el dia de pago es 30
                if (createPagoServicio(domiciliacion, fechaActual)) {
                    System.out.println("Pago creado");
                } else {
                    System.out.println("Fallo al realiazar el pago");
                    domiciliacion.setEstatus(false);
                    domiciliacionService.createDomiciliacion(domiciliacion);
                }
            }
        }
        return domiciliacion;
    }

    private Domiciliacion casoFebrero(LocalDate fechaActual, Domiciliacion domiciliacion) {

        if (!fechaActual.isLeapYear()) { // es año biciesto

            if (domiciliacion.getFecha_inicio().getDayOfMonth() == 29
                    || domiciliacion.getFecha_inicio().getDayOfMonth() == 30
                    || domiciliacion.getFecha_inicio().getDayOfMonth() == 31) {
                // si la fecha de domiciliacion es igual a 29,30,31 y la fecha actual es 1 de
                // marzo generar cobro
                if (createPagoServicio(domiciliacion, fechaActual)) {
                    System.out.println("Pago creado");
                } else {
                    System.out.println("Fallo al realiazar el pago");
                    domiciliacion.setEstatus(false);
                }
            }
        } else {
            if (domiciliacion.getFecha_inicio().getDayOfMonth() == 30
                    || domiciliacion.getFecha_inicio().getDayOfMonth() == 31) {
                if (createPagoServicio(domiciliacion, fechaActual)) {
                    System.out.println("Pago creado");

                } else {
                    System.out.println("Fallo al realiazar el pago");
                    domiciliacion.setEstatus(false);

                }
            }
        }
        return domiciliacion;
    }

}
