package com.javaadvent.readbehind.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReadBehindConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadBehindConsumerApplication.class, args);
    }

}
