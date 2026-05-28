package it.unicam.cs.mpgc.rpg119563.model.event;

/**
 * Contratto per la generazione di eventi casuali.
 * Implementato da ogni Era.
 */
public interface EventSource {
    HistoricalEvent generateEvent();
}
