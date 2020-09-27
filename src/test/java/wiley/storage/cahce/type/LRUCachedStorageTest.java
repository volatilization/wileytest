package wiley.storage.cahce.type;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wiley.cache.Cache;
import wiley.cache.SizedCache;
import wiley.storage.ListStorage;
import wiley.storage.Storage;
import wiley.stored.Stored;
import wiley.stored.StoredObject;

import static org.junit.Assert.*;

public class LRUCachedStorageTest {
    private LRUCachedStorage lruCachedStorage;
    private Storage storage;
    private Cache cache;
    private final static int CACHE_SIZE = 10;
    private final static String KEY = "key";
    private final Stored STORED = new StoredObject(KEY, 0);

    @Before
    public void setUp() {
        storage = new ListStorage();
        cache = new SizedCache(CACHE_SIZE);
        lruCachedStorage = new LRUCachedStorage(storage, cache);
    }

    @After
    public void tearDown() {
        lruCachedStorage = null;
        storage = null;
        cache = null;
    }

    @Test
    public void getCache() {
        assertEquals(lruCachedStorage.getCache().getClass(), SizedCache.class);
    }

    @Test
    public void getCacheTimeQueue() {
        assertNotNull(lruCachedStorage.getCacheTimeQueue());
    }

    @Test
    public void getFromCache() {
        cache.add(KEY, STORED);

        assertEquals(lruCachedStorage.get(KEY), STORED);
        assertNull(storage.get(KEY));
    }

    @Test
    public void getFromStorageAndAddToCache() {
        storage.add(STORED);

        assertFalse(cache.contains(KEY));
        assertEquals(lruCachedStorage.get(KEY), STORED);
        assertTrue(cache.contains(KEY));
    }

    @Test
    public void getFromStorageAndUpdate() {
        storage.add(STORED);

        lruCachedStorage.get(KEY);
        assertTrue(lruCachedStorage.getCache().contains(KEY));
        fillCacheAndStorage();
        assertFalse(lruCachedStorage.getCache().contains(KEY));
        lruCachedStorage.get(KEY);
        assertTrue(lruCachedStorage.getCache().contains(KEY));
    }

    private void fillCacheAndStorage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        for (int i = 0; i < CACHE_SIZE; i++) {
            Stored stored = new StoredObject(KEY + i, i);
            storage.add(stored);
            lruCachedStorage.get(KEY + i);
        }
    }
}