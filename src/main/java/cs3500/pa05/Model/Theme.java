package cs3500.pa05.Model;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Represents a theme in a bujo session.
 */
public class Theme {

  private String name;
  private Font font;
  private Color primary;
  private Color secondary;
  private Color fontColor;

  /**
   * Create a new theme.
   *
   * @param name      The theme name
   * @param font      The theme font
   * @param primary   The primary color
   * @param secondary The secondary color
   * @param fontColor The color of the font
   */
  public Theme(String name, Font font, Color primary, Color secondary, Color fontColor) {
    this.name = name;
    this.font = font;
    this.primary = primary;
    this.secondary = secondary;
    this.fontColor = fontColor;
  }

  /**
   * A pre-made light theme.
   *
   * @return A light theme
   */
  public static Theme lightTheme() {
    Font font = new Font("Calibri", 17);
    Color primary = Color.rgb(230, 230, 230);
    Color secondary = Color.rgb(227, 217, 200);
    Color fontColor = Color.rgb(23, 22, 22);
    return new Theme("Light Theme", font, primary, secondary, fontColor);
  }

  /**
   * A pre-made dark theme.
   *
   * @return A dark theme
   */
  public static Theme darkTheme() {
    Font font = new Font("Tahoma", 16);
    Color primary = Color.rgb(22, 23, 33);
    Color secondary = Color.rgb(65, 55, 82);
    Color fontColor = Color.rgb(228, 228, 235);
    return new Theme("Dark Theme", font, primary, secondary, fontColor);
  }

  /**
   * A pre-made sunset theme.
   *
   * @return A sunset theme
   */
  public static Theme sunsetTheme() {
    Font font = new Font("Garamond", 17);
    Color primary = Color.rgb(250, 208, 135);
    Color secondary = Color.rgb(191, 77, 111);
    Color fontColor = Color.rgb(40, 25, 59);
    return new Theme("Sunset Theme", font, primary, secondary, fontColor);
  }

  /**
   * Get the name of this theme.
   *
   * @return The name of the theme
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get the theme's font.
   *
   * @return The theme's font.
   */
  public Font getFont() {
    return this.font;
  }

  /**
   * Get the theme's primary color.
   *
   * @return The theme's primary color.
   */
  public Color getPrimaryColor() {
    return this.primary;
  }

  /**
   * Get the theme's secondary color.
   *
   * @return The theme's secondary color.
   */
  public Color getSecondaryColor() {
    return this.secondary;
  }

  /**
   * Get the theme's font color.
   *
   * @return The theme's font color.
   */
  public Color getFontColor() {
    return this.fontColor;
  }

}
