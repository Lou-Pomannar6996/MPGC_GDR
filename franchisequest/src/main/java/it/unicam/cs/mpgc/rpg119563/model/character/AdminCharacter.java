package it.unicam.cs.mpgc.rpg119563.model.character;

import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;

/**
 * Personaggio con ruolo Amministrazione.
 * Statistiche di ruolo: management — influenza le risorse economiche.
 */
public class AdminCharacter extends Character {

    private int management;

    public AdminCharacter(String name, int level, int management) {
        super(name, level);
        this.management = management;
    }

    public int getManagement() { return management; }
    public void setManagement(int management) { this.management = management; }

    @Override
    public RoleType getRoleType() { return RoleType.ADMIN; }

    @Override
    public void applyBonus(EventEffect effect) {
        // TODO: applicare bonus economico basato su management
    }
}
