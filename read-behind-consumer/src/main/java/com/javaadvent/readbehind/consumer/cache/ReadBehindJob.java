package com.javaadvent.readbehind.consumer.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.MethodInvoker;

import java.util.Set;

import static com.javaadvent.readbehind.consumer.ProducerClient.CACHE_NAME;
import static java.util.Optional.ofNullable;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReadBehindJob {
    private final CachedInvocations invocations;
    private final KeyGenerator keyGenerator;
    private final CacheManager cacheManager;

    @Scheduled(fixedDelay = 10000)
    public void job() {
        final Set<CachedInvocation> invocationsToRefresh = invocations.nextInvocations();
        if (!invocationsToRefresh.isEmpty()) {
            log.info("Refreshing " + invocationsToRefresh.size() + " invocations");
        }
        invocationsToRefresh
                .forEach(this::refreshForInvocation);
    }

    private void refreshForInvocation(CachedInvocation invocation) {
        var result = execute(invocation);
        var cacheKey = keyGenerator.generate(invocation.getTargetBean(),
                invocation.getTargetMethod(),
                invocation.getArguments());
        ofNullable(cacheManager.getCache(CACHE_NAME))
                .ifPresent(cache -> cache.put(cacheKey, result));
    }

    private Object execute(CachedInvocation invocation) {
        final MethodInvoker invoker = new MethodInvoker();
        invoker.setTargetObject(invocation.getTargetBean());
        invoker.setArguments(invocation.getArguments());
        invoker.setTargetMethod(invocation.getTargetMethod().getName());
        try {
            invoker.prepare();
            return invoker.invoke();
        } catch (Exception e) {
            log.error("Error when trying to reload the cache entries ", e);
            return null;
        }
    }

}
