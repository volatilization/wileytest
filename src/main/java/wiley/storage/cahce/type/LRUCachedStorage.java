package wiley.storage.cahce.type;

import wiley.cache.Cache;
import wiley.cache.SizedCache;
import wiley.storage.Storage;
import wiley.storage.cahce.CachedStorage;
import wiley.stored.Stored;

import java.time.Clock;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class LRUCachedStorage extends CachedStorage {
    private final CacheTimeQueue cacheTimeQueue;

    public LRUCachedStorage(Storage storage, Cache cache) {
        super(storage, cache);
        this.cacheTimeQueue = new CacheTimeQueue();
    }

    public CacheTimeQueue getCacheTimeQueue() {
        return cacheTimeQueue;
    }

    @Override
    protected Storage getStorage() {
        return super.getStorage();
    }

    @Override
    protected SizedCache getCache() {
        return (SizedCache) super.getCache();
    }

    @Override
    public Stored get(String name) {
        if (getCache().contains(name)) {
            getCacheTimeQueue().update(name);
            return getCache().get(name);
        }

        Stored stored = getStorage().get(name);
        if (stored != null) {
            if (getCache().isFull()) {
                String key = getCacheTimeQueue().withdrawOldest();
                getCache().delete(key);
            }
            getCache().add(name, stored);
            getCacheTimeQueue().add(name);

        }
        return stored;
    }

    private static class CacheTimeQueue {
        private final Map<String, Instant> timeMap;
//        private final Map<String, Long> timeMap;

        CacheTimeQueue() {
            this(new HashMap<>());
        }

        CacheTimeQueue(Map<String, Instant> timeMap) {
//        CacheTimeQueue(Map<String, Long> timeMap) {
            this.timeMap = timeMap;
        }

        void add(String key) {
            timeMap.put(key, Instant.now());
//              timeMap.put(key, System.currentTimeMillis());
        }

        void update(String key) {
            timeMap.computeIfPresent(key, (k, v) -> Instant.now());
//            timeMap.computeIfPresent(key, (k, v) -> System.currentTimeMillis());
        }

        String withdrawOldest() {
            if (!timeMap.isEmpty()) {
                String result = getFirstElement();
                Instant minInst = timeMap.get(result);
//                Long minInst = timeMap.get(result);
                for (String s : timeMap.keySet()) {
                    if (timeMap.get(s).isBefore(minInst)) {
//                    if (timeMap.get(s) < (minInst)) {
                        minInst = timeMap.get(s);
                        result = s;
                    }
                }
                timeMap.remove(result);
                return result;
            }
            return null;
        }

        private String getFirstElement() {
            return (String) timeMap.keySet().toArray()[0];
        }
    }
}
