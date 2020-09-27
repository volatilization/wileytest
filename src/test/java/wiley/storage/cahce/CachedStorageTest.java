package wiley.storage.cahce;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wiley.cache.BaseCache;
import wiley.storage.ListStorage;
import wiley.storage.Storage;
import wiley.stored.Stored;
import wiley.stored.StoredObject;

import static org.junit.Assert.*;

public class CachedStorageTest {
    private CachedStorage cachedStorage;
    private static Storage storage;
    private static BaseCache cache;
    public static final String KEY = "key";
    private static final Stored STORED = new StoredObject(KEY, 0);

    @Before
    public void setUp() {
        storage = new ListStorage();
        cache = new BaseCache();
        cachedStorage = new CachedStorage(storage, cache);
    }

    @After
    public void tearDown() {
        storage = null;
        cache = null;
        cachedStorage = null;
    }

    @Test
    public void getCache() {
        assertEquals(cachedStorage.getCache(), cache);
    }

    @Test
    public void getStorage() {
        assertEquals(cachedStorage.getStorage(), storage);
    }

    @Test
    public void add() {
        cachedStorage.add(STORED);
        assertEquals(cachedStorage.getStorage().get(KEY), STORED);
    }

    @Test
    public void get() {
        cachedStorage.add(STORED);
        assertEquals(cachedStorage.get(KEY), STORED);
    }
}