package cs3500.pa05.Model.JavaFxDialog;

import cs3500.pa05.Model.Weekdays;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * A day picker JavaFX component.
 */
public class DayPicker implements JavaFxDialog {

  private String type = "DayPicker";
  HBox chooseDayHbox = new HBox();
  ComboBox<String> dayComboBox = new ComboBox<>(
      FXCollections.observableArrayList(Weekdays.Monday.toString(), Weekdays.Tuesday.toString(),
          Weekdays.Wednesday.toString(), Weekdays.Thursday.toString(), Weekdays.Friday.toString(),
          Weekdays.Saturday.toString(), Weekdays.Sunday.toString()));
  Label daylabel = new Label("  Day Chosen: -");

  /**
   * Create a new day picker dialog.
   */
  public DayPicker() {
    this.chooseDayHbox.setPrefHeight(40);
    this.dayComboBox.setValue("Monday");
    dayComboBox.setOnAction(e -> {
      daylabel.setText("Day Chosen: " + dayComboBox.getValue());
    });
    chooseDayHbox.getChildren().addAll(dayComboBox, daylabel);
    chooseDayHbox.setAlignment(Pos.CENTER);
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
   * Get the HBox of this component.
   *
   * @return The HBox of this component
   */
  public HBox getHbox() {
    return this.chooseDayHbox;
  }

  /**
   * Get the value of this dialog.
   *
   * @return The chosen day
   */
  public String getValue() {
    return this.dayComboBox.getValue();
  }
}
