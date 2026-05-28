package it.unicam.cs.mpgc.rpg119563.persistence;

import it.unicam.cs.mpgc.rpg119563.model.era.EraType;
import it.unicam.cs.mpgc.rpg119563.model.state.GameState;
import org.w3c.dom.*;
import javax.xml.parsers.*;

/**
 * Deserializza un file XML e ricostruisce il GameState.
 */
public class XmlLoadManager {

    public GameState load(XmlSaveManager saveManager) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(saveManager.getSavePath().toFile());
        doc.getDocumentElement().normalize();

        EraType eraType    = EraType.valueOf(getText(doc, "era"));
        int     level      = Integer.parseInt(getText(doc, "level"));
        int     money      = Integer.parseInt(getText(doc, "money"));
        int     reputation = Integer.parseInt(getText(doc, "reputation"));
        // TODO: deserializzare brigade

        return new GameState(eraType, level, money, reputation);
    }

    private String getText(Document doc, String tag) {
        return doc.getElementsByTagName(tag).item(0).getTextContent();
    }
}
