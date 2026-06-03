package it.unicam.cs.mpgc.rpg119563.view;

import it.unicam.cs.mpgc.rpg119563.controller.GameController;

/**
 * Interfaccia per i controller JavaFX che necessitano del GameController.
 * Permette a SceneNavigator di iniettare il controller dopo il caricamento FXML.
 */
public interface GameAware {
    void setGameController(GameController gc);
}
