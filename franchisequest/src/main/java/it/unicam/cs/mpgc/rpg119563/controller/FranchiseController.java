package it.unicam.cs.mpgc.rpg119563.controller;

import it.unicam.cs.mpgc.rpg119563.model.franchise.Franchise;

/**
 * Gestisce le azioni sul franchise e aggiorna la View.
 * Punto di contatto tra la logica di gioco e i componenti JavaFX.
 */
public class FranchiseController {

    private final Franchise franchise;

    public FranchiseController(Franchise franchise) {
        this.franchise = franchise;
    }

    public int  getMoney()      { return franchise.getMoney(); }
    public int  getReputation() { return franchise.getReputation(); }
    public int  getLevel()      { return franchise.getLevel(); }
    public String getEraName()  { return franchise.getCurrentEra().getName(); }
}
