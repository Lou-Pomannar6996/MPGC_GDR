package it.unicam.cs.mpgc.rpg119563.view;

import it.unicam.cs.mpgc.rpg119563.controller.CharacterSelectionController;
import it.unicam.cs.mpgc.rpg119563.controller.GameController;
import it.unicam.cs.mpgc.rpg119563.model.brigade.Brigade;
import it.unicam.cs.mpgc.rpg119563.model.character.*;
import it.unicam.cs.mpgc.rpg119563.model.era.EraFactory;
import it.unicam.cs.mpgc.rpg119563.model.franchise.Franchise;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controller JavaFX per la selezione della brigata.
 */
public class CharacterSelectionViewController implements GameAware {

    @FXML private TextField hallNameField;
    @FXML private TextField kitchenNameField;
    @FXML private TextField adminNameField;
    @FXML private Button    confirmButton;

    private final CharacterSelectionController selectionController = new CharacterSelectionController();
    private GameController gameController;

    @Override
    public void setGameController(GameController gc) {
        this.gameController = gc;
    }

    @FXML
    public void onConfirm() {
        String hallName    = hallNameField.getText().trim();
        String kitchenName = kitchenNameField.getText().trim();
        String adminName   = adminNameField.getText().trim();

        if (hallName.isEmpty() || kitchenName.isEmpty() || adminName.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Tutti i nomi devono essere compilati.").showAndWait();
            return;
        }

        selectionController.selectHall(new HallCharacter(hallName, 1, 5));
        selectionController.selectKitchen(new KitchenCharacter(kitchenName, 1, 5));
        selectionController.selectAdmin(new AdminCharacter(adminName, 1, 5));

        Brigade   brigade   = selectionController.buildBrigade();
        Franchise franchise = new Franchise(brigade, EraFactory.first(), 1000, 50);
        gameController.newGame(franchise);

        try {
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            SceneNavigator.goTo(stage, SceneNavigator.GAME_VIEW, gameController);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
