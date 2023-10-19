package cs3500.pa05.Model.Json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import cs3500.pa05.Model.Day;
import cs3500.pa05.Model.Event;
import cs3500.pa05.Model.Task;
import java.io.IOException;
import java.util.List;

/**
 * Deserialize Object from JsonNode to new Day Obj
 */
public class DayDeserializer extends JsonDeserializer<Day> {
  @Override
  public Day deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    TreeNode node = p.readValueAsTree();
    ArrayNode tasks = (ArrayNode) node.get("tasks");
    ArrayNode events = (ArrayNode) node.get("events");

    ObjectMapper objectMapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(Task.class, new TaskDeserializer());
    module.addDeserializer(Event.class, new EventDeserializer());
    objectMapper.registerModule(module);
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    List<Task> taskList = objectMapper.readValue(tasks.toString(), new TypeReference<>() {});
    List<Event> eventList = objectMapper.readValue(events.toString(), new TypeReference<>() {});

    return Day.day(taskList, eventList);
  }
}
