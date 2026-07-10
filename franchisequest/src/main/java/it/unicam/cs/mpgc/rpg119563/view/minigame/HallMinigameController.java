package it.unicam.cs.mpgc.rpg119563.view.minigame;

import it.unicam.cs.mpgc.rpg119563.model.era.EraType;
import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

/**
 * Minigioco Sala: clicca almeno 5 bottoni in posizioni casuali entro 5 secondi.
 */
public class HallMinigameController implements MinigameController {

    // Intro panel
    @FXML private VBox  introPanel;
    @FXML private Label characterNameLabel;
    @FXML private Label eventInfoLabel;
    @FXML private Label rulesLabel;

    // Game panel
    @FXML private VBox       gamePanel;
    @FXML private Label      timerLabel;
    @FXML private Label      scoreLabel;
    @FXML private AnchorPane gameArea;
    @FXML private Label      resultLabel;

    private EventEffect effect;
    private int         timeLeft   = 5;
    private int         clickCount = 0;
    private boolean     won        = false;
    private boolean     gameOver   = false;

    private final Random random = new Random();
    private Timeline countdown;
    private Button   activeButton;

    @Override
    public void init(String characterName, EraType eraType, EventEffect effect) {
        this.effect = effect;
        characterNameLabel.setText(characterName + "  —  Sala");
        eventInfoLabel.setText("Evento: " + MinigameUtils.formatEffect(effect));
        rulesLabel.setText(MinigameUtils.buildRulesText(effect));
        updateLabels();
    }

    @FXML
    public void onStart() {
        introPanel.setVisible(false);
        introPanel.setManaged(false);
        gamePanel.setVisible(true);
        gamePanel.setManaged(true);
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
        btn.setPrefSize(90, 55);

        double maxX = gameArea.getPrefWidth()  - 100;
        double maxY = gameArea.getPrefHeight() - 65;
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
        resultLabel.setText(MinigameUtils.buildResultText(won, effect)
            + "  (" + clickCount + "/5 clic)");
        resultLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: "
            + (won ? "#2d7a2d" : "#b00020") + ";");

        new Timeline(new KeyFrame(Duration.seconds(2.5),
            e -> ((Stage) timerLabel.getScene().getWindow()).close())).play();
    }

    @Override
    public boolean isWon() { return won; }
}
