package com.javaadvent.readbehind.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
    @GetMapping
    public String consume() {
        return new RestTemplate().getForEntity("http://localhost:8888/producer", String.class).getBody();
    }
}
