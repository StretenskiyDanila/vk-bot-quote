package org.example.vkbot.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.vkbot.services.VkBotApiService;
import org.example.vkbot.services.VkBotService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VkBotServiceImpl implements VkBotService {

    private final VkBotApiService vkBotApiService;

    public void startBot() {
        vkBotApiService.startLongPolling();
    }

}