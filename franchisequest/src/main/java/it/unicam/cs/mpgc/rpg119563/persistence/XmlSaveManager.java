package it.unicam.cs.mpgc.rpg119563.persistence;

import it.unicam.cs.mpgc.rpg119563.model.brigade.Brigade;
import it.unicam.cs.mpgc.rpg119563.model.character.AdminCharacter;
import it.unicam.cs.mpgc.rpg119563.model.character.HallCharacter;
import it.unicam.cs.mpgc.rpg119563.model.character.KitchenCharacter;
import it.unicam.cs.mpgc.rpg119563.model.state.GameState;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.nio.file.Path;

/**
 * Serializza lo stato di gioco in XML.
 * Struttura: &lt;savegame version="1"&gt;&lt;gameState&gt;...&lt;brigade&gt;&lt;character .../&gt;&lt;/brigade&gt;&lt;/gameState&gt;&lt;/savegame&gt;
 * Il file viene scritto in: {user.home}/.franchisequest/savegame.xml
 */
public class XmlSaveManager {

    private static final String SAVE_DIR  = ".franchisequest";
    private static final String SAVE_FILE = "savegame.xml";

    public Path getSavePath() {
        return Path.of(System.getProperty("user.home"), SAVE_DIR, SAVE_FILE);
    }

    public void save(GameState state) throws Exception {
        File dir = getSavePath().getParent().toFile();
        if (!dir.exists()) dir.mkdirs();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.newDocument();

        Element savegame = doc.createElement("savegame");
        savegame.setAttribute("version", "1");
        doc.appendChild(savegame);

        Element gameState = doc.createElement("gameState");
        savegame.appendChild(gameState);

        appendTextElement(doc, gameState, "era",        state.getEraType().name());
        appendTextElement(doc, gameState, "level",      String.valueOf(state.getLevel()));
        appendTextElement(doc, gameState, "money",      String.valueOf(state.getMoney()));
        appendTextElement(doc, gameState, "reputation", String.valueOf(state.getReputation()));

        gameState.appendChild(buildBrigadeElement(doc, state.getBrigade()));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(getSavePath().toFile()));
    }

    private Element buildBrigadeElement(Document doc, Brigade brigade) {
        Element brigadeEl = doc.createElement("brigade");

        HallCharacter    hall    = brigade.getHallCharacter();
        KitchenCharacter kitchen = brigade.getKitchenCharacter();
        AdminCharacter   admin   = brigade.getAdminCharacter();

        brigadeEl.appendChild(characterElement(doc, hall.getRoleType().name(),    hall.getName(),    hall.getLevel(),    hall.getCharisma()));
        brigadeEl.appendChild(characterElement(doc, kitchen.getRoleType().name(), kitchen.getName(), kitchen.getLevel(), kitchen.getSkill()));
        brigadeEl.appendChild(characterElement(doc, admin.getRoleType().name(),   admin.getName(),   admin.getLevel(),   admin.getManagement()));

        return brigadeEl;
    }

    private Element characterElement(Document doc, String role, String name, int level, int stat) {
        Element el = doc.createElement("character");
        el.setAttribute("role",  role);
        el.setAttribute("name",  name);
        el.setAttribute("level", String.valueOf(level));
        el.setAttribute("stat",  String.valueOf(stat));
        return el;
    }

    private void appendTextElement(Document doc, Element parent, String tag, String value) {
        Element el = doc.createElement(tag);
        el.setTextContent(value);
        parent.appendChild(el);
    }
}
