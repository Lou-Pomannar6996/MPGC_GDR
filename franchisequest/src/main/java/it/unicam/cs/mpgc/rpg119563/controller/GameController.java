package it.unicam.cs.mpgc.rpg119563.controller;

import it.unicam.cs.mpgc.rpg119563.model.era.Era;
import it.unicam.cs.mpgc.rpg119563.model.era.EraFactory;
import it.unicam.cs.mpgc.rpg119563.model.era.EraType;
import it.unicam.cs.mpgc.rpg119563.model.event.EventEngine;
import it.unicam.cs.mpgc.rpg119563.model.event.HistoricalEvent;
import it.unicam.cs.mpgc.rpg119563.model.franchise.Franchise;
import it.unicam.cs.mpgc.rpg119563.model.state.GameState;
import it.unicam.cs.mpgc.rpg119563.persistence.SaveFileValidator;
import it.unicam.cs.mpgc.rpg119563.persistence.XmlLoadManager;
import it.unicam.cs.mpgc.rpg119563.persistence.XmlSaveManager;

/**
 * Coordina il flusso di gioco: turni, eventi, aggiornamento indicatori, persistenza.
 * Dipende da interfacce — non da implementazioni concrete (DIP).
 */
public class GameController {

    private Franchise         franchise;
    private final EventEngine eventEngine     = new EventEngine();
    private final XmlSaveManager saveManager  = new XmlSaveManager();
    private final XmlLoadManager loadManager  = new XmlLoadManager();
    private final SaveFileValidator validator  = new SaveFileValidator();

    public void newGame(Franchise franchise) {
        this.franchise = franchise;
    }

    /**
     * Esegue un turno: genera un evento, applica gli effetti, restituisce l'evento per la View.
     */
    public HistoricalEvent nextTurn() {
        HistoricalEvent event = eventEngine.pickEvent(franchise.getCurrentEra());
        franchise.applyEffect(event.getEffect());
        franchise.levelUp();
        return event;
    }

    public boolean canAdvanceEra() {
        return franchise.canAdvanceEra();
    }

    public void advanceEra(EraType nextEraType) {
        Era nextEra = EraFactory.create(nextEraType);
        franchise.advanceEra(nextEra);
    }

    public void saveGame(EraType currentEraType) throws Exception {
        GameState state = GameState.from(franchise, currentEraType);
        saveManager.save(state);
    }

    public boolean loadGame() throws Exception {
        if (!validator.isValid(saveManager)) return false;
        GameState state = loadManager.load(saveManager);
        // TODO: ricostruire Franchise da GameState
        return true;
    }

    public Franchise getFranchise() { return franchise; }
}
