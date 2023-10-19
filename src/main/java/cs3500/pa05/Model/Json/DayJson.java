package cs3500.pa05.Model.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * A java record to represent a Day
 *
 * @param tasks list of tasks for that day
 * @param events list of events for that day
 */
public record DayJson(
    @JsonProperty("tasks") List<TaskJson> tasks,
    @JsonProperty("events") List<EventJson> events
) {
}
