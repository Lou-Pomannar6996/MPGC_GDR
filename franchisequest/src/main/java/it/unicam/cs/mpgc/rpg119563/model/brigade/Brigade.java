package it.unicam.cs.mpgc.rpg119563.model.brigade;

import it.unicam.cs.mpgc.rpg119563.model.character.*;

/**
 * Aggregato dei tre personaggi della brigata (uno per ruolo).
 * Garantisce che ogni slot sia sempre occupato.
 */
public class Brigade {

    private HallCharacter    hallCharacter;
    private KitchenCharacter kitchenCharacter;
    private AdminCharacter   adminCharacter;

    public Brigade(HallCharacter hall, KitchenCharacter kitchen, AdminCharacter admin) {
        if (hall == null || kitchen == null || admin == null)
            throw new IllegalArgumentException("Tutti i ruoli della brigata devono essere assegnati");
        this.hallCharacter    = hall;
        this.kitchenCharacter = kitchen;
        this.adminCharacter   = admin;
    }

    public HallCharacter    getHallCharacter()    { return hallCharacter; }
    public KitchenCharacter getKitchenCharacter() { return kitchenCharacter; }
    public AdminCharacter   getAdminCharacter()   { return adminCharacter; }

    public void setHallCharacter(HallCharacter hall)          { this.hallCharacter = hall; }
    public void setKitchenCharacter(KitchenCharacter kitchen) { this.kitchenCharacter = kitchen; }
    public void setAdminCharacter(AdminCharacter admin)       { this.adminCharacter = admin; }
}
