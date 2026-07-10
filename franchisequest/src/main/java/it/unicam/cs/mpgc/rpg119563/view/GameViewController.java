package it.unicam.cs.mpgc.rpg119563.view;

import it.unicam.cs.mpgc.rpg119563.controller.FranchiseController;
import it.unicam.cs.mpgc.rpg119563.controller.GameController;
import it.unicam.cs.mpgc.rpg119563.model.character.Character;
import it.unicam.cs.mpgc.rpg119563.model.character.CharacterRole;
import it.unicam.cs.mpgc.rpg119563.model.era.EraType;
import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;
import it.unicam.cs.mpgc.rpg119563.model.event.HistoricalEvent;
import it.unicam.cs.mpgc.rpg119563.view.minigame.MinigameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller JavaFX per la schermata principale di gioco.
 * Mostra indicatori del franchise e gestisce il turno con minigioco.
 */
public class GameViewController implements GameAware {

    @FXML private Label  eraLabel;
    @FXML private Label  levelLabel;
    @FXML private Label  moneyLabel;
    @FXML private Label  reputationLabel;
    @FXML private Label  eventTitleLabel;
    @FXML private Label  eventDescLabel;
    @FXML private Button nextTurnButton;

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
        HistoricalEvent event = gameController.prepareTurn();
        eventTitleLabel.setText(event.getTitle());
        eventDescLabel.setText(event.getDescription());

        boolean won = openMinigame(
            gameController.getSelectedCharacter(),
            gameController.getFranchise().getCurrentEra().getType(),
            event.getEffect()
        );

        gameController.finalizeTurn(won);
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

    private boolean openMinigame(CharacterRole character, EraType eraType, EventEffect effect) {
        try {
            String fxml = switch (character.getRoleType()) {
                case HALL    -> "/it/unicam/cs/mpgc/rpg119563/view/HallMinigame.fxml";
                case KITCHEN -> "/it/unicam/cs/mpgc/rpg119563/view/KitchenMinigame.fxml";
                case ADMIN   -> "/it/unicam/cs/mpgc/rpg119563/view/AdminMinigame.fxml";
            };

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Minigioco — " + ((Character) character).getName());
            stage.setScene(new Scene(loader.load()));
            stage.setResizable(false);

            MinigameController mc = loader.getController();
            mc.init(((Character) character).getName(), eraType, effect);

            stage.showAndWait();
            return mc.isWon();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Errore apertura minigioco: " + e.getMessage()).showAndWait();
            return false;
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
