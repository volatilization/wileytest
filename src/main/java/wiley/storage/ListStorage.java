package wiley.storage;

import wiley.stored.Stored;

import java.util.LinkedList;
import java.util.List;

public class ListStorage implements Storage {
    private final List<Stored> storage;

    public ListStorage() {
        this(new LinkedList<>());
    }

    public ListStorage(List<Stored> storage) {
        this.storage = storage;
    }

    public void add(Stored stored) {
        storage.add(stored);
    }

    public Stored get(String name) {
        for (Stored s : storage) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
}
