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
    @GetMapping
    String produce(@RequestParam(required = false, defaultValue = "default") String name) throws InterruptedException {
        Thread.sleep(5000);
        return name + " : " + new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
    }
}
