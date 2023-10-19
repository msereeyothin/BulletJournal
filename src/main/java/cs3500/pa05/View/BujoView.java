package cs3500.pa05.View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import cs3500.pa05.Controller.Controller;

/**
 * Represents the view of a bujo session.
 */
public class BujoView implements View {
  FXMLLoader loader = new FXMLLoader();

  /**
   * Create a new BujoView.
   *
   * @param controller The controller of the GUI
   */
  public BujoView(Controller controller) {
    this.loader.setController(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource("PA5JavaJournal.fxml"));
  }

  /**
   * Loads a scene from a bujo GUI layout.
   *
   * @return the layout
   */
  @Override
  public Scene load() {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}

