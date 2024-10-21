package org.example.vkbot.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.example.vkbot.utils.UrlConstants.SCHEMA_BASE;

@Component
@RequiredArgsConstructor
public class WebClientWork {

    private final WebClient webClient;

    public <T> Mono<T> getMono(MultiValueMap<String, String> map, String host, String path, Class<T> clazz) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(SCHEMA_BASE.getMessage())
                        .host(host)
                        .path(path)
                        .queryParams(map)
                        .build())
                .retrieve()
                .bodyToMono(clazz);
    }

}
