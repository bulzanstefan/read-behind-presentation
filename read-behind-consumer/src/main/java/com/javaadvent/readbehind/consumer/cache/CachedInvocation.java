package com.javaadvent.readbehind.consumer.cache;

import lombok.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Value
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CachedInvocation {
    private Object targetBean;
    private Object[] arguments;
    private Method targetMethod;

    public CachedInvocation(JoinPoint joinPoint) {
        targetBean = joinPoint.getTarget();
        arguments = joinPoint.getArgs();
        targetMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
    }
}
