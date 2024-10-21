package org.example.vkbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VkLongPollServerResponse {
    private VkLongPollParams response;
}