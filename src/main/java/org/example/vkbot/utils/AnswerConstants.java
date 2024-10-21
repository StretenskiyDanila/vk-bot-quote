package org.example.vkbot.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AnswerConstants {

    PREFIX_BOT_ANSWER("Вы сказали: ");

    private final String message;

}
