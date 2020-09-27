package wiley.cache;

import wiley.stored.Stored;

public interface Cache {
    void add(String key, Stored value);

    Stored get(String key);

    void delete(String key);

    boolean contains(String key);
}
