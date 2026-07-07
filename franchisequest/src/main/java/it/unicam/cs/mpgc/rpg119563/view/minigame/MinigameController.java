package it.unicam.cs.mpgc.rpg119563.view.minigame;

import it.unicam.cs.mpgc.rpg119563.model.era.EraType;

/** Contratto comune per tutti i controller di minigioco. */
public interface MinigameController {
    /** Inizializza il minigioco con il nome del personaggio estratto e l'era corrente. */
    void init(String characterName, EraType eraType);
    /** Restituisce true se il giocatore ha vinto il minigioco. */
    boolean isWon();
}
