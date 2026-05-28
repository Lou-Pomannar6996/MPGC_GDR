package it.unicam.cs.mpgc.rpg119563.persistence;

import java.nio.file.Files;

/**
 * Verifica l'integrità del file di salvataggio prima del caricamento.
 * In caso di file corrotto o assente, il GameController proporrà il reset.
 */
public class SaveFileValidator {

    public boolean saveExists(XmlSaveManager saveManager) {
        return Files.exists(saveManager.getSavePath());
    }

    public boolean isValid(XmlSaveManager saveManager) {
        if (!saveExists(saveManager)) return false;
        try {
            new XmlLoadManager().load(saveManager);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
