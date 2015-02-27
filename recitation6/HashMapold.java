import java.util.Set;

/**
 * Class for implementing your HashMap with external chaining
 *
 * Created by CS1332TAs on 2/11/15.
 */
public class HashMap<K,V> implements HashMapInterface<K,V> {
    private MapEntry<K, V>[] backing;
    private int size;

    public HashMap() {
        this.backing = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public void add(K key, V value) {

    }

    @Override
    public Set<MapEntry<K, V>> getEntrySet() {

    }

    @Override
    public MapEntry<K, V>[] getBackingArray() {

    }

    @Override
    public int size() {

    }
}
