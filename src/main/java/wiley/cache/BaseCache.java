package wiley.cache;

import wiley.stored.Stored;

import java.util.HashMap;
import java.util.Map;

public class BaseCache implements Cache {
    private final Map<String, Stored> cacheMap;

    public BaseCache() {
        this(new HashMap<>());
    }

    public BaseCache(Map<String, Stored> cacheMap) {
        this.cacheMap = cacheMap;
    }

    protected Map<String, Stored> getCacheMap() {
        return cacheMap;
    }

    public void add(String key, Stored value) {
        getCacheMap().put(key, value);
    }

    public Stored get(String key) {
        return getCacheMap().get(key);
    }

    public void delete(String key) {
        getCacheMap().remove(key);
    }

    public boolean contains(String key) {
        return getCacheMap().containsKey(key);
    }
}
