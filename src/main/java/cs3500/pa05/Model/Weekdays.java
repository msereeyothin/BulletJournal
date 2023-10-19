package cs3500.pa05.Model;

/**
 * An enumeration for days of the week.
 */
public enum Weekdays {
  Monday(0),
  Tuesday(1),
  Wednesday(2),
  Thursday(3),
  Friday(4),
  Saturday(5),
  Sunday(6);

  private int value;

  /**
   * Create a weekday enumeration.
   *
   * @param value The index of the weekday
   */
  Weekdays(int value) {
    this.value = value;
  }

  /**
   * Get the corresponding index of the day.
   *
   * @return The corresponding index
   */
  public int getCorrespondingIndex() {
    return value;
  }

  /**
   * Get the weekday enumeration from the given string.
   *
   * @param string The given string
   * @return The weekday enumeration
   * @throws IllegalArgumentException If the given string is not a weekday
   */
  public static Weekdays getEnumFromString(String string) throws IllegalArgumentException {
    switch (string) {
      case "Monday":
        return Monday;
      case "Tuesday":
        return Tuesday;
      case "Wednesday":
        return Wednesday;
      case "Thursday":
        return Thursday;
      case "Friday":
        return Friday;
      case "Saturday":
        return Saturday;
      case "Sunday":
        return Sunday;
    }
    throw new IllegalArgumentException("Given string must match the enumeration");
  }
}
