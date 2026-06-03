package it.unicam.cs.mpgc.rpg119563.view;

import it.unicam.cs.mpgc.rpg119563.controller.FranchiseController;
import it.unicam.cs.mpgc.rpg119563.controller.GameController;
import it.unicam.cs.mpgc.rpg119563.model.era.EraType;
import it.unicam.cs.mpgc.rpg119563.model.event.HistoricalEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller JavaFX per la schermata principale di gioco.
 * Mostra indicatori del franchise e gestisce il turno.
 */
public class GameViewController implements GameAware {

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

    @Override
    public void setGameController(GameController gc) {
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
            gameController.nextEraType().ifPresentOrElse(
                this::showAdvanceEraDialog,
                () -> {
                    new Alert(Alert.AlertType.INFORMATION, "Hai completato tutte le ere. Fine del gioco!").showAndWait();
                    nextTurnButton.setDisable(true);
                }
            );
        }
    }

    @FXML
    public void onSave() {
        try {
            gameController.saveGame();
            new Alert(Alert.AlertType.INFORMATION, "Partita salvata.").showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Errore nel salvataggio: " + e.getMessage()).showAndWait();
        }
    }

    private void showAdvanceEraDialog(EraType next) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Hai completato l'era " + franchiseController.getEraName() + "!");
        alert.setContentText("Vuoi avanzare all'era successiva?");
        alert.showAndWait().ifPresent(button -> {
            if (button == ButtonType.OK) {
                gameController.advanceEra(next);
                refreshUI();
            }
        });
    }

    private void refreshUI() {
        eraLabel.setText(franchiseController.getEraName());
        levelLabel.setText("Livello: " + franchiseController.getLevel());
        moneyLabel.setText("💰 " + franchiseController.getMoney());
        reputationLabel.setText("⭐ " + franchiseController.getReputation());
    }
}
