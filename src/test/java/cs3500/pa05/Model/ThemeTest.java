package cs3500.pa05.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.junit.jupiter.api.Test;

/**
 * Tests the methods in the Theme Class
 */
public class ThemeTest {

  /**
   * Tests the lightTheme() method in the Theme Class
   */
  @Test
  public void testLightTheme() {
    Theme lightTheme = Theme.lightTheme();

    assertNotNull(lightTheme);
    assertEquals("Light Theme", lightTheme.getName());
    assertEquals(new Font("Calibri", 17), lightTheme.getFont());
    assertEquals(Color.rgb(230, 230, 230), lightTheme.getPrimaryColor());
    assertEquals(Color.rgb(227, 217, 200), lightTheme.getSecondaryColor());
    assertEquals(Color.rgb(23, 22, 22), lightTheme.getFontColor());
  }

  /**
   * Tests the darkTheme() method in the Theme Class
   */
  @Test
  public void testDarkTheme() {
    Theme darkTheme = Theme.darkTheme();

    assertNotNull(darkTheme);
    assertEquals("Dark Theme", darkTheme.getName());
    assertEquals(new Font("Tahoma", 16), darkTheme.getFont());
    assertEquals(Color.rgb(22, 23, 33), darkTheme.getPrimaryColor());
    assertEquals(Color.rgb(65, 55, 82), darkTheme.getSecondaryColor());
    assertEquals(Color.rgb(228, 228, 235), darkTheme.getFontColor());
  }

  /**
   * Tests the sunsetTheme() method in the Theme Class
   */
  @Test
  public void testSunsetTheme() {
    Theme sunsetTheme = Theme.sunsetTheme();

    assertNotNull(sunsetTheme);
    assertEquals("Sunset Theme", sunsetTheme.getName());
    assertEquals(new Font("Garamond", 17), sunsetTheme.getFont());
    assertEquals(Color.rgb(250, 208, 135), sunsetTheme.getPrimaryColor());
    assertEquals(Color.rgb(191, 77, 111), sunsetTheme.getSecondaryColor());
    assertEquals(Color.rgb(40, 25, 59), sunsetTheme.getFontColor());
  }
}
