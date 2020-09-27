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

public class LFUCachedStorageTest {
    private LFUCachedStorage lfuCachedStorage;
    private Storage storage;
    private Cache cache;
    private final static int CACHE_SIZE = 10;
    private final static String KEY = "key";
    private final static Stored STORED = new StoredObject(KEY, 0);

    @Before
    public void setUp() {
        storage = new ListStorage();
        cache = new SizedCache(CACHE_SIZE);
        lfuCachedStorage = new LFUCachedStorage(storage, cache);
    }

    @After
    public void tearDown() {
        storage = null;
        cache = null;
        lfuCachedStorage = null;
    }

    @Test
    public void getCache() {
        assertEquals(lfuCachedStorage.getCache().getClass(), SizedCache.class);
    }

    @Test
    public void getCacheQueue() {
        assertNotNull(lfuCachedStorage.getCacheQueue());
    }

    @Test
    public void getFromCache() {
        cache.add(KEY, STORED);

        assertEquals(lfuCachedStorage.get(KEY), STORED);
        assertNull(storage.get(KEY));
    }

    @Test
    public void getFromStorageAndAddToCache() {
        storage.add(STORED);

        assertFalse(cache.contains(KEY));
        assertEquals(lfuCachedStorage.get(KEY), STORED);
        assertTrue(cache.contains(KEY));
    }

    @Test
    public void getFromStorageAndUpdate() {
        storage.add(STORED);

        lfuCachedStorage.get(KEY);
        assertTrue(lfuCachedStorage.getCache().contains(KEY));
        fillCacheAndStorage();
        assertFalse(lfuCachedStorage.getCache().contains(KEY));
        lfuCachedStorage.get(KEY);
        assertTrue(lfuCachedStorage.getCache().contains(KEY));
    }

    private void fillCacheAndStorage() {
        for (int i = 0; i < CACHE_SIZE; i++) {
            Stored stored = new StoredObject(KEY+i, i);
            storage.add(stored);
            lfuCachedStorage.get(KEY+i);
            lfuCachedStorage.get(KEY+i);
        }
    }
}