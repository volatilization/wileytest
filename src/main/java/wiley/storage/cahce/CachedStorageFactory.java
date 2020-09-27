package wiley.storage.cahce;

import wiley.cache.BaseCache;
import wiley.cache.SizedCache;
import wiley.storage.Storage;
import wiley.storage.cahce.type.CacheType;
import wiley.storage.cahce.type.LFUCachedStorage;
import wiley.storage.cahce.type.LRUCachedStorage;

public abstract class CachedStorageFactory {
    public static CachedStorage getCachedStorage(Storage storage, CacheType type, int cacheSize) {
        switch (type) {
            case LFU:
                return new LFUCachedStorage(storage, new SizedCache(cacheSize));
            case LRU:
                return new LRUCachedStorage(storage, new SizedCache(cacheSize));
            default:
                return new CachedStorage(storage, new BaseCache());
        }
    }
}
