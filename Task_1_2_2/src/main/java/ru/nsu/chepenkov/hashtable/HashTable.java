package ru.nsu.chepenkov.hashtable;

import java.util.*;

public class HashTable<K, V> implements Iterable<Entry<K, V>> {


    private ArrayList<Entry<K, V>>[] hashTable;
    private int capacity;
    private int size;
    private int changesAmount;

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.capacity = 2;
        this.hashTable = new ArrayList[capacity];
        this.size = 0;
        this.changesAmount = 0;
    }

    private int hash(K key) {
        return (Objects.hashCode(key) % hashTable.length);
    }

    private void resize() {
        capacity *= 2;
        @SuppressWarnings("unchecked")
        ArrayList<Entry<K, V>>[] newTable = new ArrayList[capacity];
        for (List<Entry<K, V>> entries : hashTable) {
            if (entries != null) {
                for (Entry<K, V> entry : entries) {
                    int newIndex = hash(entry.key);
                    if (newTable[newIndex] == null) {
                        newTable[newIndex] = new ArrayList<>();
                    }
                    newTable[newIndex].add(entry);
                }
            }
        }
        hashTable = newTable;
    }

    public void put(K key, V value) {
        double needResize = 0.75d;
        if ((double) size / capacity >= needResize) {
            resize();
        }
        int index = hash(key);
        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
        }
        for (Entry<K, V> entry : hashTable[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        hashTable[index].add(new Entry<>(key, value));
        size++;
        changesAmount++;
    }


    public V get(K key) {
        int index = hash(key);
        if (hashTable[index] != null) {
            for (Entry<K, V> entry : hashTable[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        if (hashTable[index] != null) {
            Iterator<Entry<K, V>> iterator = hashTable[index].iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (entry.key.equals(key)) {
                    iterator.remove();
                    size--;
                    changesAmount++;
                    return;
                }
            }
        }
    }

    public void update(K key, V value) {
        put(key, value);
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<Entry<K, V>> {
        private int index = 0;
        private Iterator<Entry<K, V>> currentIterator = null;
        private final int expectedChangesAmount = changesAmount;

        @Override
        public boolean hasNext() {
            while ((currentIterator == null || !currentIterator.hasNext()) && index < capacity) {
                if (hashTable[index] != null) {
                    currentIterator = hashTable[index].iterator();
                }
                index++;
            }
            return currentIterator != null && currentIterator.hasNext();
        }

        @Override
        public Entry<K, V> next() {
            if (changesAmount != expectedChangesAmount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return currentIterator.next();
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HashTable)) {
            return false;
        }
        HashTable<K, V> test = (HashTable<K, V>) obj;
        if (size != test.size) {
            return false;
        }
        for (ArrayList<Entry<K, V>> list : hashTable) {
            if (list == null) {
                continue;
            }
            for (Entry<K, V> entry : list) {
                V testValue = test.get(entry.getKey());
                if (!Objects.equals(entry.getValue(), testValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(hashTable), capacity, size, changesAmount);
    }
}