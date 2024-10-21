package org.example.vkbot.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LogMessages {

    START_METHOD("Start method {}"),
    END_METHOD("End method {}"),
    ERROR("Error in method {}.\nTrace: {}"),
    REQUEST("Method {} execute with params {}"),

    BOT_ANSWER("Bot answer: {}");

    private final String message;

}
