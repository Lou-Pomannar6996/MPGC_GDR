package it.unicam.cs.mpgc.rpg119563.view.minigame;

import it.unicam.cs.mpgc.rpg119563.model.era.EraType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Minigioco Cucina: clicca un bottone almeno 20 volte in 5 secondi.
 */
public class KitchenMinigameController implements MinigameController {

    @FXML private Label  characterNameLabel;
    @FXML private Label  timerLabel;
    @FXML private Label  clickLabel;
    @FXML private Button clickButton;
    @FXML private Label  resultLabel;

    private int     timeLeft   = 5;
    private int     clickCount = 0;
    private boolean won        = false;
    private boolean gameOver   = false;
    private Timeline countdown;

    @Override
    public void init(String characterName, EraType eraType) {
        characterNameLabel.setText(characterName + "  —  Cucina");
        updateLabels();
        startCountdown();
    }

    private void startCountdown() {
        countdown = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft--;
            updateLabels();
            if (timeLeft <= 0) endGame();
        }));
        countdown.setCycleCount(5);
        countdown.play();
    }

    private void updateLabels() {
        timerLabel.setText("Tempo: " + timeLeft + "s");
        clickLabel.setText("Clic: " + clickCount + " / 20");
    }

    @FXML
    public void onClickButton() {
        if (!gameOver) {
            clickCount++;
            updateLabels();
        }
    }

    private void endGame() {
        gameOver = true;
        countdown.stop();
        clickButton.setDisable(true);

        won = clickCount >= 20;
        resultLabel.setText(won
            ? "HAI VINTO! (" + clickCount + "/20)"
            : "SCONFITTA — solo " + clickCount + "/20");
        resultLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: "
            + (won ? "#2d7a2d" : "#b00020") + ";");

        new Timeline(new KeyFrame(Duration.seconds(2),
            e -> ((Stage) clickButton.getScene().getWindow()).close())).play();
    }

    @Override
    public boolean isWon() { return won; }
}
