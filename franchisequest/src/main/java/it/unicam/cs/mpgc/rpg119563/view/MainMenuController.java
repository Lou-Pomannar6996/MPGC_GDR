package it.unicam.cs.mpgc.rpg119563.view;

import it.unicam.cs.mpgc.rpg119563.controller.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller JavaFX per la schermata principale.
 * NOTA: questo è collante UI — nessuna logica di gioco qui.
 */
public class MainMenuController {

    @FXML private Button newGameButton;
    @FXML private Button loadGameButton;
    @FXML private Button exitButton;

    private final GameController gameController = new GameController();

    @FXML
    public void onNewGame() {
        // TODO: aprire CharacterSelectionView
    }

    @FXML
    public void onLoadGame() {
        try {
            boolean loaded = gameController.loadGame();
            if (!loaded) {
                // TODO: mostrare dialog "salvataggio non trovato o corrotto"
            } else {
                // TODO: aprire GameView
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
