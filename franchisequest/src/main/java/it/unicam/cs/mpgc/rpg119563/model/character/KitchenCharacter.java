package it.unicam.cs.mpgc.rpg119563.model.character;

import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;

/**
 * Personaggio con ruolo Cucina.
 * Statistiche di ruolo: skill — influenza gli eventi produttivi.
 */
public class KitchenCharacter extends Character {

    private int skill;

    public KitchenCharacter(String name, int level, int skill) {
        super(name, level);
        this.skill = skill;
    }

    public int getSkill() { return skill; }
    public void setSkill(int skill) { this.skill = skill; }

    @Override
    public RoleType getRoleType() { return RoleType.KITCHEN; }

    @Override
    public EventEffect applyBonus(EventEffect effect) {
        if (effect.getMoneyDelta() <= 0) return effect;
        return effect.add(skill * 10, 0);
    }
}
