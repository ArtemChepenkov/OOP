package ru.nsu.chepenkov.hashtable;

/**
 * Это класс для описания записи.
 *
 * @author ArtemChepenkov
 */
public class Entry<K, V> {
    K key;
    V value;

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
}
