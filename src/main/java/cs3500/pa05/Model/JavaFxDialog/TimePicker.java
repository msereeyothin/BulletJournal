package cs3500.pa05.Model.JavaFxDialog;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents a JavaFX time picker dialog.
 */
public class TimePicker implements JavaFxDialog {
  HBox timePickerHbox = new HBox(10);
  ComboBox<String> hourComboBox = new ComboBox<>(
      FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
          "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));
  ComboBox<String> minuteComboBox =
      new ComboBox<>(FXCollections.observableArrayList("00", "15", "30", "45"));

  private String type = "TimePicker";
  String customLabel = "";
  Label label = new Label();

  /**
   * Create a time picker dialog.
   *
   * @param customLabel The label of the time picker
   */
  public TimePicker(String customLabel) {
    this.customLabel = customLabel;
    this.hourComboBox.setValue("00");
    this.hourComboBox.setOnAction(e -> {
      this.updateLabel();
    });
    this.minuteComboBox.setValue("00");
    this.minuteComboBox.setOnAction(e -> {
      this.updateLabel();
    });
    this.timePickerHbox.getChildren().addAll(hourComboBox, minuteComboBox, label);
    this.timePickerHbox.setPrefWidth(350);
    this.timePickerHbox.setPrefHeight(40);
    this.timePickerHbox.setAlignment(Pos.CENTER);
    this.updateLabel();
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
   * Change the type identifier of this dialog.
   *
   * @param type The new identifier
   */
  @Override
  public void changeType(String type) {
    this.type = type;
  }

  /**
   * Get the HBox of this dialog.
   *
   * @return The HBox of this dialog
   */
  @Override
  public HBox getHbox() {
    return timePickerHbox;
  }

  /**
   * Get the value of the dialog.
   *
   * @return The dialog's entered value
   */
  @Override
  public String getValue() {
    String hour = hourComboBox.getValue();
    String minute = minuteComboBox.getValue();
    return hour + ":" + minute;
  }

  /**
   * Update the label of this dialog.
   */
  private void updateLabel() {
    this.label.setText(this.customLabel + this.getValue());
  }

}
