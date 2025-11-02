package org.emp.gl.time.service.impl;

import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * Implémentation simple du TimerService qui publie des PropertyChange events.
 */
public class DummyTimeServiceImpl implements TimerService {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private volatile int dixiemeDeSeconde;
    private volatile int minutes;
    private volatile int secondes;
    private volatile int heures;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public DummyTimeServiceImpl() {
        // initialisation des valeurs
        setTimeValuesInitial();

        // tick toutes les 100ms (dixièmes)
        scheduler.scheduleAtFixedRate(this::tick, 100, 100, TimeUnit.MILLISECONDS);
    }

    private void setTimeValuesInitial() {
        LocalTime now = LocalTime.now();
        this.secondes = now.getSecond();
        this.minutes = now.getMinute();
        this.heures = now.getHour();
        this.dixiemeDeSeconde = now.getNano() / 100_000_000; // 0..9
    }

    private void tick() {
        LocalTime now = LocalTime.now();

        int newDixieme = now.getNano() / 100_000_000;
        int newSecondes = now.getSecond();
        int newMinutes = now.getMinute();
        int newHeures = now.getHour();

        // vérifier et notifier uniquement si changement
        if (newDixieme != this.dixiemeDeSeconde) {
            int old = this.dixiemeDeSeconde;
            this.dixiemeDeSeconde = newDixieme;
            pcs.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP, old, newDixieme);
        }
        if (newSecondes != this.secondes) {
            int old = this.secondes;
            this.secondes = newSecondes;
            pcs.firePropertyChange(TimerChangeListener.SECONDE_PROP, old, newSecondes);
        }
        if (newMinutes != this.minutes) {
            int old = this.minutes;
            this.minutes = newMinutes;
            pcs.firePropertyChange(TimerChangeListener.MINUTE_PROP, old, newMinutes);
        }
        if (newHeures != this.heures) {
            int old = this.heures;
            this.heures = newHeures;
            pcs.firePropertyChange(TimerChangeListener.HEURE_PROP, old, newHeures);
        }
    }

    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {
        pcs.addPropertyChangeListener(pl);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {
        pcs.removePropertyChangeListener(pl);
    }

    @Override
    public int getDixiemeDeSeconde() { return dixiemeDeSeconde; }

    @Override
    public int getHeures() { return heures; }

    @Override
    public int getMinutes() { return minutes; }

    @Override
    public int getSecondes() { return secondes; }

    /**
     * Pour arrêter le scheduler proprement si besoin.
     */
    public void shutdown() {
        scheduler.shutdownNow();
    }
}
