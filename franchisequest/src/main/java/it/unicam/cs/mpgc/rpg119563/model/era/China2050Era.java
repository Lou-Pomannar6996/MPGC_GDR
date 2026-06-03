package it.unicam.cs.mpgc.rpg119563.model.era;

import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;
import it.unicam.cs.mpgc.rpg119563.model.event.HistoricalEvent;

import java.util.List;
import java.util.Random;

/** Era: Cina 2050. */
public class China2050Era implements Era {

    private final Random random = new Random();

    private final List<HistoricalEvent> events = List.of(
        new HistoricalEvent("Blackout della rete", "Guasto tecnologico: perdite operative.", new EventEffect(-200, -20)),
        new HistoricalEvent("Innovazione robotica", "Un nuovo sistema automatizza la cucina.", new EventEffect(300, +30))
    );

    @Override public String getName() { return "Cina 2050"; }
    @Override public int getMaxLevel() { return 100; }
    @Override public List<HistoricalEvent> getAvailableEvents() { return events; }
    @Override public EraType getType() { return EraType.CHINA_2050; }
    @Override public HistoricalEvent generateEvent() { return events.get(random.nextInt(events.size())); }
}
