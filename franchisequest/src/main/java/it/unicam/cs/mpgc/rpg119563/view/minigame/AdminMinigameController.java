package it.unicam.cs.mpgc.rpg119563.view.minigame;

import it.unicam.cs.mpgc.rpg119563.model.era.EraType;
import it.unicam.cs.mpgc.rpg119563.model.event.EventEffect;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

/**
 * Minigioco Amministrazione: quiz a risposta singola sul periodo storico corrente.
 */
public class AdminMinigameController implements MinigameController {

    // Intro panel
    @FXML private VBox  introPanel;
    @FXML private Label characterNameLabel;
    @FXML private Label eventInfoLabel;
    @FXML private Label rulesLabel;

    // Game panel
    @FXML private VBox   gamePanel;
    @FXML private Label  questionLabel;
    @FXML private Button answerA;
    @FXML private Button answerB;
    @FXML private Button answerC;
    @FXML private Button answerD;
    @FXML private Label  resultLabel;

    private EventEffect  effect;
    private boolean      won = false;
    private int          correctButtonIndex;
    private List<Button> answerButtons;

    private record QuizQuestion(String question, String[] options) {
        // options[0] è sempre la risposta corretta — viene mescolata in init()
    }

    private static final Map<EraType, List<QuizQuestion>> QUESTIONS = new EnumMap<>(EraType.class);

    static {
        QUESTIONS.put(EraType.ANCIENT_EGYPT, List.of(
            new QuizQuestion("Quale fiume era al centro dell'Antico Egitto?",
                new String[]{"Il Nilo", "Il Tigri", "L'Eufrate", "Il Gange"}),
            new QuizQuestion("Quale faraone costruì la Grande Piramide di Giza?",
                new String[]{"Cheope", "Ramesse II", "Tutankhamon", "Akhenaton"}),
            new QuizQuestion("Come si chiamava la scrittura dell'Antico Egitto?",
                new String[]{"Geroglifici", "Cuneiforme", "Alfabeto fenicio", "Rune"})
        ));
        QUESTIONS.put(EraType.ANCIENT_ROME, List.of(
            new QuizQuestion("In che anno cadde l'Impero Romano d'Occidente?",
                new String[]{"476 d.C.", "410 d.C.", "500 d.C.", "380 d.C."}),
            new QuizQuestion("Chi fu il primo imperatore romano?",
                new String[]{"Augusto", "Giulio Cesare", "Nerone", "Caligola"}),
            new QuizQuestion("Come si chiamava la grande arena di Roma?",
                new String[]{"Colosseo", "Circo Massimo", "Pantheon", "Foro Romano"})
        ));
        QUESTIONS.put(EraType.AMERICA_80S, List.of(
            new QuizQuestion("Chi era il presidente degli USA negli anni '80?",
                new String[]{"Ronald Reagan", "George Bush", "Jimmy Carter", "Bill Clinton"}),
            new QuizQuestion("Quale console Nintendo fu lanciata nel 1983?",
                new String[]{"NES (Famicom)", "Super Nintendo", "Nintendo 64", "Game Boy"}),
            new QuizQuestion("Come si chiamava la politica economica di Reagan?",
                new String[]{"Reaganomics", "New Deal", "Great Society", "Marshall Plan"})
        ));
        QUESTIONS.put(EraType.CHINA_2050, List.of(
            new QuizQuestion("Quale tecnologia è considerata dominante entro il 2050?",
                new String[]{"Intelligenza artificiale", "Vapore", "Energia a carbone", "Motore a scoppio"}),
            new QuizQuestion("Cosa si intende per 'economia circolare'?",
                new String[]{"Riciclo e riuso delle risorse", "Banca centrale digitale", "Mercato globale", "Esportazioni nette"}),
            new QuizQuestion("Come si chiama il principale sistema di pagamento digitale in Cina?",
                new String[]{"WeChat Pay", "PayPal", "Apple Pay", "Google Pay"})
        ));
    }

    @Override
    public void init(String characterName, EraType eraType, EventEffect effect) {
        this.effect = effect;
        characterNameLabel.setText(characterName + "  —  Amministrazione");
        eventInfoLabel.setText("Evento: " + MinigameUtils.formatEffect(effect));
        rulesLabel.setText(MinigameUtils.buildRulesText(effect));

        answerButtons = List.of(answerA, answerB, answerC, answerD);

        List<QuizQuestion> pool = QUESTIONS.getOrDefault(eraType, QUESTIONS.get(EraType.ANCIENT_EGYPT));
        QuizQuestion q = pool.get(new Random().nextInt(pool.size()));
        questionLabel.setText(q.question());

        List<Integer> order = new ArrayList<>(List.of(0, 1, 2, 3));
        Collections.shuffle(order);
        for (int i = 0; i < 4; i++) {
            answerButtons.get(i).setText(q.options()[order.get(i)]);
            if (order.get(i) == 0) correctButtonIndex = i;
        }
    }

    @FXML
    public void onStart() {
        introPanel.setVisible(false);
        introPanel.setManaged(false);
        gamePanel.setVisible(true);
        gamePanel.setManaged(true);
    }

    @FXML
    public void onAnswer(ActionEvent e) {
        int chosen = answerButtons.indexOf((Button) e.getSource());
        won = chosen == correctButtonIndex;

        answerButtons.forEach(b -> b.setDisable(true));
        answerButtons.get(correctButtonIndex)
            .setStyle("-fx-background-color: #a8e6a3; -fx-font-weight: bold;");
        if (!won)
            ((Button) e.getSource()).setStyle("-fx-background-color: #f5a0a0;");

        resultLabel.setText(MinigameUtils.buildResultText(won, effect));
        resultLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: "
            + (won ? "#2d7a2d" : "#b00020") + ";");

        new Timeline(new KeyFrame(Duration.seconds(2.5),
            ev -> ((Stage) questionLabel.getScene().getWindow()).close())).play();
    }

    @Override
    public boolean isWon() { return won; }
}
