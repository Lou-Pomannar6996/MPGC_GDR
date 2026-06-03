package it.unicam.cs.mpgc.rpg119563.model.character;

import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;

/**
 * Contratto per i ruoli della brigata.
 * Ogni ruolo può applicare bonus specifici agli effetti degli eventi.
 * Il metodo applyBonus riceve l'effetto corrente e restituisce uno nuovo con il bonus applicato.
 */
public interface CharacterRole {
    RoleType getRoleType();
    EventEffect applyBonus(EventEffect effect);
}
