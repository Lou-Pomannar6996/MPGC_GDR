package it.unicam.cs.mpgc.rpg119563.view.minigame;

import it.unicam.cs.mpgc.rpg119563.model.era.EraType;
import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;

/** Contratto comune per tutti i controller di minigioco. */
public interface MinigameController {
    /** Inizializza il minigioco con nome personaggio, era corrente ed effetto dell'evento. */
    void init(String characterName, EraType eraType, EventEffect effect);
    /** Restituisce true se il giocatore ha vinto il minigioco. */
    boolean isWon();
}
