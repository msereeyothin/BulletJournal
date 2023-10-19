package cs3500.pa05.View;

import javafx.scene.Scene;

/**
 * Represents a GUI view for a bujo.
 */
public interface View {
  /**
   * Loads a scene from a bujo GUI layout.
   *
   * @return the layout
   */
  Scene load() throws IllegalStateException;
}