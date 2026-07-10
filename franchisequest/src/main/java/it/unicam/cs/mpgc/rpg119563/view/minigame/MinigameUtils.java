package it.unicam.cs.mpgc.rpg119563.view.minigame;

import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;

/** Metodi di formattazione condivisi tra i controller di minigioco. */
class MinigameUtils {

    private MinigameUtils() {}

    /** Formatta un EventEffect in testo leggibile, es. "+250 monete  /  +15 reputazione". */
    static String formatEffect(EventEffect e) {
        StringBuilder sb = new StringBuilder();
        if (e.getMoneyDelta() != 0)
            sb.append(e.getMoneyDelta() > 0 ? "+" : "").append(e.getMoneyDelta()).append(" monete");
        if (e.getMoneyDelta() != 0 && e.getReputationDelta() != 0)
            sb.append("  /  ");
        if (e.getReputationDelta() != 0)
            sb.append(e.getReputationDelta() > 0 ? "+" : "").append(e.getReputationDelta()).append(" reputazione");
        return sb.toString();
    }

    /**
     * Produce il testo delle regole con i valori concreti dell'evento:
     *   Se vinci: <valori vittoria>
     *   Se perdi: <valori sconfitta>
     */
    static String buildRulesText(EventEffect effect) {
        return "Se vinci: " + formatEffect(effect.withMinigameResult(true))  + "\n"
             + "Se perdi: " + formatEffect(effect.withMinigameResult(false));
    }

    /** Produce il testo del risultato con i valori effettivamente applicati. */
    static String buildResultText(boolean won, EventEffect effect) {
        EventEffect applied = effect.withMinigameResult(won);
        return (won ? "VITTORIA!   " : "SCONFITTA!   ") + formatEffect(applied);
    }
}
