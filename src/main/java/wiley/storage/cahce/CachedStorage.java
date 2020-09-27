package wiley.storage.cahce;

import wiley.cache.Cache;
import wiley.storage.Storage;
import wiley.storage.cahce.type.CacheType;
import wiley.stored.Stored;

public class CachedStorage implements Storage {
    private Cache cache;
    private final Storage storage;

    public CachedStorage(Storage storage, CacheType type, int cacheSize) {
        this.storage = CachedStorageFactory.getCachedStorage(storage, type, cacheSize);
    }

    protected CachedStorage(Storage storage, Cache cache) {
        this.storage = storage;
        this.cache = cache;
    }

    protected Cache getCache() {
        return cache;
    }

    protected Storage getStorage() {
        return storage;
    }

    @Override
    public void add(Stored stored) {
        getStorage().add(stored);
    }

    @Override
    public Stored get(String name) {
        return getStorage().get(name);
    }
}

