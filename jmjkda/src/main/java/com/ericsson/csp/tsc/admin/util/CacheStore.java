package com.ericsson.csp.tsc.admin.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.events.Event;
import org.apache.ignite.events.EventType;
import org.apache.ignite.lang.IgnitePredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheStore implements InitializingBean, IgnitePredicate<Event> {

    /**
     * serialVersionUID
     */
    private static final long                        serialVersionUID = 6675163451039264607L;

    private static final Logger                      LOGGER           = LoggerFactory.getLogger(CacheStore.class);

    @Autowired
    private Ignite                                   ignite;

    private ConcurrentMap<String, IgniteCache<?, ?>> cacheMap         = new ConcurrentHashMap<>();

    public IgniteCache<?, ?> getOrCreateCache(String cacheName) {
        IgniteCache<?, ?> cache = cacheMap.get(cacheName);
        if (cache == null) {
            try {
                IgniteCache<?, ?> newCache = getIgnite().getOrCreateCache(cacheName);
                cacheMap.putIfAbsent(cacheName, newCache);

                cache = cacheMap.get(cacheName);
            } catch (Exception e) {
                LOGGER.error("Could not getOrCreateCache in Ignite", e);
            }
        }
        return cache;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ignite.events().localListen(this, EventType.EVT_CLIENT_NODE_DISCONNECTED);
    }

    @Override
    public boolean apply(Event e) {
        LOGGER.info("client node disconnected...");
        cacheMap.clear();
        return true;
    }

    public Ignite getIgnite() {
        return ignite;
    }

    public void setIgnite(Ignite ignite) {
        this.ignite = ignite;
    }
}
