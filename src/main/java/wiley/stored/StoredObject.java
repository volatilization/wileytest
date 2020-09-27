package wiley.stored;

public class StoredObject implements Stored {
    private String name;
    private int digit;

    public StoredObject(String name, int digit) {
        this.name = name;
        this.digit = digit;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }
}
