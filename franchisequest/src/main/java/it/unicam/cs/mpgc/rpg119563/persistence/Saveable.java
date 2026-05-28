package it.unicam.cs.mpgc.rpg119563.persistence;

/**
 * Contratto per oggetti che possono essere serializzati/deserializzati in XML.
 */
public interface Saveable {
    void save(XmlSaveManager manager);
    void load(XmlLoadManager manager);
}
