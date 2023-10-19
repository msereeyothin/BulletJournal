package cs3500.pa05.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the methods in the BujoFileWriter and BujoJavaReader
 */
public class BujoFileTest {
  private Week week;
  private Task task;
  private Event event;
  private Path filePath;

  /**
   * sets up the conditions needed for the rest of the tests
   */
  @BeforeEach
  public void setup() {
    week = new Week();
    task = new Task("Task 1", "Description 1", false);
    event =
        new Event("Event 1", "10am", "30 min", "Description 1");
    week.changeName("Sample Week");
    week.setMaxEvents(10);
    week.setMaxTasks(5);
    week.addTask(1, task);
    week.addEvent(4, event);

    filePath = Path.of("src/test/resources/test.bujo");
  }

  /**
   * Tests the methods in the BujoFileWriter Class
   *
   * @throws IOException if file couldn't be found
   */
  @Test
  public void testSaveWeek() throws IOException {

    BujoFileWriter.saveWeek(week, String.valueOf(filePath));

    String jsonContent = Files.readString(filePath);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode jsonNode = mapper.readTree(jsonContent);

    assertEquals("Sample Week", jsonNode.get("name").asText());
    assertEquals(10, jsonNode.get("maxEvents").asInt());
    assertEquals(5, jsonNode.get("maxTasks").asInt());
    assertEquals(3, jsonNode.get("themeList").size());
    assertEquals(task, week.getAllTasks().get(0));
  }

  /**
   * Tests the methods in the BujoFileReader
   */
  @Test
  public void testGetFile() {

    Path filePath = Path.of("src/test/resources/test.bujo");

    // Read the file using BujoFileReader
    Week week = BujoFileReader.getFile(filePath.toFile());

    assertNotNull(week);

    assertEquals("Sample Week", week.getName());
    assertEquals(7, week.getDays().size());

    Day day1 = week.getDays().get(0);
    List<Task> tasks1 = day1.getTasks();
    List<Event> events1 = day1.getEvents();

    assertEquals(0, tasks1.size());
    assertEquals(0, events1.size());

    Day day2 = week.getDays().get(1);
    List<Task> tasks2 = day2.getTasks();

    assertEquals(1, tasks2.size());

    Day day3 = week.getDays().get(4);
    List<Event> event3 = day3.getEvents();

    assertEquals(1, event3.size());

    Task task = tasks2.get(0);
    assertEquals("Task 1", task.getName());
    assertEquals("Description 1", task.getDescription());
    assertFalse(task.getComplete());

    Event event = event3.get(0);
    assertEquals("Event 1", event.getName());
    assertEquals("10am", event.getStartTime());
    assertEquals("30 min", event.getDuration());
    assertEquals("Description 1", event.getDescription());

    Theme theme = week.getTheme();
    assertEquals("Light Theme", theme.getName());
    assertEquals(new Font("Calibri", 17), theme.getFont());
    assertEquals(Color.rgb(230, 230, 230), theme.getPrimaryColor());
    assertEquals(Color.rgb(227, 217, 200), theme.getSecondaryColor());
    assertEquals(Color.rgb(23, 22, 22), theme.getFontColor());

    assertEquals(10, week.getMaxEvents());
    assertEquals(5, week.getMaxTasks());

    List<Theme> themeList = week.getThemeList();
    assertEquals(3, themeList.size());

  }


}
