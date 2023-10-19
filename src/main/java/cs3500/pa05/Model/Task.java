package cs3500.pa05.Model;

/**
 * Represents a bujo task.
 */
public class Task {
  private String name;
  private String description;
  private boolean complete;

  /**
   * Create a new task.
   *
   * @param name        The task name
   * @param description The task description
   * @param complete    The status of the task
   */
  public Task(String name, String description, boolean complete) {
    this.name = name;
    this.description = description;
    this.complete = complete;
  }

  /**
   * Change the completion status of the task.
   */
  public void changeStatus() {
    this.complete = !this.complete;
  }

  /**
   * Get the completion state of this task.
   *
   * @return The completion state of this task
   */
  public boolean getComplete() {
    return this.complete;
  }

  /**
   * Get the name of the task
   *
   * @return The name of the task
   */
  public String getName() {
    return this.name;
  }

  /**
   * Change the task description.
   *
   * @param description The new description
   */
  public void changeDescription(String description) {
    this.description = description;
  }

  /**
   * Get a deep copy of this task.
   *
   * @return A deep copy of this task
   */
  public Task getCopy() {
    return new Task(this.name, this.description, this.complete);
  }

  /**
   * Edit the name of this task.
   *
   * @param name The new name
   */
  public void editName(String name) {
    this.name = name;
  }

  /**
   * Edit the description of this task.
   *
   * @param description The new description
   */
  public void editDescription(String description) {
    this.description = description;
  }

  /**
   * Get the description of this task.
   *
   * @return The description
   */
  public String getDescription() {
    return this.description;
  }
}
