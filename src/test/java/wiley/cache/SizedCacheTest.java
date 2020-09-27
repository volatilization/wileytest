package wiley.cache;

import org.junit.Before;
import org.junit.Test;
import wiley.stored.StoredObject;

import static org.junit.Assert.*;

public class SizedCacheTest extends BaseCacheTest {
    private final int CACHE_SIZE = 10;

    @Before
    public void setUp() {
        cache = new SizedCache(CACHE_SIZE);
    }

    @Test
    public void add() {
        super.add();
        fillCache();

        assertTrue(cache.contains("key1"));
        assertEquals(cache.getCacheMap().size(), CACHE_SIZE);
        assertFalse(cache.contains("key"+CACHE_SIZE));
    }

    @Test
    public void isFull() {
        assertFalse(((SizedCache) cache).isFull());
        fillCache();
        assertTrue(((SizedCache) cache).isFull());
        assertEquals(cache.getCacheMap().size(), CACHE_SIZE);
    }

    private void fillCache() {
        for (int i = 0; i < CACHE_SIZE; i++) {
            cache.add("key"+i, new StoredObject("key"+i, i));
        }
    }
}