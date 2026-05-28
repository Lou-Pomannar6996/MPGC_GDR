package it.unicam.cs.mpgc.rpg119563.model.event;

import it.unicam.cs.mpgc.rpg119563.model.era.Era;

import java.util.List;
import java.util.Random;

/**
 * Seleziona casualmente un evento dall'era corrente e ne restituisce gli effetti.
 * Non modifica lo stato del franchise — è responsabilità di Franchise.applyEffect().
 */
public class EventEngine {

    private final Random random = new Random();

    public HistoricalEvent pickEvent(Era era) {
        List<HistoricalEvent> events = era.getAvailableEvents();
        if (events.isEmpty()) throw new IllegalStateException("Nessun evento disponibile per l'era: " + era.getName());
        return events.get(random.nextInt(events.size()));
    }
}
