package it.unicam.cs.mpgc.rpg119563.view;

import it.unicam.cs.mpgc.rpg119563.controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller JavaFX per la schermata principale.
 * NOTA: questo è collante UI — nessuna logica di gioco qui.
 */
public class MainMenuController implements GameAware {

    @FXML private Button newGameButton;
    @FXML private Button loadGameButton;
    @FXML private Button exitButton;

    private GameController gameController;

    @Override
    public void setGameController(GameController gc) {
        this.gameController = gc;
    }

    @FXML
    public void onNewGame() {
        try {
            Stage stage = (Stage) newGameButton.getScene().getWindow();
            SceneNavigator.goTo(stage, SceneNavigator.CHARACTER_SELECTION, gameController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onLoadGame() {
        try {
            boolean loaded = gameController.loadGame();
            if (!loaded) {
                new Alert(Alert.AlertType.WARNING, "Salvataggio non trovato o corrotto.").showAndWait();
            } else {
                Stage stage = (Stage) loadGameButton.getScene().getWindow();
                SceneNavigator.goTo(stage, SceneNavigator.GAME_VIEW, gameController);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
