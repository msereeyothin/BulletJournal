package cs3500.pa05.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the methods in the Weekdays Class
 */
public class WeekdaysTest {

  /**
   * Tests the getCorrespondingIndex() method in the Weekdays Class
   */
  @Test
  public void testGetCorrespondingIndex() {
    assertEquals(0, Weekdays.Monday.getCorrespondingIndex());
    assertEquals(1, Weekdays.Tuesday.getCorrespondingIndex());
    assertEquals(2, Weekdays.Wednesday.getCorrespondingIndex());
    assertEquals(3, Weekdays.Thursday.getCorrespondingIndex());
    assertEquals(4, Weekdays.Friday.getCorrespondingIndex());
    assertEquals(5, Weekdays.Saturday.getCorrespondingIndex());
    assertEquals(6, Weekdays.Sunday.getCorrespondingIndex());
  }

  /**
   * Tests the getEnumFromString() method in the Weekday Class
   */
  @Test
  public void testGetEnumFromString() {
    assertEquals(Weekdays.Monday, Weekdays.getEnumFromString("Monday"));
    assertEquals(Weekdays.Tuesday, Weekdays.getEnumFromString("Tuesday"));
    assertEquals(Weekdays.Wednesday, Weekdays.getEnumFromString("Wednesday"));
    assertEquals(Weekdays.Thursday, Weekdays.getEnumFromString("Thursday"));
    assertEquals(Weekdays.Friday, Weekdays.getEnumFromString("Friday"));
    assertEquals(Weekdays.Saturday, Weekdays.getEnumFromString("Saturday"));
    assertEquals(Weekdays.Sunday, Weekdays.getEnumFromString("Sunday"));

    assertThrows(IllegalArgumentException.class, () -> Weekdays.getEnumFromString("InvalidDay"));
  }
}
