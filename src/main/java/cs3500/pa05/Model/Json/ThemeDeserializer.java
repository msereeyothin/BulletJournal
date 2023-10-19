package cs3500.pa05.Model.Json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import cs3500.pa05.Model.Theme;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Deserialize the theme class from Json Obj to Theme Obj
 */
public class ThemeDeserializer extends JsonDeserializer<Theme> {
  @Override
  public Theme deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    TreeNode node = p.readValueAsTree();
    TextNode name = (TextNode) node.get("name");
    ObjectNode primaryColor = (ObjectNode) node.get("primaryColor");
    ObjectNode secondaryColor = (ObjectNode) node.get("secondaryColor");
    ObjectNode font = (ObjectNode) node.get("font");
    ObjectNode fontColor =  (ObjectNode) node.get("fontColor");

    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(Color.class, new ColorDeserializer());
    module.addDeserializer(Font.class, new FontDeserializer());
    objectMapper.registerModule(module);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    Font themeFont = objectMapper.readValue(font.toString(), new TypeReference<>() {});
    Color primColor = objectMapper.readValue(primaryColor.toString(), new TypeReference<>() {});
    Color secColor = objectMapper.readValue(secondaryColor.toString(), new TypeReference<>() {});
    Color fColor = objectMapper.readValue(fontColor.toString(), new TypeReference<>() {});

    return new Theme(name.toString(), themeFont, primColor, secColor, fColor);
  }
}
