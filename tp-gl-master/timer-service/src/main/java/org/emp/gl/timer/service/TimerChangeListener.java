package org.emp.gl.timer.service;

import java.beans.PropertyChangeListener;

/**
 * TimerChangeListener hérite maintenant de PropertyChangeListener.
 * On conserve les noms de propriétés en constantes.
 */
public interface TimerChangeListener extends PropertyChangeListener {
    String DIXEME_DE_SECONDE_PROP = "dixieme";
    String SECONDE_PROP = "seconde";
    String MINUTE_PROP = "minute";
    String HEURE_PROP = "heure";
}
