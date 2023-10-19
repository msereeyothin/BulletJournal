package cs3500.pa05.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests the Task Class
 */
public class TaskTest {
  private Task task;

  /**
   * Sets up the conditions needed for the rest of the tests
   */
  @BeforeEach
  public void setUp() {
    task = new Task("Buy groceries", "Go to the supermarket", false);
  }

  /**
   * Tests the getComplete() method in the Task class
   */
  @Test
  public void testGetComplete() {
    assertFalse(task.getComplete());
  }

  /**
   * Tests the getName() method in the Task class
   */
  @Test
  public void testGetName() {
    assertEquals("Buy groceries", task.getName());
  }

  /**
   * Tests the getDescription() method in the Task class
   */
  @Test
  public void testGetDescription() {
    assertEquals("Go to the supermarket", task.getDescription());
  }

  /**
   * Tests the changeStatus() method in the Task class
   */
  @Test
  public void testChangeStatus() {
    task.changeStatus();
    assertTrue(task.getComplete());

    task.changeStatus();
    assertFalse(task.getComplete());
  }

  /**
   * Tests the changeDescription() method in the Task class
   */
  @Test
  public void testChangeDescription() {
    task.changeDescription("Buy groceries online");
    assertEquals("Buy groceries online", task.getDescription());
  }

  /**
   * Tests the getCopy() method in the Task class
   */
  @Test
  public void testGetCopy() {
    Task copy = task.getCopy();

    assertEquals(task.getName(), copy.getName());
    assertEquals(task.getDescription(), copy.getDescription());
    assertEquals(task.getComplete(), copy.getComplete());

    copy.editName("Modified Task");
    assertNotEquals(copy.getName(), task.getName());
  }

  /**
   * Tests the editName() method in the Task class
   */
  @Test
  public void testEditName() {
    task.editName("Buy household supplies");
    assertEquals("Buy household supplies", task.getName());
  }

  /**
   * Tests the editDescription() method in the Task class
   */
  @Test
  public void testEditDescription() {
    task.editDescription("Go to the hardware store");
    assertEquals("Go to the hardware store", task.getDescription());
  }
}
