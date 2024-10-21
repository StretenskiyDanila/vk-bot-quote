package org.example.vkbot;

import lombok.RequiredArgsConstructor;
import org.example.vkbot.services.VkBotService;
import org.example.vkbot.services.impl.VkBotServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class VkBotApplication implements CommandLineRunner {

    private final VkBotService vkBotService;

    public static void main(String[] args) {
        SpringApplication.run(VkBotApplication.class, args);
    }

    @Override
    public void run(String... args) {
        vkBotService.startBot();
    }

}
