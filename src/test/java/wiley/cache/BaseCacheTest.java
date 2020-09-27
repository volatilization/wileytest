package wiley.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wiley.stored.Stored;
import wiley.stored.StoredObject;

import static org.junit.Assert.*;

public class BaseCacheTest {
    public BaseCache cache;
    protected static final String KEY = "key";
    protected static final Stored STORED = new StoredObject("name", 0);

    @Before
    public void setUp() {
        cache = new BaseCache();
    }

    @After
    public void tearDown() {
        cache = null;
    }

    @Test
    public void getCacheMap() {
        assertNotNull(cache.getCacheMap());
    }

    @Test
    public void add() {
        cache.add(KEY, STORED);

        assertEquals(cache.get(KEY), STORED);
        assertTrue(cache.getCacheMap().size() > 0);
    }

    @Test
    public void get() {
        cache.add(KEY, STORED);

        Stored result = cache.get(KEY);
        assertEquals(result, STORED);
    }

    @Test
    public void delete() {
        cache.add(KEY, STORED);
        int cacheSizeBeforeDeleting = cache.getCacheMap().size();

        cache.delete(KEY);
        assertFalse(cache.contains(KEY));
        assertEquals(cache.getCacheMap().size(), (cacheSizeBeforeDeleting - 1));
    }

    @Test
    public void contains() {
        cache.add(KEY, STORED);

        assertTrue(cache.contains(KEY));
    }
}