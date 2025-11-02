package org.emp.gl.clients;

import java.awt.Font;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;


public class HorlogeGraphique extends JFrame implements TimerChangeListener {

    private final TimerService timerService;
    private JLabel labelHeure = new JLabel("00:00:00");

    public HorlogeGraphique(String titre, TimerService timerService) {
        super(titre);
        this.timerService = timerService;

        // inscription
        this.timerService.addTimeChangeListener(this);

        // config UI
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        labelHeure.setFont(new Font("Consolas", Font.BOLD, 50));
        labelHeure.setHorizontalAlignment(JLabel.CENTER);
        add(labelHeure);

        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(() -> {
                labelHeure.setText(
                        pad(timerService.getHeures()) + ":" +
                                pad(timerService.getMinutes()) + ":" +
                                pad(timerService.getSecondes())
                );
            });
        }
    }

    private String pad(int v) { return v < 10 ? "0" + v : "" + v; }
}
