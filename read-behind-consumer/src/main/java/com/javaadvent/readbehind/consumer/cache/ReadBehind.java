package com.javaadvent.readbehind.consumer.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface ReadBehind {
}
