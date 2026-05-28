package it.unicam.cs.mpgc.rpg119563.model.era;

/**
 * Factory per la creazione delle ere di gioco.
 * Aggiungere nuove ere registrandole qui — nessuna modifica al core necessaria.
 */
public class EraFactory {

    private EraFactory() {}

    public static Era create(EraType type) {
        return switch (type) {
            case ANCIENT_EGYPT -> new AncientEgyptEra();
            case ANCIENT_ROME  -> new AncientRomeEra();
            case AMERICA_80S   -> new America80sEra();
            case CHINA_2050    -> new China2050Era();
        };
    }

    /** Restituisce la prima era disponibile. */
    public static Era first() {
        return create(EraType.ANCIENT_EGYPT);
    }
}
