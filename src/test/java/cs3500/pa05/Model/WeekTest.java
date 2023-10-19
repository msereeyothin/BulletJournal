package cs3500.pa05.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Tests for the Week Class
 */
public class WeekTest {
  private Week week;

  /**
   * Sets up the conditions needs for the rest of the tests
   */
  @BeforeEach
  public void setup() {
    this.week = new Week();
  }

  /**
   * Tests the defaultWeek() method in the Week Class
   */
  @Test
  public void testDefaultWeek() {
    assertEquals("New Week", this.week.getName());
    assertEquals(10, this.week.getMaxTasks());
    assertEquals(10, this.week.getMaxEvents());

    List<Theme> themeList = week.getThemeList();

    assertEquals(3, themeList.size());
    assertEquals("Light Theme", themeList.get(0).getName());
    assertEquals("Dark Theme", themeList.get(1).getName());
    assertEquals("Sunset Theme", themeList.get(2).getName());

    List<Day> days = week.getDays();
    assertEquals(7, days.size());
    for (Day day : days) {
      assertNotNull(day);
    }
  }

  /**
   * Tests the customWeek() method in the Week Class
   */
  @Test
  public void testCustomWeek() {
    String name = "Custom Week";
    List<Day> days = new ArrayList<>();
    Theme theme =
        new Theme("Custom Theme", new Font("Arial", 18), Color.RED, Color.BLUE, Color.WHITE);
    int maxEvents = 5;
    int maxTasks = 8;
    List<Theme> themeList = new ArrayList<>();
    themeList.add(Theme.lightTheme());
    themeList.add(Theme.darkTheme());

    for (int i = 0; i < 7; i++) {
      days.add(new Day());
    }

    Week customWeek = new Week(name, days, theme, maxEvents, maxTasks, themeList);

    assertEquals(name, customWeek.getName());
    assertEquals(maxEvents, customWeek.getMaxEvents());
    assertEquals(maxTasks, customWeek.getMaxTasks());
    assertEquals(theme, customWeek.getTheme());
    assertEquals(themeList, customWeek.getThemeList());
    assertEquals(days, customWeek.getDays());
  }

  /**
   * Tests the changeTheme() method in the Week Class
   */
  @Test
  public void testChangeTheme() {
    Theme theme =
        new Theme("Custom Theme", new Font("Arial", 18), Color.RED, Color.BLUE, Color.WHITE);
    week.changeTheme(theme);
    assertEquals(theme, week.getTheme());
  }

  /**
   * Tests the changeName() method in the Week Class
   */
  @Test
  public void testChangeName() {
    String name = "Custom Week";
    week.changeName(name);
    assertEquals(name, week.getName());
  }

  /**
   * Tests the setMaxEvents() method in the Week Class
   */
  @Test
  public void testSetMaxEvents() {
    int maxEvents = 5;
    week.setMaxEvents(maxEvents);
    assertEquals(maxEvents, week.getMaxEvents());
  }

  /**
   * Tests the setMaxTasks() method in the Week Class
   */
  @Test
  public void testSetMaxTasks() {
    int maxTasks = 8;
    week.setMaxTasks(maxTasks);
    assertEquals(maxTasks, week.getMaxTasks());
  }

  /**
   * Tests the addEvent() method in the Week Class
   */
  @Test
  public void testAddEvent() {
    Event event = new Event("Event 1", "Sample Event", "10:00 AM", "12:00 PM");
    week.addEvent(0, event);
    assertEquals(event, week.getDay(0).getEvents().get(0));
  }

  /**
   * Tests the addTask() method in the Week Class
   */
  @Test
  public void testAddTask() {
    Task task = new Task("Task 1", "Sample Task", false);
    week.addTask(0, task);
    assertEquals(task, week.getDay(0).getTasks().get(0));
  }

  /**
   * Tests the getAllTasks() method in the Week Class
   */
  @Test
  public void testGetAllTasks() {
    Task task1 = new Task("Task 1", "Sample Task 1", false);
    Task task2 = new Task("Task 2", "Sample Task 2", false);
    week.addTask(0, task1);
    week.addTask(1, task2);

    List<Task> allTasks = week.getAllTasks();
    assertEquals(2, allTasks.size());
    assertTrue(allTasks.contains(task1));
    assertTrue(allTasks.contains(task2));
  }

  /**
   * Tests the getCompletedTasks() method in the Week Class
   */
  @Test
  public void testGetCompletedTasks() {
    Task task1 = new Task("Task 1", "Sample Task 1", false);
    Task task2 = new Task("Task 2", "Sample Task 2", false);
    Task task3 = new Task("Task 3", "Sample Task 3", false);
    task1.changeStatus();
    task3.changeStatus();
    week.addTask(0, task1);
    week.addTask(1, task2);
    week.addTask(2, task3);

    List<Task> completedTasks = week.getCompletedTasks();
    assertEquals(2, completedTasks.size());
    assertTrue(completedTasks.contains(task1));
    assertTrue(completedTasks.contains(task3));
  }

  /**
   * Tests the getAllEvents() method in the Week Class
   */
  @Test
  public void testGetAllEvents() {
    Event event1 = new Event("Event 1", "Sample Event 1", "10:00 AM", "12:00 PM");
    Event event2 = new Event("Event 2", "Sample Event 2", "2:00 PM", "4:00 PM");
    week.addEvent(0, event1);
    week.addEvent(1, event2);

    List<Event> allEvents = week.getAllEvents();
    assertEquals(2, allEvents.size());
    assertTrue(allEvents.contains(event1));
    assertTrue(allEvents.contains(event2));
  }
}
