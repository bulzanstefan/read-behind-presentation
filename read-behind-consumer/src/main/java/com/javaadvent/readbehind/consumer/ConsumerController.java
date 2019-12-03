package com.javaadvent.readbehind.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("consumer")
@RequiredArgsConstructor
public class ConsumerController {
    private final ProducerClient producerClient;

    @GetMapping
    public String consume(@RequestParam(required = false) String name) {
        return producerClient.performRequest(ofNullable(name).orElse("default"));
    }
}
