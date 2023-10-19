package cs3500.pa05.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests the methods in the Event Class
 */
public class EventTest {
  private Event event;

  /**
   * Sets up the conditions needed for the rest of the tests
   */
  @BeforeEach
  public void setUp() {
    event = new Event("Meeting", "9:00 AM", "1 hour",
        "Discuss project");
  }

  /**
   * Tests the getName() method in the Event Class
   */
  @Test
  public void testGetName() {
    assertEquals("Meeting", event.getName());
  }

  /**
   * Tests the getStartTime() method in the Event Class
   */
  @Test
  public void testGetStartTime() {
    assertEquals("9:00 AM", event.getStartTime());
  }

  /**
   * Tests the getDuration() method in the Event Class
   */
  @Test
  public void testGetDuration() {
    assertEquals("1 hour", event.getDuration());
  }

  /**
   * Tests the getDescription() method in the Event Class
   */
  @Test
  public void testGetDescription() {
    assertEquals("Discuss project", event.getDescription());
  }

  /**
   * Tests the editDescription() method in the Event Class
   */
  @Test
  public void testEditDescription() {
    event.editDescription("Updated description");
    assertEquals("Updated description", event.getDescription());
  }

  /**
   * Tests the editStartTime() method in the Event Class
   */
  @Test
  public void testEditStartTime() {
    event.editStartTime("10:00 AM");
    assertEquals("10:00 AM", event.getStartTime());
  }

  /**
   * Tests the editDuration() method in the Event Class
   */
  @Test
  public void testEditDuration() {
    event.editDuration("2 hours");
    assertEquals("2 hours", event.getDuration());
  }

  /**
   * Tests the editName() method in the Event Class
   */
  @Test
  public void testEditName() {
    event.editName("New Meeting");
    assertEquals("New Meeting", event.getName());
  }

  /**
   * Tests the getCopy() method in the Event Class
   */
  @Test
  public void testGetCopy() {
    Event copy = event.getCopy();

    assertEquals(event.getName(), copy.getName());
    assertEquals(event.getStartTime(), copy.getStartTime());
    assertEquals(event.getDuration(), copy.getDuration());
    assertEquals(event.getDescription(), copy.getDescription());

    copy.editName("Modified Meeting");
    assertNotEquals(copy.getName(), event.getName());
  }
}
