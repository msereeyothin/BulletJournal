package cs3500.pa05.Model.Json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import javafx.scene.text.Font;

/**
 * Deserialize Font Object
 */
public class FontDeserializer extends JsonDeserializer<Font> {
  @Override
  public Font deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    TreeNode node = p.readValueAsTree();
    TextNode family = (TextNode) node.get("family");
    NumericNode size = (NumericNode) node.get("size");

    return Font.font(family.toString(), size.doubleValue());
  }
}
