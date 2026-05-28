package it.unicam.cs.mpgc.rpg119563.model.character;

/**
 * Entità base di ogni personaggio della brigata.
 * Contiene attributi comuni: nome e livello.
 * Le statistiche di ruolo sono delegate alle sottoclassi.
 */
public abstract class Character implements CharacterRole {

    private final String name;
    private int level;

    protected Character(String name, int level) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome non valido");
        if (level < 1) throw new IllegalArgumentException("Livello deve essere >= 1");
        this.name = name;
        this.level = level;
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    @Override
    public String toString() {
        return "[" + getRoleType() + "] " + name + " (Lv." + level + ")";
    }
}
