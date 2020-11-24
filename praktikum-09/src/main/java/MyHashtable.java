import java.util.*;
import java.util.stream.Collectors;

public class MyHashtable<K, V> implements Map<K, V> {
    private K[] keys;
    private V[] values;

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return h % keys.length;
    }

    public MyHashtable() {
        new MyHashtable<K, V>(10);
    }

    public MyHashtable(int size) {
        keys = (K[]) new Object[size];
        values = (V[]) new Object[size];

    }

    //  Removes all mappings from this map (optional operation).
    public void clear() {
        Arrays.stream(keys).forEach(e -> e = null);
        Arrays.stream(values).forEach(e -> e = null);
    }

    //  Associates the specified value with the specified key in this map (optional operation).
    public V put(K key, V value) {
        int h = hash(key);
        if (keys[h] == null) {
            keys[h] = key;
        } else {
            int collisionNum = 0;
            while (keys[h] != null && !keys[h].equals(value)) {
                h += 2 * (++collisionNum) - 1;
                h = h % keys.length;
            }
        }
        values[h] = value;
        return value;
    }

    //  Returns the value to which this map maps the specified key.
    public V get(Object key) {
        int h = hash(key);
        return values[h];
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        return size() == 0;
    }

    //  Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        int h = hash(key);
        V res = get(key);
        values[h] = null;
        return res;
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        return (int) Arrays.stream(values).filter(Objects::nonNull).count();
    }

    // =======================================================================
    //  Returns a set view of the keys contained in this map.
    public Set keySet() {
        return Arrays.stream(keys).collect(Collectors.toSet());
    }

    //  Copies all of the mappings from the specified map to this map (optional operation).
    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    //  Returns a collection view of the values contained in this map.
    public Collection values() {
        throw new UnsupportedOperationException();
    }

    //  Returns true if this map contains a mapping for the specified key.
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }

    //  Returns true if this map maps one or more keys to the specified value.
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    //  Returns a set view of the mappings contained in this map.
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }

    //  Compares the specified object with this map for equality.
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }

    //  Returns the hash code value for this map.
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}
