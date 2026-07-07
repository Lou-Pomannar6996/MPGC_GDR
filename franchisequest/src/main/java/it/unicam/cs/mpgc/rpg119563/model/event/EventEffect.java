package it.unicam.cs.mpgc.rpg119563.model.event;

/**
 * Value object: rappresenta l'impatto numerico di un evento sugli indicatori.
 * Immutabile — nessuna logica, solo dati.
 */
public final class EventEffect {

    private final int moneyDelta;
    private final int reputationDelta;

    public EventEffect(int moneyDelta, int reputationDelta) {
        this.moneyDelta = moneyDelta;
        this.reputationDelta = reputationDelta;
    }

    public int getMoneyDelta() { return moneyDelta; }
    public int getReputationDelta() { return reputationDelta; }

    /** Restituisce un nuovo EventEffect con i delta sommati. Utile per applicare bonus. */
    public EventEffect add(int extraMoney, int extraReputation) {
        return new EventEffect(moneyDelta + extraMoney, reputationDelta + extraReputation);
    }

    /**
     * Applica la regola del minigioco:
     * - bonus (delta > 0): vittoria = pieno, sconfitta = metà
     * - malus (delta < 0): vittoria = dimezzato, sconfitta = intero
     */
    public EventEffect withMinigameResult(boolean won) {
        return new EventEffect(applyRule(moneyDelta, won), applyRule(reputationDelta, won));
    }

    private static int applyRule(int delta, boolean won) {
        if (delta > 0) return won ? delta : delta / 2;
        if (delta < 0) return won ? delta / 2 : delta;
        return 0;
    }

    @Override
    public String toString() {
        return "EventEffect{money=" + moneyDelta + ", reputation=" + reputationDelta + "}";
    }
}
