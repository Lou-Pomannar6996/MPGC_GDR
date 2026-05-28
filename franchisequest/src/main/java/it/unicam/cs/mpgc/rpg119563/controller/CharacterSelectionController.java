package it.unicam.cs.mpgc.rpg119563.controller;

import it.unicam.cs.mpgc.rpg119563.model.brigade.Brigade;
import it.unicam.cs.mpgc.rpg119563.model.character.*;

/**
 * Gestisce la selezione dei personaggi per la brigata.
 * Valida che tutti e tre i ruoli siano assegnati prima di procedere.
 */
public class CharacterSelectionController {

    private HallCharacter    selectedHall;
    private KitchenCharacter selectedKitchen;
    private AdminCharacter   selectedAdmin;

    public void selectHall(HallCharacter c)       { this.selectedHall    = c; }
    public void selectKitchen(KitchenCharacter c) { this.selectedKitchen = c; }
    public void selectAdmin(AdminCharacter c)     { this.selectedAdmin   = c; }

    public boolean isSelectionComplete() {
        return selectedHall != null && selectedKitchen != null && selectedAdmin != null;
    }

    public Brigade buildBrigade() {
        if (!isSelectionComplete()) throw new IllegalStateException("Selezione incompleta");
        return new Brigade(selectedHall, selectedKitchen, selectedAdmin);
    }
}
