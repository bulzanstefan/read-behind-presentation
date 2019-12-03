package com.javaadvent.readbehind.consumer;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProducerClient {
    @ReadBehind
    @Cacheable("read-behind-cache")
    public String performRequest(String name) {
        return new RestTemplate().getForEntity(
                "http://localhost:8888/producer?name={name}",
                String.class, name)
                .getBody();
    }
}
