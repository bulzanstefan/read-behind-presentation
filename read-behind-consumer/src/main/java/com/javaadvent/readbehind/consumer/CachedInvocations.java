package com.javaadvent.readbehind.consumer;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.synchronizedSet;

@Component
public class CachedInvocations {
    private final Set<CachedInvocation> invocations = synchronizedSet(new HashSet<>());

    public void addInvocation(CachedInvocation invocation) {
        invocations.add(invocation);
    }

    public Set<CachedInvocation> nextInvocations() {
        final Set<CachedInvocation> invocationToRefresh = new HashSet<>(invocations);
        invocations.removeAll(invocationToRefresh);
        return invocationToRefresh;
    }
}
