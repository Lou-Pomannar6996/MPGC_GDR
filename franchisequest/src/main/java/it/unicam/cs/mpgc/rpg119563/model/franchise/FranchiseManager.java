package it.unicam.cs.mpgc.rpg119563.model.franchise;

import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;

/**
 * Contratto per la gestione degli indicatori del franchise.
 * Progettata per supportare indicatori aggiuntivi futuri senza breaking changes.
 */
public interface FranchiseManager {
    void applyEffect(EventEffect effect);
    int getMoney();
    int getReputation();
}
