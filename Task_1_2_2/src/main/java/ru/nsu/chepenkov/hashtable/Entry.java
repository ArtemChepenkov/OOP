package ru.nsu.chepenkov.hashtable;

/**
 * Это класс для описания записи.
 *
 * @author ArtemChepenkov
 */
public class Entry<K, V> {
    private final K key;
    private V value;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
