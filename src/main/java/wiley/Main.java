package wiley;

import wiley.storage.ListStorage;
import wiley.storage.cahce.CachedStorage;
import wiley.storage.cahce.type.CacheType;
import wiley.stored.StoredObject;

public class Main {
    public static void main(String[] args) {
        CachedStorage storage = new CachedStorage(new ListStorage(), CacheType.LRU, 3);
        storage.add(new StoredObject("a", 1));
        storage.add(new StoredObject("b", 1));
        storage.add(new StoredObject("c", 1));
        storage.add(new StoredObject("d", 1));
        storage.add(new StoredObject("e", 1));
        storage.add(new StoredObject("f", 1));
        storage.add(new StoredObject("g", 1));
        storage.add(new StoredObject("h", 1));
        for (int i = 0; i < 1; i++) {
            System.out.println(storage.get("a").getName());
            System.out.println(storage.get("a").getName());
            System.out.println(storage.get("a").getName());
            System.out.println(storage.get("a").getName());
            System.out.println(storage.get("b").getName());
            System.out.println(storage.get("b").getName());
            System.out.println(storage.get("b").getName());
            System.out.println(storage.get("c").getName());
            System.out.println(storage.get("d").getName());
            System.out.println(storage.get("c").getName());

            System.out.println(storage.get("a").getName());
            System.out.println(storage.get("a").getName());
            System.out.println(storage.get("a").getName());
            System.out.println(storage.get("a").getName());
        }
    }
}

