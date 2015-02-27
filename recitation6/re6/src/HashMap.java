import java.util.HashSet;
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
    	MapEntry<K, V> entry = new MapEntry(key, value);
    	int index;
    	if ((key == null) || (value == null)) {
    		throw new IllegalArgumentException();
    	}

        if ((size + 1) >= MAX_LOAD_FACTOR * backing.length) {
            MapEntry<K, V>[] temp = (MapEntry<K, V>[]) new MapEntry[backing.length * 2 + 1];
            size = 0;
            for (int i = 0; i < backing.length; i++) {
                if (backing[i] != null) {
                    addHelper(temp, backing[i].getKey(), backing[i].getValue());
                    MapEntry<K, V> curr = backing[i];
                    while (curr.getNext() != null) {
                        addHelper(temp, curr.getNext().getKey(), curr.getNext().getValue());
                        curr = curr.getNext();
                    }
                }
            }

            backing = temp;
        }

        addHelper(backing, key, value);
    }
    		
    private void addHelper(MapEntry<K, V>[] arr, K key, V value) {
        int index = key.hashCode() % arr.length;
        MapEntry<K, V> curr;
        curr = arr[index];
        if (curr == null) {
            arr[index] = new MapEntry<>(key, value);
        } else {
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(new MapEntry<>(key, value));
        }
        size++;
    }

    	
    @Override
    public Set<MapEntry<K, V>> getEntrySet() {
    	Set<MapEntry<K, V>> set = new HashSet<MapEntry<K, V>>();
    	for (MapEntry<K, V> entry : backing) {
    		if (entry != null) {
    			set.add(entry);
    			MapEntry<K, V> curr = entry;
    			while (curr.getNext() != null) {
    				curr = curr.getNext();
    				set.add(curr);
    			}  			
    		} 		
    	}  		
    	return set;
    }

    @Override
    public MapEntry<K, V>[] getBackingArray() {
    	return backing;
    }

    @Override
    public int size() {
    	return size;
    }
}
