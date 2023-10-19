package cs3500.pa05.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cs3500.pa05.Model.Json.ColorDeserializer;
import cs3500.pa05.Model.Json.DayDeserializer;
import cs3500.pa05.Model.Json.DayJson;
import cs3500.pa05.Model.Json.EventJson;
import cs3500.pa05.Model.Json.FontDeserializer;
import cs3500.pa05.Model.Json.FontJson;
import cs3500.pa05.Model.Json.TaskJson;
import cs3500.pa05.Model.Json.ThemeDeserializer;
import cs3500.pa05.Model.Json.ThemeJson;
import cs3500.pa05.Model.Json.WeekJson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * Read bujo file
 */
public class BujoFileReader {

  /**
   * Get data to create a Week object from file
   *
   * @param file bujo file got from constructor in the form of that week
   * @return Week after being processed
   */
  public static Week getFile(File file) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String fileContent = Files.readString(Path.of(file.getPath()));
      ObjectMapper mapper = new ObjectMapper();
      JsonNode jsonNode = mapper.readTree(fileContent);
      return processJsonNode(jsonNode);
    } catch (IOException e) {
      System.out.println("An error occurred while reading the JSON file: " + e.getMessage());
      return null;
    }
  }

  /**
   * Turn record into actual objects in the Week class
   *
   * @param jsonNode node to be read from
   * @return Week object after mapped
   */
  private static Week processJsonNode(JsonNode jsonNode) {
    SimpleModule module = new SimpleModule();
    module.addDeserializer(Day.class, new DayDeserializer());
    module.addDeserializer(Theme.class, new ThemeDeserializer());
    module.addDeserializer(Color.class, new ColorDeserializer());
    module.addDeserializer(Font.class, new FontDeserializer());

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(module);
    mapper.enable(SerializationFeature.INDENT_OUTPUT);


    WeekJson weekJson = mapper.convertValue(jsonNode, WeekJson.class);
    String name = weekJson.name();
    List<DayJson> dayJsons = weekJson.days();
    ThemeJson themeJson = weekJson.theme();
    int maxEvents = weekJson.maxEvents();
    int maxTasks = weekJson.maxTasks();
    List<ThemeJson> themeJsons = weekJson.themeList();

    List<Day> days = new ArrayList<>();
    for (DayJson day : dayJsons) {
      List<Task> tasks = new ArrayList<>();
      List<Event> events = new ArrayList<>();

      for (TaskJson taskJson : day.tasks()) {
        Task task = new Task(taskJson.name(), taskJson.description(), taskJson.complete());
        tasks.add(task);
      }
      for (EventJson eventJson : day.events()) {
        Event event = new Event(eventJson.name(), eventJson.startTime(),
            eventJson.duration(), eventJson.description());
        events.add(event);
      }
      Day currDay = Day.day(tasks, events);
      days.add(currDay);
    }

    FontJson fontJson = themeJson.font();
    Font font = new Font(fontJson.family(), fontJson.size());
    Theme theme = new Theme(themeJson.name(), font,
        themeJson.primaryColor(), themeJson.secondaryColor(), themeJson.fontColor());

    List<Theme> themelist = new ArrayList<>();
    for (ThemeJson currThemeJson : themeJsons) {
      fontJson = currThemeJson.font();
      font = new Font(fontJson.family(), fontJson.size());
      Theme currTheme = new Theme(currThemeJson.name(), font,
          currThemeJson.primaryColor(), currThemeJson.secondaryColor(), currThemeJson.fontColor());
      themelist.add(currTheme);
    }

    System.out.println("File Read");
    return new Week(name, days, theme, maxEvents, maxTasks, themelist);
  }


}
