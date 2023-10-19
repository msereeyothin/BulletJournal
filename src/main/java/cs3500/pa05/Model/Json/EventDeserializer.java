package cs3500.pa05.Model.Json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import cs3500.pa05.Model.Event;
import java.io.IOException;
import javafx.scene.Node;

/**
 * Deserialize Event Object
 */
public class EventDeserializer extends JsonDeserializer<Event> {
  @Override
  public Event deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    TreeNode node = p.readValueAsTree();
    Node name = (Node) node.get("name");
    Node startTime = (Node) node.get("startTime");
    Node duration = (Node) node.get("duration");
    Node description = (Node) node.get("description");

    return new Event(name.toString(), startTime.toString(), duration.toString(),
        description.toString());
  }
}
