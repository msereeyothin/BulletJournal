package cs3500.pa05.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.Model.Json.DayJson;
import cs3500.pa05.Model.Json.FontJson;
import cs3500.pa05.Model.Json.ThemeJson;
import cs3500.pa05.Model.Json.WeekJson;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Font;
import org.junit.jupiter.api.Test;

/**
 * Tests the WeekJson Record
 */
public class WeekJsonTest {

  /**
   * Tests all the properties of the WeekJson Record
   */
  @Test
  public void testWeekJsonRecord() {
    String name = "Week 1";
    List<DayJson> days = new ArrayList<>();
    Font font = Theme.lightTheme().getFont();
    FontJson fontJson = new FontJson(font.getName(), font.getSize());
    ThemeJson theme = new ThemeJson(Theme.lightTheme().getName(), fontJson,
        Theme.lightTheme().getPrimaryColor(), Theme.lightTheme().getSecondaryColor(),
        Theme.lightTheme()
            .getFontColor());
    int maxEvents = 5;
    int maxTasks = 10;
    List<ThemeJson> themeList = new ArrayList<>();

    // Create an instance of WeekJson
    WeekJson weekJson = new WeekJson(name, days, theme, maxEvents, maxTasks, themeList);

    // Verify the values using the record's getter methods
    assertEquals(name, weekJson.name());
    assertEquals(days, weekJson.days());
    assertEquals(theme, weekJson.theme());
    assertEquals(maxEvents, weekJson.maxEvents());
    assertEquals(maxTasks, weekJson.maxTasks());
    assertEquals(themeList, weekJson.themeList());
  }
}
