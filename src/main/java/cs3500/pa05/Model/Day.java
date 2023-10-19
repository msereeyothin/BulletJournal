package cs3500.pa05.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a day in a bullet journal.
 */
public class Day {
  private List<Task> tasks = new ArrayList<>();
  private List<Event> events = new ArrayList<>();

  /**
   * Create a new day.
   */
  public Day() {

  }


  /**
   * Add a task to the list of tasks.
   *
   * @param task The task to be added
   */
  public void addTask(Task task) {
    this.tasks.add(task);
  }

  /**
   * Add an event to the list of events.
   *
   * @param event The event to be added
   */
  public void addEvent(Event event) {
    this.events.add(event);
  }


  /**
   * Get this day's tasks.
   *
   * @return The day's tasks
   */
  public List<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Get this day's events.
   *
   * @return The day's events
   */
  public List<Event> getEvents() {
    return this.events;
  }

  /**
   * Remove an event from this day.
   *
   * @param event The event to be removed
   */
  public void removeEvent(Event event) {
    for (int i = 0; i < this.events.size(); i++) {
      if (event == this.events.get(i)) {
        this.events.remove(i);
      }
    }
  }

  /**
   * Remove a task from this day.
   *
   * @param task The task to be removed
   */
  public void removeTask(Task task) {
    for (int i = 0; i < this.tasks.size(); i++) {
      if (task == this.tasks.get(i)) {
        this.tasks.remove(i);
      }
    }
  }

  /**
   * Clear this day's events.
   */
  public void clearEvents() {
    this.events.clear();
  }

  /**
   * Clear this day's tasks.
   */
  public void clearTasks() {
    this.tasks.clear();
  }

  /**
   * Get a deep copy of the list of events.
   *
   * @return A deep copy of the list of events
   */
  public List<Event> getEventsCopy() {
    List<Event> copiedEvents = new ArrayList<>();
    for (Event event : this.events) {
      Event newEvent = event.getCopy();
      copiedEvents.add(newEvent);
    }
    return copiedEvents;
  }

  /**
   * Get a deep copy of the list of tasks.
   *
   * @return A deep copy of the list of tasks
   */
  public List<Task> getTasksCopy() {
    List<Task> copiedTasks = new ArrayList<>();
    for (Task task : this.tasks) {
      Task newTask = task.getCopy();
      copiedTasks.add(newTask);
    }
    return copiedTasks;
  }

  /**
   * Create new day in File Reader
   *
   * @param tasks List of tasks
   * @param events List of events
   * @return new Day object
   */
  public static Day day(List<Task> tasks, List<Event> events) {
    Day day = new Day();
    for (Task task : tasks) {
      day.addTask(task);
    }
    for (Event event : events) {
      day.addEvent(event);
    }
    return day;
  }
}
