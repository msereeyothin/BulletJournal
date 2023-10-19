package cs3500.pa05.Model.JavaFxDialog;

import javafx.scene.layout.HBox;

/**
 * Has the methods needd for JavaFx Dialog
 */
public interface JavaFxDialog {
  /**
   * Get the type of this JavaFxDialog.
   *
   * @return The type of this JavaFxDialog
   */
  public String type();

  /**
   * Reassign the type identifier of this dialog
   *
   * @param type The new type
   */
  public void changeType(String type);

  /**
   * Get the HBox of this component.
   *
   * @return The HBox of this component
   */
  public HBox getHbox();

  /**
   * Get the value of this dialog.
   *
   * @return The chosen day
   */
  public String getValue();
}
