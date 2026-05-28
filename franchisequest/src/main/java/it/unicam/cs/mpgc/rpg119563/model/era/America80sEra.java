package it.unicam.cs.mpgc.rpg119563.model.era;

import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;
import it.unicam.cs.mpgc.rpg119563.model.event.HistoricalEvent;

import java.util.List;
import java.util.Random;

/** Era: America anni '80. */
public class America80sEra implements Era {

    private final Random random = new Random();

    private final List<HistoricalEvent> events = List.of(
        new HistoricalEvent("Crisi del 1982", "Recessione economica: meno clienti.", new EventEffect(-150, -10)),
        new HistoricalEvent("Boom fast-food", "La cultura fast-food esplode: affari d'oro.", new EventEffect(250, +15))
    );

    @Override public String getName() { return "America anni '80"; }
    @Override public int getMaxLevel() { return 100; }
    @Override public List<HistoricalEvent> getAvailableEvents() { return events; }
    @Override public HistoricalEvent generateEvent() { return events.get(random.nextInt(events.size())); }
}
