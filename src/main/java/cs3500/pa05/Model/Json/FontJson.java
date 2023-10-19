package cs3500.pa05.Model.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A java record to represent a Theme
 *
 * @param family Name of font family
 * @param size Size of font
 */
public record FontJson(
    @JsonProperty("family") String family,
    @JsonProperty("size") double size
) {
}
