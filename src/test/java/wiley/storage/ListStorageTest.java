package wiley.storage;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import wiley.stored.Stored;
import wiley.stored.StoredObject;

public class ListStorageTest {
    private ListStorage listStorage;
    private static final Stored STORED = new StoredObject("name", 0);

    @Before
    public void setUp() {
        listStorage = new ListStorage();
    }

    @After
    public void tearDown() {
        listStorage = null;
    }

    @Test
    public void add() {
        listStorage.add(STORED);

        assertEquals(listStorage.get(STORED.getName()), STORED);
    }

    @Test
    public void get() {
        listStorage.add(STORED);

        assertEquals(listStorage.get(STORED.getName()), STORED);
        assertNull(listStorage.get(""));
    }
}