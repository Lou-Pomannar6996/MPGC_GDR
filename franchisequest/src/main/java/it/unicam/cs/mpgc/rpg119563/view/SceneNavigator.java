package it.unicam.cs.mpgc.rpg119563.view;

import it.unicam.cs.mpgc.rpg119563.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Gestisce la navigazione tra le schermate JavaFX.
 * Carica l'FXML, costruisce la scena e inietta il GameController
 * nei controller che implementano GameAware.
 */
public class SceneNavigator {

    private static final String BASE = "/it/unicam/cs/mpgc/rpg119563/view/";

    public static final String MAIN_MENU           = BASE + "MainMenu.fxml";
    public static final String CHARACTER_SELECTION = BASE + "CharacterSelection.fxml";
    public static final String GAME_VIEW           = BASE + "GameView.fxml";

    private SceneNavigator() {}

    public static void goTo(Stage stage, String fxmlPath, GameController gc) throws Exception {
        FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        Object controller = loader.getController();
        if (controller instanceof GameAware aware) {
            aware.setGameController(gc);
        }
        stage.setScene(scene);
    }
}
