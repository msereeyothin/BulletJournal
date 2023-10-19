package cs3500.pa05.Model.Json;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.NumericNode;
import java.io.IOException;
import javafx.scene.paint.Color;

/**
 * Deserialize Color Object
 */
public class ColorDeserializer extends JsonDeserializer<Color> {

  @Override
  public Color deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    TreeNode node = p.readValueAsTree();
    NumericNode red = (NumericNode) node.get("red");
    NumericNode green = (NumericNode) node.get("green");
    NumericNode blue = (NumericNode) node.get("blue");
    NumericNode opacity = (NumericNode) node.get("opacity");

    return Color.color(red.doubleValue(), green.doubleValue(), blue.doubleValue(),
        opacity.doubleValue());
  }
}

