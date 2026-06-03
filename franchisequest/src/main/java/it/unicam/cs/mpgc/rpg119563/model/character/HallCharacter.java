package it.unicam.cs.mpgc.rpg119563.model.character;

import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;

/**
 * Personaggio con ruolo Sala.
 * Statistiche di ruolo: carisma — influenza la reputazione del franchise.
 */
public class HallCharacter extends Character {

    private int charisma;

    public HallCharacter(String name, int level, int charisma) {
        super(name, level);
        this.charisma = charisma;
    }

    public int getCharisma() { return charisma; }
    public void setCharisma(int charisma) { this.charisma = charisma; }

    @Override
    public RoleType getRoleType() { return RoleType.HALL; }

    @Override
    public EventEffect applyBonus(EventEffect effect) {
        if (effect.getReputationDelta() <= 0) return effect;
        return effect.add(0, charisma);
    }
}
