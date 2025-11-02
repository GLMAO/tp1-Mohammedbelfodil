package org.emp.gl.timer.service;

/**
 * Fournit la possibilité d'ajouter/enlever des listeners.
 */
public interface TimeChangeProvider {
    void addTimeChangeListener(TimerChangeListener pl);
    void removeTimeChangeListener(TimerChangeListener pl);
}
