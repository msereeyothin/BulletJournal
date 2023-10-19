package cs3500.pa05.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the methods in the Day Class
 */
public class DayTest {
  private Day day;
  private Task task1;
  private Task task2;
  private Event event1;
  private Event event2;

  /**
   * Sets up the conditions needed for the rest of the tests
   */
  @BeforeEach
  public void setUp() {
    day = new Day();
    task1 = new Task("Task 1", "Description 1", false);
    task2 = new Task("Task 2", "Description 2", false);
    event1 =
        new Event("Event 1", "1300", "5 min", "Description 1");
    event2 = new Event("Event 2", "800", "1 hr", "Description 2");
  }

  /**
   * Tests the addTask() method in the Day Class
   */
  @Test
  public void testAddTask() {
    day.addTask(task1);

    List<Task> tasks = day.getTasks();
    assertEquals(1, tasks.size());
    assertEquals(task1, tasks.get(0));
  }

  /**
   * Tests the addEvent() method in the Day Class
   */
  @Test
  public void testAddEvent() {
    day.addEvent(event1);

    List<Event> events = day.getEvents();
    assertEquals(1, events.size());
    assertEquals(event1, events.get(0));
  }

  /**
   * Tests the removeTask() method in the Day Class
   */
  @Test
  public void testRemoveTask() {
    day.addTask(task1);
    day.addTask(task2);

    day.removeTask(task1);

    List<Task> tasks = day.getTasks();
    assertEquals(1, tasks.size());
    assertEquals(task2, tasks.get(0));
  }

  /**
   * Tests the removeEvent() method in the Day Class
   */
  @Test
  public void testRemoveEvent() {
    day.addEvent(event1);
    day.addEvent(event2);

    day.removeEvent(event1);

    List<Event> events = day.getEvents();
    assertEquals(1, events.size());
    assertEquals(event2, events.get(0));
  }

  /**
   * Tests the clearTasks() method in the Day Class
   */
  @Test
  public void testClearTasks() {
    day.addTask(task1);
    day.addTask(task2);

    day.clearTasks();

    List<Task> tasks = day.getTasks();
    assertEquals(0, tasks.size());
  }

  /**
   * Tests the clearEvents() method in the Day Class
   */
  @Test
  public void testClearEvents() {
    day.addEvent(event1);
    day.addEvent(event2);

    day.clearEvents();

    List<Event> events = day.getEvents();
    assertEquals(0, events.size());
  }

  /**
   * Tests the getTasksCopy() method in the Day Class
   */
  @Test
  public void testGetTasksCopy() {
    day.addTask(task1);
    day.addTask(task2);

    List<Task> copiedTasks = day.getTasksCopy();

    assertNotSame(day.getTasks(), copiedTasks);
    assertNotSame(day.getTasks(), copiedTasks);

    assertNotEquals(day.getTasks(), copiedTasks);
  }

  /**
   * Tests the getEventCopy() method in the Day Class
   */
  @Test
  public void testGetEventsCopy() {
    day.addEvent(event1);
    day.addEvent(event2);

    List<Event> copiedEvents = day.getEventsCopy();

    assertNotSame(day.getEvents(), copiedEvents);
    assertNotSame(day.getEvents(), copiedEvents);

    assertNotEquals(day.getEvents(), copiedEvents);
  }
}
