package com.javaadvent.readbehind.producer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("producer")
public class ProducerController {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");

    @GetMapping
    String produce(@RequestParam String name) throws InterruptedException {
        Thread.sleep(5000);
        return name + " : " + SIMPLE_DATE_FORMAT.format(new Date());
    }
}
