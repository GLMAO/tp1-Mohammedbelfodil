package org.emp.gl.timer.service;

/**
 * TimerService expose l'heure courante (abstraction).
 */
public interface TimerService extends TimeChangeProvider {
    int getMinutes();
    int getHeures();
    int getSecondes();
    int getDixiemeDeSeconde();
}
