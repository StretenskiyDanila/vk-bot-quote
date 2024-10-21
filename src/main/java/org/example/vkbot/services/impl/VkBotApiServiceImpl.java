package org.example.vkbot.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.vkbot.configurations.BotConfig;
import org.example.vkbot.dto.VkLongPollResponse;
import org.example.vkbot.dto.VkLongPollServerResponse;
import org.example.vkbot.dto.VkUpdate;
import org.example.vkbot.helpers.BuildQueryParams;
import org.example.vkbot.helpers.WebClientWork;
import org.example.vkbot.services.VkBotApiService;
import org.example.vkbot.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import static org.example.vkbot.utils.AnswerConstants.PREFIX_BOT_ANSWER;
import static org.example.vkbot.utils.LogMessages.BOT_ANSWER;
import static org.example.vkbot.utils.ParamsConstants.*;
import static org.example.vkbot.utils.UrlConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class VkBotApiServiceImpl implements VkBotApiService {

    private final WebClientWork webClientWork;
    private final BotConfig botConfig;
    private final BuildQueryParams buildQueryParams;

    public void startLongPolling() {
        MultiValueMap<String, String> map = buildQueryParams.buildQueries(
                PARAMS_SERVER,
                new String[]{botConfig.getBotId(), botConfig.getToken(), botConfig.getApiVersion()});

        webClientWork
                .getMono(map, HOST_BASE.getMessage(), PATH_LONG_POLL_SERVER.getMessage(), VkLongPollServerResponse.class)
                .subscribe(response -> {
                    String server = response.getResponse().getServer();
                    String key = response.getResponse().getKey();
                    String ts = response.getResponse().getTs();
                    pollUpdates(server, key, ts);
                });
    }

    private void pollUpdates(String server, String key, String ts) {
        String serverHost = StringUtils.getServerHost(server);
        String path = StringUtils.getServerPath(server);

        MultiValueMap<String, String> map = buildQueryParams.buildQueries(
                PARAMS_UPDATE,
                new String[]{"a_check", key, ts, "25"});

        webClientWork
                .getMono(map, serverHost, path, VkLongPollResponse.class)
                .subscribe(updateResponse -> {
                    if (updateResponse.getUpdates() != null) {
                        updateResponse.getUpdates().forEach(this::processUpdate);
                    }
                    pollUpdates(server, key, updateResponse.getTs());
                });
    }

    private void processUpdate(VkUpdate update) {
        if (update.getType().equals("message_new")) {
            String userId = update.getObject().getMessage().getFromId().toString();
            String text = update.getObject().getMessage().getText();
            sendMessage(userId, PREFIX_BOT_ANSWER.getMessage() + text);
        }
    }

    private void sendMessage(String userId, String message) {
        MultiValueMap<String, String> map = buildQueryParams.buildQueries(
                PARAMS_SEND,
                new String[]{botConfig.getToken(), botConfig.getApiVersion(), userId, message, String.valueOf(System.currentTimeMillis())});

        webClientWork
                .getMono(map, HOST_BASE.getMessage(), PATH_SEND_MESSAGE.getMessage(), String.class)
                .subscribe();

        log.info(BOT_ANSWER.getMessage(), message);
    }

}
