package cs3500.pa05.Model;

/**
 * Represents an event in a bullet journal.
 */
public class Event {
  private String name;
  private String startTime;
  private String duration;
  private String description;

  /**
   * Create a new event.
   *
   * @param name        The event name
   * @param startTime   The event start time
   * @param duration    The event duration
   * @param description THe event description
   */
  public Event(String name, String startTime, String duration, String description) {
    this.name = name;
    this.startTime = startTime;
    this.duration = duration;
    this.description = description;
  }

  /**
   * Get the name of the Event
   *
   * @return The name of the Event
   */
  public String getName() {
    return this.name;
  }

  /**
   * The start time of the Event
   *
   * @return The start time of the Event
   */
  public String getStartTime() {
    return this.startTime;
  }

  /**
   * The duration of the Event
   *
   * @return The duration of the Event
   */
  public String getDuration() {
    return this.duration;
  }

  /**
   * Get this event's description.
   *
   * @return The description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Edit this event's description.
   *
   * @param description The new description
   */
  public void editDescription(String description) {
    this.description = description;
  }

  /**
   * Edit this event's start time.
   *
   * @param startTime The new start time
   */
  public void editStartTime(String startTime) {
    this.startTime = startTime;
  }

  /**
   * Edit this event's duration.
   *
   * @param duration The new duration
   */
  public void editDuration(String duration) {
    this.duration = duration;
  }

  /**
   * Edit this event's name.
   *
   * @param name The new name
   */
  public void editName(String name) {
    this.name = name;
  }

  /**
   * Get a deep copy of this event.
   *
   * @return A deep copy of this event
   */
  public Event getCopy() {
    return new Event(this.name, this.startTime, this.duration, this.description);
  }
}
