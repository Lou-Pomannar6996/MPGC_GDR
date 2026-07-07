package it.unicam.cs.mpgc.rpg119563.controller;

import it.unicam.cs.mpgc.rpg119563.model.character.CharacterRole;
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
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Coordina il flusso di gioco: turni, eventi, aggiornamento indicatori, persistenza.
 * Dipende da interfacce — non da implementazioni concrete (DIP).
 */
public class GameController {

    private Franchise         franchise;
    private final EventEngine eventEngine    = new EventEngine();
    private final XmlSaveManager saveManager = new XmlSaveManager();
    private final XmlLoadManager loadManager = new XmlLoadManager();
    private final SaveFileValidator validator = new SaveFileValidator();
    private final Random random              = new Random();

    private HistoricalEvent pendingEvent;
    private CharacterRole   selectedCharacter;

    public void newGame(Franchise franchise) {
        this.franchise = franchise;
    }

    /**
     * Prima fase del turno: estrae l'evento e il personaggio casuale.
     * Non applica ancora nulla — attende il risultato del minigioco.
     */
    public HistoricalEvent prepareTurn() {
        pendingEvent = eventEngine.pickEvent(franchise.getCurrentEra());
        List<CharacterRole> characters = franchise.getBrigade().getCharacters();
        selectedCharacter = characters.get(random.nextInt(characters.size()));
        return pendingEvent;
    }

    /** Restituisce il personaggio estratto nel prepareTurn corrente. */
    public CharacterRole getSelectedCharacter() { return selectedCharacter; }

    /**
     * Seconda fase del turno: applica l'effetto modificato dal risultato del minigioco
     * e fa avanzare il livello.
     */
    public void finalizeTurn(boolean won) {
        franchise.applyEffectDirect(pendingEvent.getEffect().withMinigameResult(won));
        franchise.levelUp();
    }

    public boolean canAdvanceEra() {
        return franchise.canAdvanceEra();
    }

    public void advanceEra(EraType nextEraType) {
        Era nextEra = EraFactory.create(nextEraType);
        franchise.advanceEra(nextEra);
    }

    public void saveGame() throws Exception {
        saveManager.save(GameState.from(franchise));
    }

    public boolean loadGame() throws Exception {
        if (!validator.isValid(saveManager)) return false;
        GameState state = loadManager.load(saveManager);
        Era era = EraFactory.create(state.getEraType());
        this.franchise = new Franchise(state.getBrigade(), era, state.getMoney(), state.getReputation());
        this.franchise.setLevel(state.getLevel());
        return true;
    }

    /** Restituisce il tipo dell'era successiva, o empty se siamo all'ultima. */
    public Optional<EraType> nextEraType() {
        EraType current = franchise.getCurrentEra().getType();
        EraType[] all   = EraType.values();
        int next        = current.ordinal() + 1;
        return next < all.length ? Optional.of(all[next]) : Optional.empty();
    }

    public Franchise getFranchise() { return franchise; }
}
