package cs3500.pa05.Model.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A java record to represent an Event
 *
 * @param name Name of Event
 * @param startTime Start time of the Event
 * @param duration Duration of the Event
 * @param description Description for the Event
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("startTime") String startTime,
    @JsonProperty("duration") String duration,
    @JsonProperty("description") String description
) {
}
