package it.unicam.cs.mpgc.rpg119563.model.event;

/**
 * Rappresenta un evento storico casuale con descrizione ed effetti sugli indicatori.
 */
public class HistoricalEvent {

    private final String title;
    private final String description;
    private final EventEffect effect;

    public HistoricalEvent(String title, String description, EventEffect effect) {
        this.title = title;
        this.description = description;
        this.effect = effect;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public EventEffect getEffect() { return effect; }

    @Override
    public String toString() {
        return title + ": " + effect;
    }
}
