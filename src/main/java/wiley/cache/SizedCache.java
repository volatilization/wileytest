package wiley.cache;

import wiley.stored.Stored;

import java.util.HashMap;
import java.util.Map;

public class SizedCache extends BaseCache {
    private final int size;
    private static int DEFAULT_SIZE = 15;

    public SizedCache(int size) {
        this(size, new HashMap<>());
    }

    public SizedCache(int size, Map<String, Stored> cacheMap) {
        super(cacheMap);
        if (size > 0) {
            this.size = size;
        } else {
            this.size = DEFAULT_SIZE;
        }
    }

    public void add(String key, Stored value) {
        if (getCacheMap().size() < size) {
            super.add(key, value);
        }
    }

    public boolean isFull() {
        return getCacheMap().size() == size;
    }
}
