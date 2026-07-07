package it.unicam.cs.mpgc.rpg119563.view.minigame;

import it.unicam.cs.mpgc.rpg119563.model.era.EraType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

/**
 * Minigioco Sala: clicca almeno 5 bottoni in posizioni casuali entro 5 secondi.
 */
public class HallMinigameController implements MinigameController {

    @FXML private Label      characterNameLabel;
    @FXML private Label      timerLabel;
    @FXML private Label      scoreLabel;
    @FXML private AnchorPane gameArea;
    @FXML private Label      resultLabel;

    private int     timeLeft   = 5;
    private int     clickCount = 0;
    private boolean won        = false;
    private boolean gameOver   = false;

    private final Random random = new Random();
    private Timeline countdown;
    private Button   activeButton;

    @Override
    public void init(String characterName, EraType eraType) {
        characterNameLabel.setText(characterName + "  —  Sala");
        updateLabels();
        spawnButton();
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
        scoreLabel.setText("Clic: " + clickCount + " / 5");
    }

    private void spawnButton() {
        if (gameOver) return;
        if (activeButton != null) gameArea.getChildren().remove(activeButton);

        Button btn = new Button("CLICCA!");
        btn.setPrefSize(80, 50);

        double maxX = gameArea.getPrefWidth()  - 90;
        double maxY = gameArea.getPrefHeight() - 60;
        AnchorPane.setLeftAnchor(btn, random.nextDouble() * Math.max(10, maxX));
        AnchorPane.setTopAnchor (btn, random.nextDouble() * Math.max(10, maxY));

        btn.setOnAction(e -> {
            if (!gameOver) {
                clickCount++;
                updateLabels();
                spawnButton();
            }
        });

        activeButton = btn;
        gameArea.getChildren().add(btn);
    }

    private void endGame() {
        gameOver = true;
        countdown.stop();
        if (activeButton != null) activeButton.setDisable(true);

        won = clickCount >= 5;
        resultLabel.setText(won
            ? "HAI VINTO! (" + clickCount + "/5)"
            : "SCONFITTA — solo " + clickCount + "/5");
        resultLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: "
            + (won ? "#2d7a2d" : "#b00020") + ";");

        new Timeline(new KeyFrame(Duration.seconds(2),
            e -> ((Stage) characterNameLabel.getScene().getWindow()).close())).play();
    }

    @Override
    public boolean isWon() { return won; }
}
