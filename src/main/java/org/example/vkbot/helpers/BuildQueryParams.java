package org.example.vkbot.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class BuildQueryParams {

    public MultiValueMap<String, String> buildQueries(String[] keys, String[] values) {
        return IntStream.range(0, Math.min(keys.length, values.length))
                .collect(
                        LinkedMultiValueMap::new,
                        (temp, i) -> temp.add(keys[i], values[i]),
                        LinkedMultiValueMap::putAll);
    }

}
