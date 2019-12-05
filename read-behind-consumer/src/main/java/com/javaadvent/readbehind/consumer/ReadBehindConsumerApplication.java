package com.javaadvent.readbehind.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class ReadBehindConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadBehindConsumerApplication.class, args);
    }

    @Bean
    KeyGenerator myKeyGenerator() {
        return (target, method, params) -> Stream.of(params)
                .map(String::valueOf)
                .collect(joining("-"));
    }
}
