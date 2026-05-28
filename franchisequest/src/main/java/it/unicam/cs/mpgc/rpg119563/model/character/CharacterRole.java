package it.unicam.cs.mpgc.rpg119563.model.character;

import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;

/**
 * Contratto per i ruoli della brigata.
 * Ogni ruolo può applicare bonus specifici agli effetti degli eventi.
 */
public interface CharacterRole {
    RoleType getRoleType();
    void applyBonus(EventEffect effect);
}
