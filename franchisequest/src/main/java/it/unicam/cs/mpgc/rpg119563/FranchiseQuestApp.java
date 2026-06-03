package it.unicam.cs.mpgc.rpg119563;

import it.unicam.cs.mpgc.rpg119563.controller.GameController;
import it.unicam.cs.mpgc.rpg119563.view.SceneNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point dell'applicazione FranchiseQuest.
 * Crea il GameController e carica la schermata principale.
 */
public class FranchiseQuestApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameController gc = new GameController();
        primaryStage.setTitle("FranchiseQuest");
        SceneNavigator.goTo(primaryStage, SceneNavigator.MAIN_MENU, gc);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
