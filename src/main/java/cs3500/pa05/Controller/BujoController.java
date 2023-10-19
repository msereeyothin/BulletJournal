package cs3500.pa05.Controller;

import static cs3500.pa05.Model.Weekdays.getEnumFromString;

import cs3500.pa05.Model.BujoFileReader;
import cs3500.pa05.Model.BujoFileWriter;
import cs3500.pa05.Model.Day;
import cs3500.pa05.Model.Event;
import cs3500.pa05.Model.JavaFxDialog.DayPicker;
import cs3500.pa05.Model.JavaFxDialog.DescriptionField;
import cs3500.pa05.Model.JavaFxDialog.JavaFxDialog;
import cs3500.pa05.Model.JavaFxDialog.NameEntry;
import cs3500.pa05.Model.JavaFxDialog.TimePicker;
import cs3500.pa05.Model.Task;
import cs3500.pa05.Model.Theme;
import cs3500.pa05.Model.Week;
import cs3500.pa05.Model.Weekdays;
import java.io.File;
import java.util.List;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Controller class for a bujo session.
 */
public class BujoController implements Controller {
  private Stage stage;
  private Week week;
  @FXML
  private AnchorPane anchorPane;
  @FXML
  private ListView mondayEventList;
  @FXML
  private ListView mondayTaskList;
  @FXML
  private ListView tuesdayEventList;
  @FXML
  private ListView tuesdayTaskList;
  @FXML
  private ListView wednesdayEventList;
  @FXML
  private ListView wednesdayTaskList;
  @FXML
  private ListView thursdayEventList;
  @FXML
  private ListView thursdayTaskList;
  @FXML
  private ListView fridayEventList;
  @FXML
  private ListView fridayTaskList;
  @FXML
  private ListView saturdayEventList;
  @FXML
  private ListView saturdayTaskList;
  @FXML
  private ListView sundayEventList;
  @FXML
  private ListView sundayTaskList;
  @FXML
  private ListView allTasksList;
  @FXML
  private HBox weekHbox;
  @FXML
  private HBox headerHbox;
  @FXML
  private HBox weekViewHbox;
  @FXML
  private Button addEventButton;
  @FXML
  private Button addTaskButton;
  @FXML
  private Button chooseBujoButton;
  @FXML
  private Button saveBujoButton;
  @FXML
  private Button setMaxEventsButton;
  @FXML
  private Button setMaxTasksButton;
  @FXML
  private Hyperlink weekNameText;
  @FXML
  private Text maxTasks;
  @FXML
  private Text maxEvents;
  @FXML
  private Text headerText;
  @FXML
  private Text totalTasksText;
  @FXML
  private Text totalEventsText;
  @FXML
  private Text taskCompletionText;
  @FXML
  private Text allTasksText;
  @FXML
  private MenuButton themeMenuButton;
  @FXML
  private BorderPane rootPane;
  @FXML
  private VBox taskSideBar;

  /**
   * Create a new BujoController.
   *
   * @param week  The week to be processed
   * @param stage The stage of this gui
   */
  public BujoController(Week week, Stage stage) {
    this.week = week;
    this.stage = stage;
  }

  /**
   * Run the application controller.
   */
  @Override
  public void run() {
    this.initGui();
  }

  /**
   * Initialize the buttons in this bujo Session.
   */
  private void initGui() {
    this.maxTasks.setText("Max Tasks: " + this.week.getMaxTasks());
    this.maxEvents.setText("Max Events: " + this.week.getMaxEvents());
    this.setMaxEventsButton.setOnMouseClicked(e -> {
      this.setMaxEvents();
    });
    this.setMaxTasksButton.setOnMouseClicked(e -> {
      this.setMaxTasks();
    });
    this.addEventButton.setOnMouseClicked(e -> {
      this.showAddEventDialogue();
    });
    this.addTaskButton.setOnMouseClicked(e -> {
      this.showAddTaskDialogue();
    });
    this.weekNameText.setText(this.week.getName());
    this.weekNameText.setOnMouseClicked(e -> {
      this.changeWeekName();
    });
    this.chooseBujoButton.setOnAction(e -> {
      this.chooseFile();
    });
    this.saveBujoButton.setOnAction(e -> {
      this.saveFile();
    });
    this.themeMenuButton.getItems().clear();
    this.refreshLists();
    this.updateWeeklyOverview();
    this.initThemeOptions();
    this.applyTheme();
  }

  /**
   * Update the weekly overview statistics.
   */
  private void updateWeeklyOverview() {
    int totalTasks = this.week.getAllTasks().size();
    int totalEvents = this.week.getAllEvents().size();
    int completedTasks = this.week.getCompletedTasks().size();
    double completionPercentage = ((double) completedTasks / totalTasks) * 100;
    String formattedPercentage = String.format("%.1f", completionPercentage);

    this.totalTasksText.setText("Total Tasks: " + totalTasks);
    this.totalEventsText.setText("Total Events: " + totalEvents);
    this.taskCompletionText.setText("Task Completion " + formattedPercentage + "%");

  }

  /**
   * Shows a dialog to change the name of this week.
   */
  private void changeWeekName() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("New Week Name");
    dialog.setHeaderText("Enter a new week name:");
    dialog.setContentText("Name:");

    Optional<String> result = dialog.showAndWait();
    result.ifPresent(name -> {
      System.out.println("Entered name: " + name);
      this.week.changeName(name);
      this.weekNameText.setText(name);
    });
  }

  /**
   * Set the bujo theme.
   */
  private void setTheme(Theme theme) {
    this.week.changeTheme(theme);
  }

  /**
   * Applying the application theme.
   */
  private void applyTheme() {
    Theme theme = this.week.getTheme();
    Font font = theme.getFont();
    Font headerfont = new Font(font.getName(), 30);
    Color fontColor = theme.getFontColor();
    Color primaryColor = theme.getPrimaryColor();
    Color secondaryColor = theme.getSecondaryColor();

    BackgroundFill primaryBackgroundFill =
        new BackgroundFill(primaryColor, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
    BackgroundFill secondaryBackgroundFill =
        new BackgroundFill(secondaryColor, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);

    Background primaryBackground = new Background(primaryBackgroundFill);
    Background secondaryBackground = new Background(secondaryBackgroundFill);

    this.headerHbox.setBackground(secondaryBackground);
    this.weekViewHbox.setBackground(secondaryBackground);
    this.weekHbox.setBackground(primaryBackground);

    this.taskSideBar.setBackground(primaryBackground);
    this.allTasksText.setFont(font);
    this.allTasksText.setFill(fontColor);


    weekHbox.setStyle("-fx-font-family: " + font.getFamily() + ";");
    headerHbox.setStyle("-fx-font-family: " + font.getFamily() + ";");
    weekViewHbox.setStyle("-fx-font-family: " + font.getFamily() + ";");
    this.headerText.setFill(fontColor);
    this.headerText.setFont(headerfont);

    this.weekNameText.setTextFill(fontColor);
    this.weekNameText.setFont(headerfont);
    for (javafx.scene.Node node : weekHbox.getChildren()) {
      if (node instanceof VBox) {
        VBox innerVBox = (VBox) node;
        for (javafx.scene.Node innerNode : innerVBox.getChildren()) {
          if (innerNode instanceof Text) {
            Text text = (Text) innerNode;
            text.setFont(font);
            text.setFill(fontColor);
          }
        }
      }
    }
    for (javafx.scene.Node node : weekViewHbox.getChildren()) {
      if (node instanceof VBox) {
        VBox innerVBox = (VBox) node;
        for (javafx.scene.Node innerNode : innerVBox.getChildren()) {
          if (innerNode instanceof Text) {
            Text text = (Text) innerNode;
            text.setFont(font);
            text.setFill(fontColor);
          }
        }
      }
    }

  }

  /**
   * Show a pop-up for the user to create a theme.
   */
  private void createTheme() {
    Dialog<Event> dialog = new Dialog<>();
    dialog.setTitle("Create New Theme");
    Window window = dialog.getDialogPane().getScene().getWindow();

    GridPane grid = new GridPane();

    Label nameLabel = new Label("Name: ");
    NameEntry themeNameEntry = new NameEntry();
    grid.add(nameLabel, 1, 1);
    grid.add(themeNameEntry.getHbox(), 2, 1);

    Label fontLabel = new Label("Font:");
    ComboBox<String> fontComboBox = new ComboBox<>();
    Font.getFamilies().forEach(fontFamily -> fontComboBox.getItems().add(fontFamily));
    fontComboBox.getSelectionModel().selectFirst();
    grid.add(fontLabel, 1, 2);
    grid.add(fontComboBox, 2, 2);

    Label fontColorPickerLabel = new Label("Font Color: ");
    ColorPicker fontColorPicker = new ColorPicker();
    grid.add(fontColorPickerLabel, 1, 3);
    grid.add(fontColorPicker, 2, 3);

    Label primaryColorPickerLabel = new Label("Primary Color: ");
    ColorPicker primaryColorPicker = new ColorPicker();
    grid.add(primaryColorPickerLabel, 1, 4);
    grid.add(primaryColorPicker, 2, 4);

    Label secondaryColorPickerLabel = new Label("Secondary Color: ");
    ColorPicker secondaryColorPicker = new ColorPicker();
    grid.add(secondaryColorPickerLabel, 1, 5);
    grid.add(secondaryColorPicker, 2, 5);

    Button createButton = new Button("Create");
    Button cancelButton = new Button("Cancel");
    createButton.setOnMouseClicked(e -> {
      Font newFont = new Font(fontComboBox.getValue(), 17);
      Theme newTheme = new Theme(themeNameEntry.getValue(), newFont, primaryColorPicker.getValue(),
          secondaryColorPicker.getValue(), fontColorPicker.getValue());
      this.week.addToThemes(newTheme);
      this.themeMenuButton.getItems().clear();
      this.initThemeOptions();
      window.hide();
    });
    grid.add(createButton, 1, 6);
    grid.add(cancelButton, 2, 6);

    window.setOnCloseRequest(event -> window.hide());
    cancelButton.setOnAction(event -> window.hide());
    dialog.getDialogPane().setContent(grid);
    dialog.show();
  }

  /**
   * Initialize the theme menu options.
   */
  private void initThemeOptions() {
    for (Theme theme : this.week.getThemeList()) {
      MenuItem themeMenuItem = new MenuItem(theme.getName());
      themeMenuItem.setOnAction(e -> {
        System.out.println("Applying: " + theme.getName());
        this.setTheme(theme);
        this.applyTheme();
      });
      this.themeMenuButton.getItems().add(themeMenuItem);
    }
    MenuItem addTheme = new MenuItem("Add Theme +");
    addTheme.setOnAction(e -> {
      this.createTheme();
    });
    this.themeMenuButton.getItems().add(addTheme);
  }

  /**
   * Handler method for setting the max amount of events.
   */
  private void setMaxEvents() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("New Maximum Events");
    dialog.setHeaderText(null);
    dialog.setContentText("New Max Events:");
    Optional<String> result = dialog.showAndWait();
    result.ifPresent(input -> {
      try {
        int number = Integer.parseInt(input);
        System.out.println("Inputted number: " + number);
        this.week.setMaxEvents(number);
        this.maxEvents.setText("Max Events: " + number);
      } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid number input");
        alert.showAndWait();
      }
    });
    dialog.setResultConverter(dialogButton -> {
      if (dialogButton == ButtonType.OK) {
        return dialog.getEditor().getText();
      }
      return null;
    });
  }

  /**
   * Handler method for setting the max amount of tasks.
   */
  private void setMaxTasks() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("New Maximum Tasks");
    dialog.setHeaderText(null);
    dialog.setContentText("New Max Tasks:");
    Optional<String> result = dialog.showAndWait();
    result.ifPresent(input -> {
      try {
        int number = Integer.parseInt(input);
        System.out.println("Inputted number: " + number);
        this.week.setMaxTasks(number);
        this.maxTasks.setText("Max Tasks: " + number);
      } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid number input");
        alert.showAndWait();
      }
    });
    dialog.setResultConverter(dialogButton -> {
      if (dialogButton == ButtonType.OK) {
        return dialog.getEditor().getText();
      }
      return null;
    });
  }

  /**
   * Show a dialogue for adding an event.
   */
  private void showAddEventDialogue() {
    // Creating the name entry
    NameEntry nameEntry = new NameEntry();
    // Creating the day selection
    DayPicker chooseDay = new DayPicker();
    // Creating Time Pickers for start time and duration
    TimePicker startTimePicker = new TimePicker("Selected Start Time: ");
    TimePicker durationPicker = new TimePicker("Duration : ");
    // Creating description field
    DescriptionField descriptionField = new DescriptionField();
    // Creating 'create' button
    Button createEventButton = new Button("Create");
    createEventButton.setOnMouseClicked(e -> {
      Weekdays weekday = getEnumFromString(chooseDay.getValue());
      if (this.atCapacityEvents(this.week.getDay(weekday.getCorrespondingIndex()))) {
        Alert alert =
            new Alert(Alert.AlertType.ERROR, "Too many events! Remove some before you add more");
        alert.showAndWait();
      } else {
        Event newEvent =
            new Event(nameEntry.getValue(), startTimePicker.getValue(), durationPicker.getValue(),
                descriptionField.getValue());
        this.addEvent(weekday.getCorrespondingIndex(), newEvent);
      }
    });
    Button cancelButton = new Button("Cancel");
    HBox finalButtons = new HBox();
    finalButtons.getChildren().addAll(createEventButton, cancelButton);
    finalButtons.setAlignment(Pos.CENTER);
    finalButtons.setPrefHeight(40);

    // Creating and showing the dialog
    VBox dialogVbox = new VBox();
    dialogVbox.getChildren()
        .addAll(nameEntry.getHbox(), chooseDay.getHbox(), startTimePicker.getHbox(),
            durationPicker.getHbox(), descriptionField.getHbox(), finalButtons);
    Dialog<Void> dialog = new Dialog<>();

    dialog.setTitle("Create Event");
    dialog.getDialogPane().setPrefWidth(300);
    dialog.getDialogPane().setContent(dialogVbox);
    Window window = dialog.getDialogPane().getScene().getWindow();
    window.setOnCloseRequest(event -> window.hide());
    cancelButton.setOnAction(event -> window.hide());
    dialog.show();
  }

  /**
   * Handler method for adding an event.
   */
  private void addEvent(int dayIndex, Event event) {
    this.week.addEvent(dayIndex, event);
    Hyperlink newEvent = new Hyperlink(event.getName());
    newEvent.setOnMouseClicked(e -> {
      this.handleClickedOnEvent(event);
    });
    switch (dayIndex) {
      case 0:
        this.mondayEventList.getItems().add(newEvent);
        break;
      case 1:
        this.tuesdayEventList.getItems().add(newEvent);
        break;
      case 2:
        this.wednesdayEventList.getItems().add(newEvent);
        break;
      case 3:
        this.thursdayEventList.getItems().add(newEvent);
        break;
      case 4:
        this.fridayEventList.getItems().add(newEvent);
        break;
      case 5:
        this.saturdayEventList.getItems().add(newEvent);
        break;
      case 6:
        this.sundayEventList.getItems().add(newEvent);
        break;
    }
    updateWeeklyOverview();
  }

  /**
   * Handles when a user clicks on an event.
   */
  private void handleClickedOnEvent(Event event) {
    // Create a dialog to show event details
    Dialog<Event> dialog = new Dialog<>();
    dialog.setTitle("Event Details");
    dialog.setHeaderText(event.getName());

    // Create labels and text fields for each event detail
    Label nameLabel = new Label("Name: " + event.getName());
    Button editNameButton = new Button("Edit");
    JavaFxDialog editNameDialog = new NameEntry();
    editNameButton.setOnMouseClicked(e -> {
      this.editEventDialogue(editNameDialog, event);
      dialog.close();
    });

    Label startTimeLabel = new Label("Start Time: " + event.getStartTime());
    Button editTimeButton = new Button("Edit");
    JavaFxDialog editTimeDialogue = new TimePicker("New start time: ");
    editTimeButton.setOnMouseClicked(e -> {
      this.editEventDialogue(editTimeDialogue, event);
      dialog.close();
    });

    Label durationLabel = new Label("Duration: " + event.getDuration());
    Button editDurationButton = new Button("Edit");
    JavaFxDialog editDurationDialogue = new TimePicker("New duration: ");
    editDurationDialogue.changeType("DurationPicker");
    editDurationButton.setOnMouseClicked(e -> {
      this.editEventDialogue(editDurationDialogue, event);
      dialog.close();
    });

    Label descriptionLabel = new Label("Description: " + event.getDescription());
    Button editDescriptionButton = new Button("Edit");
    JavaFxDialog editDescriptionDialog = new DescriptionField();
    editDescriptionButton.setOnMouseClicked(e -> {
      this.editEventDialogue(editDescriptionDialog, event);
      dialog.close();
    });

    Button deleteEventButton = new Button("Delete Event");
    deleteEventButton.setOnMouseClicked(e -> {
      this.removeEvent(event);
      dialog.close();
    });

    // Create a grid pane to organize the labels and text fields
    GridPane grid = new GridPane();
    grid.add(nameLabel, 1, 1);
    grid.add(editNameButton, 2, 1);

    grid.add(startTimeLabel, 1, 2);
    grid.add(editTimeButton, 2, 2);

    grid.add(durationLabel, 1, 3);
    grid.add(editDurationButton, 2, 3);

    grid.add(descriptionLabel, 1, 4);
    grid.add(editDescriptionButton, 2, 4);

    grid.add(deleteEventButton, 1, 5);

    dialog.getDialogPane().setContent(grid);

    // Add buttons to the dialog for editing and removing the event
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
    dialog.show();
  }

  /**
   * Show a dialog to edit an event.
   *
   * @param javaFxDialog The dialog to show the user
   * @param event        The event this dialog pertains
   */
  private void editEventDialogue(JavaFxDialog javaFxDialog, Event event) {
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("Edit Event");
    Button saveButton = new Button("Save");
    saveButton.setOnMouseClicked(e -> {
      switch (javaFxDialog.type()) {
        case "NameEntry":
          event.editName(javaFxDialog.getValue());
          this.refreshLists();
          dialog.close();
          this.handleClickedOnEvent(event);
          break;
        case "TimePicker":
          event.editStartTime(javaFxDialog.getValue());
          dialog.close();
          this.handleClickedOnEvent(event);
          break;
        case "DurationPicker":
          event.editDuration(javaFxDialog.getValue());
          dialog.close();
          this.handleClickedOnEvent(event);
          break;
        case "DescriptionField":
          event.editDescription(javaFxDialog.getValue());
          dialog.close();
          this.handleClickedOnEvent(event);
          break;
      }
    });
    GridPane eventEditGrid = new GridPane();
    eventEditGrid.add(javaFxDialog.getHbox(), 1, 1);
    eventEditGrid.add(saveButton, 1, 2);
    dialog.getDialogPane().setContent(eventEditGrid);
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
    dialog.show();
  }

  /**
   * Remove an event.
   *
   * @param event the event to be removed
   */
  private void removeEvent(Event event) {
    for (int i = 0; i < 6; i++) {
      Day curDay = this.week.getDay(i);
      curDay.removeEvent(event);
    }
    this.refreshLists();
  }

  /**
   * Show a dialogue to add a task.
   */
  private void showAddTaskDialogue() {
    // Creating the name entry
    NameEntry nameEntry = new NameEntry();
    // Creating the day selection
    DayPicker chooseDay = new DayPicker();
    // Creating description field
    DescriptionField descriptionField = new DescriptionField();
    Button createTaskButton = new Button("Create");
    createTaskButton.setOnMouseClicked(e -> {
      Weekdays weekday = getEnumFromString(chooseDay.getValue());
      if (this.atCapacityTasks(this.week.getDay(weekday.getCorrespondingIndex()))) {
        Alert alert =
            new Alert(Alert.AlertType.ERROR, "Too many tasks! Remove some before you add more");
        alert.showAndWait();
      } else {
        Task newTask = new Task(nameEntry.getValue(), descriptionField.getValue(), false);
        this.addTask(weekday.getCorrespondingIndex(), newTask);
      }
    });
    Button cancelButton = new Button("Cancel");
    HBox finalButtons = new HBox();
    finalButtons.getChildren().addAll(createTaskButton, cancelButton);
    finalButtons.setAlignment(Pos.CENTER);
    finalButtons.setPrefHeight(40);
    // Creating and showing the dialog
    VBox dialogVbox = new VBox();
    dialogVbox.getChildren()
        .addAll(nameEntry.getHbox(), chooseDay.getHbox(), descriptionField.getHbox(), finalButtons);
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("Create Task");
    dialog.getDialogPane().setContent(dialogVbox);
    dialog.getDialogPane().setPrefWidth(300);
    Window window = dialog.getDialogPane().getScene().getWindow();
    window.setOnCloseRequest(event -> window.hide());
    cancelButton.setOnAction(event -> window.hide());
    dialog.show();
  }

  /**
   * Handler method for adding a task.
   */
  private void addTask(int dayIndex, Task task) {
    this.week.addTask(dayIndex, task);
    Hyperlink newTask = new Hyperlink(task.getName());
    Hyperlink newTaskDup = new Hyperlink(task.getName());
    if (task.getComplete()) {
      newTask.setTextFill(Color.GREEN);
      newTaskDup.setTextFill(Color.GREEN);
    } else {
      newTask.setTextFill(Color.RED);
      newTaskDup.setTextFill(Color.RED);
    }

    newTask.setOnMouseClicked(e -> {
      this.handleClickedOnTask(task);
    });
    newTaskDup.setOnMouseClicked(e -> {
      this.handleClickedOnTask(task);
    });
    this.allTasksList.getItems().add(newTaskDup);
    switch (dayIndex) {
      case 0:
        this.mondayTaskList.getItems().add(newTask);
        break;
      case 1:
        this.tuesdayTaskList.getItems().add(newTask);
        break;
      case 2:
        this.wednesdayTaskList.getItems().add(newTask);
        break;
      case 3:
        this.thursdayTaskList.getItems().add(newTask);
        break;
      case 4:
        this.fridayTaskList.getItems().add(newTask);
        break;
      case 5:
        this.saturdayTaskList.getItems().add(newTask);
        break;
      case 6:
        this.sundayTaskList.getItems().add(newTask);
        break;
    }
    updateWeeklyOverview();
  }

  /**
   * Show a dialog upon clicking on a task.
   *
   * @param task The clicked task
   */
  private void handleClickedOnTask(Task task) {
    // Create a dialog to show event details
    Dialog<Event> dialog = new Dialog<>();
    dialog.setTitle("Task Details");
    dialog.setHeaderText(task.getName());

    // Create labels and text fields for each event detail
    Label nameLabel = new Label("Name: " + task.getName());
    Button editNameButton = new Button("Edit");
    JavaFxDialog editNameDialog = new NameEntry();
    editNameButton.setOnMouseClicked(e -> {
      this.editTaskDialog(editNameDialog, task);
      dialog.close();
    });
    Label descriptionLabel = new Label("Description: " + task.getDescription());
    Button editDescriptionButton = new Button("Edit");
    JavaFxDialog editDescriptionDialog = new DescriptionField();
    editDescriptionButton.setOnMouseClicked(e -> {
      this.editTaskDialog(editDescriptionDialog, task);
      dialog.close();
    });

    Label taskStatusLabel = new Label("Completion Status: " + task.getComplete());
    Button changeStatus = new Button("Change Status");
    changeStatus.setOnMouseClicked(e -> {
      task.changeStatus();
      taskStatusLabel.setText("Completion Status: " + task.getComplete());
      if (task.getComplete()) {
        changeStatus.setText("Set Incomplete");
      } else {
        changeStatus.setText("Set Completed");
      }
      dialog.close();
      updateWeeklyOverview();
      refreshLists();
    });

    Button deleteEventButton = new Button("Delete Task");
    deleteEventButton.setOnMouseClicked(e -> {
      this.removeTask(task);
      dialog.close();
    });

    // Create a grid pane to organize the labels and text fields
    GridPane grid = new GridPane();
    grid.add(nameLabel, 1, 1);
    grid.add(editNameButton, 2, 1);

    grid.add(descriptionLabel, 1, 2);
    grid.add(editDescriptionButton, 2, 2);

    grid.add(taskStatusLabel, 1, 3);
    grid.add(changeStatus, 2, 3);

    grid.add(deleteEventButton, 1, 4);

    dialog.getDialogPane().setPrefWidth(300);
    dialog.getDialogPane().setContent(grid);

    // Add buttons to the dialog for editing and removing the event
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
    dialog.show();
  }

  /**
   * Edit a task.
   *
   * @param javaFxDialog The JavaFx Dialog option
   * @param task         The task to be edited
   */
  private void editTaskDialog(JavaFxDialog javaFxDialog, Task task) {
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("Edit Task");
    Button saveButton = new Button("Save");
    saveButton.setOnMouseClicked(e -> {
      switch (javaFxDialog.type()) {
        case "NameEntry":
          task.editName(javaFxDialog.getValue());
          this.refreshLists();
          dialog.close();
          break;
        case "DescriptionField":
          task.editDescription(javaFxDialog.getValue());
          dialog.close();
          break;
      }
    });
    GridPane taskEditGrid = new GridPane();
    taskEditGrid.add(javaFxDialog.getHbox(), 1, 1);
    taskEditGrid.add(saveButton, 1, 2);
    dialog.getDialogPane().setContent(taskEditGrid);
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
    dialog.show();
  }

  /**
   * Remove this task.
   *
   * @param task The task to be removed
   */
  private void removeTask(Task task) {
    for (int i = 0; i < 6; i++) {
      Day curDay = this.week.getDay(i);
      curDay.removeTask(task);
    }
    this.refreshLists();
  }

  /**
   * Refresh the events and task lists.
   */
  private void refreshLists() {
    mondayEventList.getItems().clear();
    mondayTaskList.getItems().clear();
    tuesdayEventList.getItems().clear();
    tuesdayTaskList.getItems().clear();
    wednesdayEventList.getItems().clear();
    wednesdayTaskList.getItems().clear();
    thursdayEventList.getItems().clear();
    thursdayTaskList.getItems().clear();
    fridayEventList.getItems().clear();
    fridayTaskList.getItems().clear();
    saturdayEventList.getItems().clear();
    saturdayTaskList.getItems().clear();
    sundayEventList.getItems().clear();
    sundayTaskList.getItems().clear();
    allTasksList.getItems().clear();
    for (int i = 0; i < 6; i++) {
      Day curDay = this.week.getDay(i);
      List<Event> events = curDay.getEventsCopy();
      curDay.clearEvents();
      List<Task> tasks = curDay.getTasksCopy(); // implement later
      curDay.clearTasks();
      for (Event event : events) {
        this.addEvent(i, event);
      }
      for (Task task : tasks) {
        this.addTask(i, task);
      }
    }
  }

  /**
   * Determine if the day's tasks are at max capacity.
   *
   * @param day The day to be examined
   * @return Is the day's tasks at max capacity
   */
  private boolean atCapacityTasks(Day day) {
    return day.getTasks().size() >= week.getMaxTasks();
  }

  /**
   * Determine if the day's events are at max capacity.
   *
   * @param day The day to be examined
   * @return Is the day's events at max capacity
   */
  private boolean atCapacityEvents(Day day) {
    return day.getEvents().size() >= week.getMaxEvents();
  }

  /**
   * Handler method for choosing a .bujo file.
   */
  private void chooseFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Bujo File");
    File selectedFile = fileChooser.showOpenDialog(this.stage); // The chosen file
    System.out.println(selectedFile.toString());
    this.week = BujoFileReader.getFile(selectedFile);
    this.initGui();
  }

  /**
   * Handler method for saving a .bujo file.
   */
  private void saveFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Bujo File");
    fileChooser.setInitialFileName(this.week.getName() + ".bujo");
    File selectedFile = fileChooser.showSaveDialog(this.stage);
    try {
      String path = selectedFile.getPath();
      BujoFileWriter.saveWeek(this.week, path);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Cannot save file");
    }
  }
}
