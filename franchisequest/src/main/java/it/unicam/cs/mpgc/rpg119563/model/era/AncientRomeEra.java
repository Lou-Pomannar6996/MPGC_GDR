package it.unicam.cs.mpgc.rpg119563.model.era;

import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;
import it.unicam.cs.mpgc.rpg119563.model.event.HistoricalEvent;

import java.util.List;
import java.util.Random;

/** Era: Antica Roma. */
public class AncientRomeEra implements Era {

    private final Random random = new Random();

    private final List<HistoricalEvent> events = List.of(
        new HistoricalEvent("Incendio di Roma", "Parte del quartiere brucia.", new EventEffect(-300, -15)),
        new HistoricalEvent("Giochi del Colosseo", "La folla affollata porta clienti.", new EventEffect(150, +25))
    );

    @Override public String getName() { return "Antica Roma"; }
    @Override public int getMaxLevel() { return 100; }
    @Override public List<HistoricalEvent> getAvailableEvents() { return events; }
    @Override public HistoricalEvent generateEvent() { return events.get(random.nextInt(events.size())); }
}
