package wiley.storage;

import wiley.stored.Stored;

public interface Storage {
    void add(Stored stored);

    Stored get(String name);
}
