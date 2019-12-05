package com.javaadvent.readbehind.consumer.cache;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ReadBehindAspect implements Ordered {
    private final CachedInvocations invocations;

    @Before("@annotation(com.javaadvent.readbehind.consumer.cache.ReadBehind)")
    public Object cacheInvocation(JoinPoint joinPoint) {
        invocations.addInvocation(new CachedInvocation(joinPoint));
        return null;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
