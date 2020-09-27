package wiley.storage.cahce;

import org.junit.Test;
import wiley.storage.ListStorage;
import wiley.storage.cahce.type.CacheType;
import wiley.storage.cahce.type.LFUCachedStorage;
import wiley.storage.cahce.type.LRUCachedStorage;

import static org.junit.Assert.*;

public class CachedStorageFactoryTest {

    @Test(expected = NullPointerException.class)
    public void getCachedStorage() {
        LFUCachedStorage lfuCachedStorage = (LFUCachedStorage) CachedStorageFactory.getCachedStorage(new ListStorage(), CacheType.LFU, 1);
        assertEquals(lfuCachedStorage.getClass(), LFUCachedStorage.class);

        LRUCachedStorage lruCachedStorage = (LRUCachedStorage) CachedStorageFactory.getCachedStorage(new ListStorage(), CacheType.LRU, 1);
        assertEquals(lruCachedStorage.getClass(), LRUCachedStorage.class);

        CachedStorageFactory.getCachedStorage(new ListStorage(), null, 1);
    }
}