package org.example.vkbot.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    private static final String PREFIX = "https://";

    public String getServerHost(String server) {
        return server.split("/")[2];
    }

    public String getServerPath(String server) {
        return server.substring(PREFIX.length() + getServerHost(server).length());
    }

}
