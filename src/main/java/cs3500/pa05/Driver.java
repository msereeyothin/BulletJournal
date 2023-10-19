package cs3500.pa05;

import cs3500.pa05.Controller.BujoController;
import cs3500.pa05.Model.Week;
import cs3500.pa05.View.BujoView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The driver class for this application.
 */
public class Driver extends Application {

  /**
   * The entry point of this application.
   *
   * @param args the Command Line arguments
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * Start the application
   *
   * @param stage the primary stage for this application, onto which
   *              the application scene can be set.
   *              Applications may create other stages, if needed, but they will not be
   *              primary stages.
   */
  @Override
  public void start(Stage stage)  {
    BujoController controller = new BujoController(new Week(), stage);
    BujoView viewer = new BujoView(controller);
    try {
      // load and place the view's scene onto the stage
      stage.setScene(viewer.load());
      // render the stage
      stage.show();
      controller.run();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }
}
