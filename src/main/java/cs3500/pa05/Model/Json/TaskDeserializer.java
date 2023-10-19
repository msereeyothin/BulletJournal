package cs3500.pa05.Model.Json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.Model.Task;
import java.io.IOException;
import javafx.scene.Node;

/**
 * Deserialize Task Object
 */
public class TaskDeserializer extends JsonDeserializer<Task> {
  @Override
  public Task deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    TreeNode node = p.readValueAsTree();
    Node name = (Node) node.get("name");
    Node description = (Node) node.get("description");
    Node complete = (Node) node.get("complete");

    ObjectMapper objectMapper = new ObjectMapper();
    Boolean completed = objectMapper.readValue(complete.toString(), new TypeReference<>() {});

    return new Task(name.toString(), description.toString(), completed);
  }
}
