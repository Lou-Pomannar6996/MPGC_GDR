package it.unicam.cs.mpgc.rpg119563.view;

import it.unicam.cs.mpgc.rpg119563.controller.CharacterSelectionController;
import it.unicam.cs.mpgc.rpg119563.controller.GameController;
import it.unicam.cs.mpgc.rpg119563.model.brigade.Brigade;
import it.unicam.cs.mpgc.rpg119563.model.character.*;
import it.unicam.cs.mpgc.rpg119563.model.era.EraFactory;
import it.unicam.cs.mpgc.rpg119563.model.franchise.Franchise;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller JavaFX per la selezione della brigata.
 */
public class CharacterSelectionViewController {

    @FXML private TextField hallNameField;
    @FXML private TextField kitchenNameField;
    @FXML private TextField adminNameField;
    @FXML private Button    confirmButton;

    private final CharacterSelectionController selectionController = new CharacterSelectionController();
    private GameController gameController;

    public void setGameController(GameController gc) {
        this.gameController = gc;
    }

    @FXML
    public void onConfirm() {
        // TODO: leggere valori dai campi e validare input
        HallCharacter    hall    = new HallCharacter(hallNameField.getText(), 1, 5);
        KitchenCharacter kitchen = new KitchenCharacter(kitchenNameField.getText(), 1, 5);
        AdminCharacter   admin   = new AdminCharacter(adminNameField.getText(), 1, 5);

        selectionController.selectHall(hall);
        selectionController.selectKitchen(kitchen);
        selectionController.selectAdmin(admin);

        Brigade brigade = selectionController.buildBrigade();
        Franchise franchise = new Franchise(brigade, EraFactory.first(), 1000, 50);
        gameController.newGame(franchise);

        // TODO: aprire GameView
    }
}
