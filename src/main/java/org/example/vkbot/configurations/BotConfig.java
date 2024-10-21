package org.example.vkbot.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class BotConfig {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.api-version}")
    private String apiVersion;

    @Value("${bot.id}")
    private String botId;

}
