package it.unicam.cs.mpgc.rpg119563.view;

import it.unicam.cs.mpgc.rpg119563.controller.FranchiseController;
import it.unicam.cs.mpgc.rpg119563.controller.GameController;
import it.unicam.cs.mpgc.rpg119563.model.event.HistoricalEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller JavaFX per la schermata principale di gioco.
 * Mostra indicatori del franchise e gestisce il turno.
 */
public class GameViewController {

    @FXML private Label  eraLabel;
    @FXML private Label  levelLabel;
    @FXML private Label  moneyLabel;
    @FXML private Label  reputationLabel;
    @FXML private Label  eventTitleLabel;
    @FXML private Label  eventDescLabel;
    @FXML private Button nextTurnButton;
    @FXML private Button saveButton;

    private GameController      gameController;
    private FranchiseController franchiseController;

    public void init(GameController gc) {
        this.gameController      = gc;
        this.franchiseController = new FranchiseController(gc.getFranchise());
        refreshUI();
    }

    @FXML
    public void onNextTurn() {
        HistoricalEvent event = gameController.nextTurn();
        eventTitleLabel.setText(event.getTitle());
        eventDescLabel.setText(event.getDescription());
        refreshUI();

        if (gameController.canAdvanceEra()) {
            // TODO: mostrare dialog avanzamento era
        }
    }

    @FXML
    public void onSave() {
        try {
            // TODO: passare EraType corrente
            // gameController.saveGame(currentEraType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshUI() {
        eraLabel.setText(franchiseController.getEraName());
        levelLabel.setText("Livello: " + franchiseController.getLevel());
        moneyLabel.setText("💰 " + franchiseController.getMoney());
        reputationLabel.setText("⭐ " + franchiseController.getReputation());
    }
}
