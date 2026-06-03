package it.unicam.cs.mpgc.rpg119563.persistence;

import it.unicam.cs.mpgc.rpg119563.model.brigade.Brigade;
import it.unicam.cs.mpgc.rpg119563.model.character.*;
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
        Brigade brigade    = loadBrigade(doc);

        return new GameState(eraType, level, money, reputation, brigade);
    }

    private Brigade loadBrigade(Document doc) {
        NodeList nodes = doc.getElementsByTagName("character");
        HallCharacter    hall    = null;
        KitchenCharacter kitchen = null;
        AdminCharacter   admin   = null;

        for (int i = 0; i < nodes.getLength(); i++) {
            Element el   = (Element) nodes.item(i);
            String  name = el.getAttribute("name");
            int     lv   = Integer.parseInt(el.getAttribute("level"));
            int     stat = Integer.parseInt(el.getAttribute("stat"));

            switch (RoleType.valueOf(el.getAttribute("role"))) {
                case HALL    -> hall    = new HallCharacter(name, lv, stat);
                case KITCHEN -> kitchen = new KitchenCharacter(name, lv, stat);
                case ADMIN   -> admin   = new AdminCharacter(name, lv, stat);
            }
        }
        return new Brigade(hall, kitchen, admin);
    }

    private String getText(Document doc, String tag) {
        return doc.getElementsByTagName(tag).item(0).getTextContent();
    }
}
