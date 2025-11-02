package org.emp.gl.clients;

import java.beans.PropertyChangeEvent;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;


public class Horloge implements TimerChangeListener {

    private final String name;
    private final TimerService timerService;

    public Horloge(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;
        // s'inscrire au timer
        this.timerService.addTimeChangeListener(this);
        System.out.println("Horloge " + name + " initialisée et inscrite au TimerService.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // On filtre pour n'afficher que sur changement de seconde
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            afficherHeure();
        }
    }

    public void afficherHeure() {
        System.out.println(name + " affiche " +
                pad(timerService.getHeures()) + ":" +
                pad(timerService.getMinutes()) + ":" +
                pad(timerService.getSecondes()));
    }

    private String pad(int v) {
        return (v < 10) ? ("0" + v) : Integer.toString(v);
    }

    public void desinscrire() {
        timerService.removeTimeChangeListener(this);
        System.out.println("Horloge " + name + " desinscrite.");
    }
}
