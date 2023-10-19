package cs3500.pa05.Model.JavaFxDialog;

import cs3500.pa05.Model.JavaFxDialog.JavaFxDialog;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * A name entry JavaFX component.
 */
public class NameEntry implements JavaFxDialog {
  private String type = "NameEntry";
  HBox nameHbox = new HBox();
  TextField nameEntry = new TextField("Name");

  /**
   * Create a name entry dialog.
   */
  public NameEntry() {
    this.nameHbox.setPrefHeight(40);
    this.nameEntry.setPrefWidth(150);
    this.nameHbox.getChildren().add(this.nameEntry);
    this.nameHbox.setAlignment(Pos.CENTER);
  }

  /**
   * Get the type of this JavaFxDialog.
   *
   * @return The type of this JavaFxDialog
   */
  @Override
  public String type() {
    return this.type;
  }

  /**
   * Change the type id of the dialog.
   */
  @Override
  public void changeType(String type) {
    this.type = type;
  }

  /**
   * Get the HBox of this component.
   *
   * @return The HBox of the name entry component.
   */
  @Override
  public HBox getHbox() {
    return this.nameHbox;
  }

  /**
   * Get the name from the text field.
   *
   * @return The name from the text field
   */
  @Override
  public String getValue() {
    return this.nameEntry.getText();
  }
}
