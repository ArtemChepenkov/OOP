package ru.nsu.chepenkov.hashtable;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс для тестирования хэш таблицы.
 *
 * @author ArtemChepenkov
 */

public class HashTableTest {

    @Test
    @DisplayName("HashTableTestOneElementTest")
    void hashTableTestOneElementTest() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("one", 1);
        assertEquals(1, hashTable.get("one"));
    }

    @Test
    @DisplayName("HashTableTestEqualKeys")
    void hashTableTestEqualKeys() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("one", 1);
        hashTable.put("one", 1);
        hashTable.put("one", 1);
        hashTable.put("one", 1);
        hashTable.put("one", 1);

        assertEquals(1, hashTable.get("one"));
        assertEquals(1, hashTable.size());
    }

    @Test
    @DisplayName("HashTableTestRemove")
    void hashTableTestRemove() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);
        hashTable.remove("two");
        hashTable.remove("three");

        assertNull(hashTable.get("two"));
        assertEquals(1, hashTable.size());
    }

    @Test
    @DisplayName("HashTableTestUpdate")
    void hashTableTestUpdate() {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("one", 1);
        hashTable.update("one", 2);
        assertEquals(2, hashTable.get("one"));
    }

    @Test
    @DisplayName("HashTableTestBigSize")
    void hashTableTestBigSize() {
        HashTable<Number, Number> hashTable = new HashTable<>();
        for (int i = 0; i < 100000; i++) {
            hashTable.put(i, i);
        }
        assertEquals(55555, hashTable.get(55555));
    }

    @Test
    @DisplayName("HashTableTestEquals")
    void hashTableTestEquals() {
        HashTable<Number, Number> hashTable1 = new HashTable<>();
        hashTable1.put(1, 1);
        hashTable1.put(2, 2);
        hashTable1.put(3, 3);
        HashTable<Number, Number> hashTable2 = new HashTable<>();
        hashTable2.put(1, 1);
        hashTable2.put(2, 2);
        assertNotEquals(hashTable1, hashTable2);
        hashTable1.remove(3);
        assertEquals(hashTable1, hashTable2);
    }

    @Test
    @DisplayName("HashTableTestIterator")
    void hashTableTestIterator() {
        HashTable<Number, Number> hashTable = new HashTable<>();
        hashTable.put(1, 1);
        hashTable.put(10, 10);
        hashTable.put(100, 100);
        for (Entry<Number, Number> entry : hashTable) {
            assertEquals(hashTable.get(entry.getKey()),
                    entry.getValue());
        }
    }

    @Test
    @DisplayName("HashTableTestConcurrentModificationException")
    void hashTableTestConcurrentModificationException() {
        HashTable<Number, Number> hashTable = new HashTable<>();
        hashTable.put(1, 1);
        hashTable.put(10, 10);
        hashTable.put(100, 100);
        try {
            for (Entry<Number, Number> entry : hashTable) {
                hashTable.remove(10);
            }
        } catch (ConcurrentModificationException exception) {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    @Test
    @DisplayName("HashTableTestUpdateNoSuchElement")
    void hashTableTestUpdateNoSuchElement() {
        HashTable<Number, Number> hashTable = new HashTable<>();
        hashTable.put(1, 1);
        hashTable.put(10, 10);
        hashTable.put(100, 100);
        try {
            hashTable.update(111,111);
        } catch (NoSuchElementException exception) {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
}
