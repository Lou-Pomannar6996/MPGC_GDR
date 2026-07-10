package it.unicam.cs.mpgc.rpg119563.view.minigame;

import it.unicam.cs.mpgc.rpg119563.model.era.EraType;
import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Minigioco Cucina: clicca un bottone almeno 20 volte in 5 secondi.
 */
public class KitchenMinigameController implements MinigameController {

    // Intro panel
    @FXML private VBox  introPanel;
    @FXML private Label characterNameLabel;
    @FXML private Label eventInfoLabel;
    @FXML private Label rulesLabel;

    // Game panel
    @FXML private VBox   gamePanel;
    @FXML private Label  timerLabel;
    @FXML private Label  clickLabel;
    @FXML private Button clickButton;
    @FXML private Label  resultLabel;

    private EventEffect effect;
    private int     timeLeft   = 5;
    private int     clickCount = 0;
    private boolean won        = false;
    private boolean gameOver   = false;
    private Timeline countdown;

    @Override
    public void init(String characterName, EraType eraType, EventEffect effect) {
        this.effect = effect;
        characterNameLabel.setText(characterName + "  —  Cucina");
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
        resultLabel.setText(MinigameUtils.buildResultText(won, effect)
            + "  (" + clickCount + "/20 clic)");
        resultLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: "
            + (won ? "#2d7a2d" : "#b00020") + ";");

        new Timeline(new KeyFrame(Duration.seconds(2.5),
            e -> ((Stage) clickButton.getScene().getWindow()).close())).play();
    }

    @Override
    public boolean isWon() { return won; }
}
