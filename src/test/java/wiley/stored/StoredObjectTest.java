package wiley.stored;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StoredObjectTest {
    private StoredObject storedObject;
    private final String NAME = "name";
    private final int DIGIT = 0;

    @Before
    public void setUp() throws Exception {
        storedObject = new StoredObject(NAME, DIGIT);
    }

    @After
    public void tearDown() throws Exception {
        storedObject = null;
    }

    @Test
    public void getName() {
        String result = storedObject.getName();
        assertEquals(result, NAME);
    }

    @Test
    public void setName() {
        storedObject.setName(NAME);
        assertEquals(storedObject.getName(), NAME);
    }

    @Test
    public void getDigit() {
        int result = storedObject.getDigit();
        assertEquals(result, DIGIT);
    }

    @Test
    public void setDigit() {
        storedObject.setDigit(DIGIT);
        assertEquals(storedObject.getDigit(), DIGIT);
    }
}