package it.unicam.cs.mpgc.rpg119563.model.era;

import it.unicam.cs.mpgc.rpg119563.model.event.EventSource;
import it.unicam.cs.mpgc.rpg119563.model.event.HistoricalEvent;

import java.util.List;

/**
 * Contratto per un'era storica.
 * Ogni era definisce: nome, livello massimo e lista di eventi disponibili.
 * Aggiungere una nuova era = implementare questa interfaccia + registrarla in EraFactory.
 */
public interface Era extends EventSource {
    String getName();
    int getMaxLevel();
    List<HistoricalEvent> getAvailableEvents();
    EraType getType();
}
