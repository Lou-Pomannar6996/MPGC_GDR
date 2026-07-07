package it.unicam.cs.mpgc.rpg119563.model.franchise;

import it.unicam.cs.mpgc.rpg119563.model.brigade.Brigade;
import it.unicam.cs.mpgc.rpg119563.model.era.Era;
import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;

/**
 * Entità principale del gioco.
 * Contiene brigata, indicatori (soldi, reputazione), era corrente e livello.
 */
public class Franchise implements FranchiseManager {

    private Brigade brigade;
    private Era     currentEra;
    private int     level;
    private int     money;
    private int     reputation;

    public Franchise(Brigade brigade, Era startingEra, int money, int reputation) {
        this.brigade    = brigade;
        this.currentEra = startingEra;
        this.level      = 1;
        this.money      = money;
        this.reputation = reputation;
    }

    @Override
    public void applyEffect(EventEffect effect) {
        EventEffect boosted = brigade.applyAllBonuses(effect);
        this.money      += boosted.getMoneyDelta();
        this.reputation += boosted.getReputationDelta();
        // Impedisce valori negativi
        if (this.money < 0)      this.money = 0;
        if (this.reputation < 0) this.reputation = 0;
    }

    public void applyEffectDirect(EventEffect effect) {
        this.money      += effect.getMoneyDelta();
        this.reputation += effect.getReputationDelta();
        if (this.money < 0)      this.money = 0;
        if (this.reputation < 0) this.reputation = 0;
    }

    public void levelUp() {
        if (level < currentEra.getMaxLevel()) level++;
    }

    public boolean canAdvanceEra() {
        return level >= currentEra.getMaxLevel();
    }

    public void advanceEra(Era nextEra) {
        if (!canAdvanceEra()) throw new IllegalStateException("Livello massimo non raggiunto");
        this.currentEra = nextEra;
        this.level = 1;
    }

    // Getters & Setters
    public Brigade getBrigade()             { return brigade; }
    public void    setBrigade(Brigade b)    { this.brigade = b; }
    public Era     getCurrentEra()          { return currentEra; }
    public int     getLevel()               { return level; }
    public void    setLevel(int level)      { this.level = level; }
    @Override public int getMoney()         { return money; }
    @Override public int getReputation()    { return reputation; }
}
