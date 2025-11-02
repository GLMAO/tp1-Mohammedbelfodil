package org.emp.gl.core.launcher;

import java.util.Random;
import java.util.stream.IntStream;

import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.HorlogeGraphique;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

public class App {

    public static void main(String[] args) throws InterruptedException {

        // 1) Instancie le service
        DummyTimeServiceImpl timerService = new DummyTimeServiceImpl();

        // 2) Observers
        new HorlogeGraphique("Horloge Graphique", timerService);

        Horloge h1 = new Horloge("Num 1", timerService);
        Horloge h2 = new Horloge("Num 2", timerService);
        Horloge h3 = new Horloge("Num 3", timerService);

        // 3) compte à rebours
        new CompteARebours("CA_5", 5, timerService);

        Random rnd = new Random();
        IntStream.range(0, 10).forEach(i -> {
            int initial = 10 + rnd.nextInt(11);
            new CompteARebours("CA_" + i, initial, timerService);
        });

        // 4) laisser tourner 1 minute
        Thread.sleep(60_000);

        // 5) arrêter proprement
        timerService.shutdown();
        System.out.println("Application terminée.");
    }
}
