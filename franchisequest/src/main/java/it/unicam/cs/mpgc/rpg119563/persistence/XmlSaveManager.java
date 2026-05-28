package it.unicam.cs.mpgc.rpg119563.persistence;

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

        Element root = doc.createElement("gameState");
        doc.appendChild(root);

        appendTextElement(doc, root, "era",        state.getEraType().name());
        appendTextElement(doc, root, "level",      String.valueOf(state.getLevel()));
        appendTextElement(doc, root, "money",      String.valueOf(state.getMoney()));
        appendTextElement(doc, root, "reputation", String.valueOf(state.getReputation()));
        // TODO: serializzare brigade

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(getSavePath().toFile()));
    }

    private void appendTextElement(Document doc, Element parent, String tag, String value) {
        Element el = doc.createElement(tag);
        el.setTextContent(value);
        parent.appendChild(el);
    }
}
