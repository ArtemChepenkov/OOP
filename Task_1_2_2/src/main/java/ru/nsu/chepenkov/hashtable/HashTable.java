package ru.nsu.chepenkov.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс для хэш таблицы, внутри ещё итератор.
 *
 * @author ArtemChepenkovz
 */
public class HashTable<K, V> implements Iterable<Entry<K, V>> {

    private ArrayList<Entry<K, V>>[] hashTable;
    private int capacity;
    private int size;
    private int changesAmount;
    private final double NEEDRESIZE = 0.75d;

    @SuppressWarnings("unchecked")
    HashTable() {
        this.capacity = 8;
        this.hashTable = new ArrayList[capacity];
        this.size = 0;
        this.changesAmount = 0;
    }

    /**Функция хэша.*/
    private int hash(K key) {
        return Objects.hashCode(key) % hashTable.length;
    }

    /**Функция ресайза, вызывается, когда не хватает размера.*/
    private void resize() {
        capacity *= 2;
        @SuppressWarnings("unchecked")
        ArrayList<Entry<K, V>>[] newTable = new ArrayList[capacity];
        for (ArrayList<Entry<K, V>> entries : hashTable) {
            if (entries != null) {
                for (Entry<K, V> entry : entries) {
                    int newIndex = hash(entry.getKey());
                    if (newTable[newIndex] == null) {
                        newTable[newIndex] = new ArrayList<>();
                    }
                    newTable[newIndex].add(entry);
                }
            }
        }
        hashTable = newTable;
    }

    /**Вставка по ключу, она же и update.*/
    public void put(K key, V value) {
        if ((double) size / capacity >= NEEDRESIZE) {
            resize();
        }
        int index = hash(key);
        if (hashTable[index] == null) {
            hashTable[index] = new ArrayList<>();
            hashTable[index].add(new Entry<>(key, value));
            size++;
            changesAmount++;
            return;
        }
        hashTable[index].stream()
                .filter(entry -> entry.getKey().equals(key))
                .findFirst()
                .ifPresent(entry -> entry.setValue(value));
    }

    /**Получение значения по ключу.*/
    public V get(K key) {
        int index = hash(key);
        if (hashTable[index] != null) {
            for (Entry<K, V> entry : hashTable[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    /**Удаление по ключу.*/
    public void remove(K key) {
        int index = hash(key);
        if (hashTable[index] != null) {
            Iterator<Entry<K, V>> iterator = hashTable[index].iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (entry.getKey().equals(key)) {
                    iterator.remove();
                    size--;
                    changesAmount++;
                    return;
                }
            }
        }
    }

    /**Тут просто put используется.*/
    public void update(K key, V value) throws NoSuchElementException {
        if (!containsKey(key)) {
            throw new NoSuchElementException("No key");
        }
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
                ArrayList<Entry<K, V>> curEntry = hashTable[index];
                if (curEntry != null) {
                    currentIterator = curEntry.iterator();
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

    public boolean containsKey(K key) {
        return get(key) != null;
    }
}