package it.unicam.cs.mpgc.rpg119563.model.state;

import it.unicam.cs.mpgc.rpg119563.model.era.EraType;
import it.unicam.cs.mpgc.rpg119563.model.franchise.Franchise;

/**
 * Snapshot immutabile dello stato corrente della partita.
 * Usato esclusivamente per la persistenza — non è l'oggetto vivo di gioco.
 */
public final class GameState {

    private final EraType eraType;
    private final int     level;
    private final int     money;
    private final int     reputation;
    // TODO: aggiungere snapshot della brigata

    public GameState(EraType eraType, int level, int money, int reputation) {
        this.eraType    = eraType;
        this.level      = level;
        this.money      = money;
        this.reputation = reputation;
    }

    /** Crea uno snapshot a partire dal franchise corrente. */
    public static GameState from(Franchise franchise, EraType eraType) {
        return new GameState(eraType, franchise.getLevel(), franchise.getMoney(), franchise.getReputation());
    }

    public EraType getEraType()   { return eraType; }
    public int     getLevel()     { return level; }
    public int     getMoney()     { return money; }
    public int     getReputation(){ return reputation; }
}
