package cs3500.pa05.Model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.Model.Json.DayJson;
import cs3500.pa05.Model.Json.EventJson;
import cs3500.pa05.Model.Json.FontJson;
import cs3500.pa05.Model.Json.JsonUtils;
import cs3500.pa05.Model.Json.TaskJson;
import cs3500.pa05.Model.Json.ThemeJson;
import cs3500.pa05.Model.Json.WeekJson;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * File Writer for Bujo file to write a week
 */
public class BujoFileWriter {


  /**
   * Write into new file with given path and JsonNode
   *
   * @param week Week to be saved
   * @param filePath File Path
   */
  public static void saveWeek(Week week, String filePath) {

    // create dayJson records
    List<DayJson> dayJsons = new ArrayList<>();
    for (Day day : week.getDays()) {
      List<TaskJson> taskJsons = new ArrayList<>();
      List<EventJson> eventJsons = new ArrayList<>();

      for (Task task : day.getTasks()) {
        TaskJson taskJson = new TaskJson(task.getName(), task.getDescription(), task.getComplete());
        taskJsons.add(taskJson);
      }
      for (Event event : day.getEvents()) {
        EventJson eventJson = new EventJson(event.getName(), event.getStartTime(),
            event.getDuration(), event.getDescription());
        eventJsons.add(eventJson);
      }
      DayJson dayJson = new DayJson(taskJsons, eventJsons);
      dayJsons.add(dayJson);
    }

    // Make ThemeJson records
    Theme mainTheme = week.getTheme();
    FontJson fontJson = new FontJson(mainTheme.getFont().getName(), mainTheme.getFont().getSize());
    ThemeJson themeJson = new ThemeJson(mainTheme.getName(), fontJson,
        mainTheme.getPrimaryColor(), mainTheme.getSecondaryColor(), mainTheme.getFontColor());

    List<ThemeJson> themeJsons = new ArrayList<>();
    for (Theme theme : week.getThemeList()) {
      fontJson = new FontJson(theme.getFont().getName(), theme.getFont().getSize());
      ThemeJson currTheme = new ThemeJson(theme.getName(), fontJson,
          theme.getPrimaryColor(), theme.getSecondaryColor(), theme.getFontColor());
      themeJsons.add(currTheme);
    }

    WeekJson weekJson = new WeekJson(
        week.getName(),
        dayJsons,
        themeJson,
        week.getMaxEvents(),
        week.getMaxTasks(),
        themeJsons
    );


    JsonNode jsonFile = JsonUtils.serializeRecord(weekJson);

    ObjectMapper objectMapper = new ObjectMapper();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      String json = objectMapper.writeValueAsString(jsonFile);
      writer.write(json);
      writer.newLine();
      System.out.println("File Written");
    } catch (IOException e) {
      System.out.println("An error occurred while writing file: " + e.getMessage());
    }
  }


}
