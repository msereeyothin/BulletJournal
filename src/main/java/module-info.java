/**
 * Module
 */

module pa05 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens cs3500.pa05 to javafx.fxml;
    exports cs3500.pa05;
    exports cs3500.pa05.Controller;
    exports cs3500.pa05.Model;
    exports cs3500.pa05.View;
    opens cs3500.pa05.Controller to javafx.fxml;
  exports cs3500.pa05.Model.JavaFxDialog;
  exports cs3500.pa05.Model.Json;
}