package org.example.vkbot.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UrlConstants {

    SCHEMA_BASE("https"),
    HOST_BASE("api.vk.com"),
    PATH_LONG_POLL_SERVER("/method/groups.getLongPollServer"),
    PATH_SEND_MESSAGE("/method/messages.send");

    private final String message;

}
