package com.javaadvent.readbehind.consumer;

import lombok.*;
import org.aspectj.lang.JoinPoint;

@Value
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CachedInvocation {
    private Object targetBean;
    private Object[] arguments;
    private String targetMethodName;

    public CachedInvocation(JoinPoint joinPoint) {
        targetBean = joinPoint.getTarget();
        arguments = joinPoint.getArgs();
        targetMethodName = joinPoint.getSignature().getName();
    }
}
