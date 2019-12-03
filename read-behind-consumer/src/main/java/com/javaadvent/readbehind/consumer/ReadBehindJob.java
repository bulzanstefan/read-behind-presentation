package com.javaadvent.readbehind.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReadBehindJob {
    private final CachedInvocations invocations;

    @Scheduled(fixedDelay = 10000)
    public void job() {
        System.out.println("execute job");
        invocations.nextInvocations()
                .forEach(System.out::println);
    }
}
