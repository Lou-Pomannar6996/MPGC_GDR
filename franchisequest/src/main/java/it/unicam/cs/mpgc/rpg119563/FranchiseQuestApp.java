package it.unicam.cs.mpgc.rpg119563;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point dell'applicazione FranchiseQuest.
 * Inizializza JavaFX e carica la schermata principale.
 */
public class FranchiseQuestApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // TODO: caricare MainMenuView.fxml e impostare la scena
        primaryStage.setTitle("FranchiseQuest");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
