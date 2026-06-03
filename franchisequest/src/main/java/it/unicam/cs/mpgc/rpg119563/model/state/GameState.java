package it.unicam.cs.mpgc.rpg119563.model.state;

import it.unicam.cs.mpgc.rpg119563.model.brigade.Brigade;
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
    private final Brigade brigade;

    public GameState(EraType eraType, int level, int money, int reputation, Brigade brigade) {
        this.eraType    = eraType;
        this.level      = level;
        this.money      = money;
        this.reputation = reputation;
        this.brigade    = brigade;
    }

    /** Crea uno snapshot a partire dal franchise corrente. */
    public static GameState from(Franchise franchise) {
        return new GameState(
            franchise.getCurrentEra().getType(),
            franchise.getLevel(),
            franchise.getMoney(),
            franchise.getReputation(),
            franchise.getBrigade()
        );
    }

    public EraType getEraType()   { return eraType; }
    public int     getLevel()     { return level; }
    public int     getMoney()     { return money; }
    public int     getReputation(){ return reputation; }
    public Brigade getBrigade()   { return brigade; }
}
