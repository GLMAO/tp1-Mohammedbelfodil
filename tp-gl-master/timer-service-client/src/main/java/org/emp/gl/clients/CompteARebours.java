package org.emp.gl.clients;

import java.beans.PropertyChangeEvent;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;


public class CompteARebours implements TimerChangeListener {

    private int compteur;
    private final TimerService timerService;
    private final String name;

    public CompteARebours(String name, int initial, TimerService timerService) {
        this.name = name;
        this.compteur = Math.max(0, initial);
        this.timerService = timerService;
        // s'inscrire
        this.timerService.addTimeChangeListener(this);
        System.out.println("CompteARebours " + name + " initialisé à " + initial);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            if (compteur > 0) {
                compteur--;
                System.out.println("[" + name + "] reste : " + compteur);
                if (compteur == 0) {
                    // désinscrire lorsque 0
                    timerService.removeTimeChangeListener(this);
                    System.out.println("[" + name + "] terminé et désinscrit.");
                }
            }
        }
    }

    public int getCompteur() {
        return compteur;
    }
}
