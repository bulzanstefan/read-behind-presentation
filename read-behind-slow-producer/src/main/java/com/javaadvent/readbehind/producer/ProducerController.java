package com.javaadvent.readbehind.producer;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("producer")
public class ProducerController {
    @GetMapping
    String produce(@RequestParam String name) throws InterruptedException {
        Thread.sleep(5000);
        return name + " : " + new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
    }
}
