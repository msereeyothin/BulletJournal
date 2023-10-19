package cs3500.pa05.Model.JavaFxDialog;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * A description field JavaFX component.
 */
public class DescriptionField implements JavaFxDialog {
  private String type = "DescriptionField";
  HBox descriptionHBox = new HBox();
  TextField descriptionTextField = new TextField("Description");

  /**
   * Create a new description text field dialog.
   */
  public DescriptionField() {
    this.descriptionHBox.setPrefHeight(40);
    this.descriptionTextField.setPrefWidth(200);
    this.descriptionHBox.getChildren().add(descriptionTextField);
    this.descriptionHBox.setAlignment(Pos.CENTER);
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
   * Reassign the type identifier of this dialog.
   *
   * @param type The new type
   */
  @Override
  public void changeType(String type) {
    this.type = type;
  }

  /**
   * Get the entered description.
   *
   * @return The description
   */
  @Override
  public String getValue() {
    return this.descriptionTextField.getText();
  }

  /**
   * Get the description entry component HBox.
   *
   * @return The HBox of this component
   */
  @Override
  public HBox getHbox() {
    return this.descriptionHBox;
  }
}
