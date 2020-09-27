package wiley.storage.cahce.type;

import wiley.cache.Cache;
import wiley.cache.SizedCache;
import wiley.storage.Storage;
import wiley.storage.cahce.CachedStorage;
import wiley.stored.Stored;

import java.util.HashMap;
import java.util.Map;

public class LFUCachedStorage extends CachedStorage {
    private final CacheQueue cacheQueue;

    public LFUCachedStorage(Storage storage, Cache cache) {
        super(storage, cache);
        this.cacheQueue = new CacheQueue();
    }

    @Override
    protected SizedCache getCache() {
        return (SizedCache) super.getCache();
    }

    protected CacheQueue getCacheQueue() {
        return cacheQueue;
    }

    @Override
    public Stored get(String name) {
        if (getCache().contains(name)) {
            getCacheQueue().increment(name);
            return getCache().get(name);
        }

        Stored stored = getStorage().get(name);
        if (getCache().isFull()) {
            String key = getCacheQueue().withdrawMin();
            getCache().delete(key);
        }
        getCache().add(name, stored);
        getCacheQueue().add(name);

        return stored;
    }

    private static class CacheQueue {
        private final Map<String, Integer> queueMap;

        CacheQueue() {
            this(new HashMap<>());
        }

        CacheQueue(Map<String, Integer> cacheQueue) {
            this.queueMap = cacheQueue;
        }

        void add(String key) {
            queueMap.put(key, 1);
        }

        void increment(String key) {
            queueMap.computeIfPresent(key, (k, v) -> v + 1);
        }

        String withdrawMin() {
            if (!queueMap.isEmpty()) {
                String result = getFirstElement();
                int min = queueMap.get(result);
                for (String s : queueMap.keySet()) {
                    if (queueMap.get(s) < min) {
                        min = queueMap.get(s);
                        result = s;
                    }
                }
                queueMap.remove(result);
                return result;
            }
            return null;
        }

        private String getFirstElement() {
            return (String) queueMap.keySet().toArray()[0];
        }
    }
}
