package it.unicam.cs.mpgc.rpg119563.model.era;

import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;
import it.unicam.cs.mpgc.rpg119563.model.event.HistoricalEvent;

import java.util.List;
import java.util.Random;

/**
 * Era: Antico Egitto.
 * Franchise lungo il Nilo — eventi legati a inondazioni, festival e commercio.
 */
public class AncientEgyptEra implements Era {

    private static final int MAX_LEVEL = 10;
    private final Random random = new Random();

    private final List<HistoricalEvent> events = List.of(
        new HistoricalEvent(
            "Inondazione del Nilo",
            "Il Nilo esonda: gli approvvigionamenti scarseggiano.",
            new EventEffect(-200, -10)
        ),
        new HistoricalEvent(
            "Festival di Opet",
            "Il festival porta migliaia di visitatori: la reputazione cresce.",
            new EventEffect(100, +20)
        )
        // TODO: aggiungere altri eventi storici
    );

    @Override public String getName() { return "Antico Egitto"; }
    @Override public int getMaxLevel() { return MAX_LEVEL; }
    @Override public List<HistoricalEvent> getAvailableEvents() { return events; }
    @Override public EraType getType() { return EraType.ANCIENT_EGYPT; }

    @Override
    public HistoricalEvent generateEvent() {
        return events.get(random.nextInt(events.size()));
    }
}
