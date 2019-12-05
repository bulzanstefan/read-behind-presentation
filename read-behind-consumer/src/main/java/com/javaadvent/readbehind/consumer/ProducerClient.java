package com.javaadvent.readbehind.consumer;


import com.javaadvent.readbehind.consumer.cache.ReadBehind;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProducerClient {
    public static final String CACHE_NAME = "read-behind-cache";

    @ReadBehind
    @Cacheable(value = CACHE_NAME, keyGenerator = "myKeyGenerator")
    public String performRequest(String name) {
        return new RestTemplate().getForEntity(
                "http://localhost:8888/producer?name={name}",
                String.class, name)
                .getBody();
    }
}
