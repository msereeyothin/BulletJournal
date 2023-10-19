package cs3500.pa05.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a week in a bujo.
 */
public class Week {
  private Theme theme;
  private List<Theme> themeList = new ArrayList<>();
  private String name;
  private int maxTasks;
  private int maxEvents;
  private List<Day> days = Arrays.asList(new Day[7]);

  /**
   * Create a new default week.
   */
  public Week() {
    for (int i = 0; i < this.days.size(); i++) {
      this.days.set(i, new Day());
    }
    this.theme = Theme.lightTheme(); // Default Theme
    this.name = "New Week"; // Default name
    this.maxEvents = 10; // Default value
    this.maxTasks = 10; // Default value
    this.themeList.add(Theme.lightTheme());
    this.themeList.add(Theme.darkTheme());
    this.themeList.add(Theme.sunsetTheme());
  }

  /**
   * Create a custom week.
   *
   * @param name      The name of the week
   * @param days      The list of days in the week
   * @param theme     The theme of the week
   * @param maxEvents The max number of events
   * @param maxTasks  The max number of tasks
   * @param themeList The list of themes
   */
  public Week(String name, List<Day> days, Theme theme, int maxEvents, int maxTasks,
              List<Theme> themeList) {
    if (!(days.size() == 7)) {
      throw new IllegalArgumentException("Invalid amount of days");
    }
    this.name = name;
    this.days = days;
    this.theme = theme;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.themeList = themeList;
  }

  /**
   * Get the name of this week.
   *
   * @return The name of this week
   */
  public String getName() {
    return this.name;
  }

  /**
   * Change the theme of this week.
   *
   * @param theme The theme of this week
   */
  public void changeTheme(Theme theme) {
    this.theme = theme;
  }

  /**
   * Change the name of this week.
   *
   * @param name The new name
   */
  public void changeName(String name) {
    this.name = name;
  }

  /**
   * Set the max amount of events.
   *
   * @param max The new maximum to be set
   */
  public void setMaxEvents(int max) {
    this.maxEvents = max;
  }

  /**
   * Set the max amount of tasks.
   *
   * @param max The new maximum to be set
   */
  public void setMaxTasks(int max) {
    this.maxTasks = max;
  }

  /**
   * Get every task for this week.
   *
   * @return All the week's tasks
   */
  public List<Task> getAllTasks() {
    List<Task> weeklyTasks = new ArrayList<>();
    for (Day day : this.days) {
      weeklyTasks.addAll(day.getTasks());
    }
    return weeklyTasks;
  }

  /**
   * Add an event to a day of the week.
   *
   * @param dayIndex The day to add the event to
   *                 0 - Monday
   *                 1 - Tuesday
   *                 2 - Wednesday
   *                 3 - Thursday
   *                 4 - Friday
   *                 5 - Saturday
   *                 6 - Sunday
   * @param event    The event to be added.
   */
  public void addEvent(int dayIndex, Event event) {
    Day thisDay = this.days.get(dayIndex);
    thisDay.addEvent(event);
  }

  /**
   * Add a task to the day of the week
   *
   * @param dayIndex The day to add the event to
   *                 0 - Monday
   *                 1 - Tuesday
   *                 2 - Wednesday
   *                 3 - Thursday
   *                 4 - Friday
   *                 5 - Saturday
   *                 6 - Sunday
   * @param task     The task to be added.
   */
  public void addTask(int dayIndex, Task task) {
    Day thisDay = this.days.get(dayIndex);
    thisDay.addTask(task);
  }

  /**
   * Get every finished task for the week.
   *
   * @return List of finished weekly tasks
   */
  public List<Task> getCompletedTasks() {
    List<Task> weeklyTasks = new ArrayList<>();
    for (Day day : this.days) {
      List<Task> dailyTasks = day.getTasks();
      for (Task task : dailyTasks) {
        if (task.getComplete()) {
          weeklyTasks.add(task);
        }
      }
    }
    return weeklyTasks;
  }

  /**
   * Get all events for the week.
   *
   * @return List of weekly events
   */
  public List<Event> getAllEvents() {
    List<Event> weeklyEvents = new ArrayList<>();
    for (Day day : this.days) {
      weeklyEvents.addAll(day.getEvents());
    }
    return weeklyEvents;
  }

  /**
   * Get a day of the week given the day index.
   *
   * @param dayIndex The day index
   * @return The day
   */
  public Day getDay(int dayIndex) {
    return this.days.get(dayIndex);
  }

  /**
   * Get theme of the week.
   *
   * @return Theme
   */
  public Theme getTheme() {
    return this.theme;
  }

  /**
   * Get this week's theme list.
   *
   * @return The list of themes
   */
  public List<Theme> getThemeList() {
    return this.themeList;
  }

  /**
   * Add a theme to the theme list.
   *
   * @param theme The theme to be added
   */
  public void addToThemes(Theme theme) {
    this.themeList.add(theme);
  }

  /**
   * Get list of days and the information it stores
   *
   * @return list of all days in the week
   */
  public List<Day> getDays() {
    return this.days;
  }

  /**
   * Get max number of tasks
   *
   * @return max number of tasks
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Get max number of events
   *
   * @return max number of events
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

}
