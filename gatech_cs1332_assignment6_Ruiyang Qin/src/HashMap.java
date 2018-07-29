import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author Ruiyang Qin
 * @userid rqin37
 * @GTID 903146737
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        size = 0;
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("the key or value is null");
        }
        /*
        check if the hashMap needs to be resized
         */
        if (((double) size + 1) / (double) table.length >= MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }

        int mod = Math.abs(key.hashCode() % table.length); // Gain the position
        MapEntry<K, V> newEntry
                = new MapEntry<K, V>(key, value, null); // Make a new MapEntry

        /*
        Linear Probing
        if (table[mod] == null) {
            table[mod] = newEntry;
            size++;
            return null;
        }
        int j = 0;//Qua
        int deleteIndex = -1;
        int i = mod;
        while (table[i] != null) {
            if (table[i].getKey().equals(key)){
                V oldValue = table[i].getValue()  ;
                table[i].setValue(value);
                return oldValue;
            }
            if (deleteIndex != -1 && table[i].isRemoved) {
                deleteIndex = i;
            }
            i = (i + Math.pow(j, 2)) % table.length; // Qua
            j++;// Qua
            i = (i + 1) % table.length;
        }
        if (deleteIndex != -1) {
            table[deleteIndex] = newEntry;
        } else {
            table[i] = newEntry;
        }
        size++;
        return null;
         */

        /*
        the part of add
         */
        if (table[mod] == null) { // if the list position is empty
            table[mod] = newEntry;
            size++;
            return null;
        } else {

            MapEntry<K, V> node = table[mod];
            /*
            check if there exists a duplication
             */
            while (node != null) {
                if (node.getKey().equals(key)) {
                    V returnValue = node.getValue();
                    node.setValue(value);
                    return returnValue;
                }
                node = node.getNext();
            }
            newEntry.setNext(table[mod]);
            table[mod] = newEntry;
            size++;
            return null;
        }

    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("the key is null");
        }
        int mod = Math.abs(key.hashCode()
                % table.length); // find the position
        MapEntry<K, V> node
                = table[mod]; // get the head of one of the linked list
        if (node == null) {
            throw new java.util.NoSuchElementException("the key doesn't exist");
        } else {
            if (node.getKey().equals(key)) {
                V returnValue = node.getValue();
                table[mod] = table[mod].getNext();
                size--;
                return returnValue;
            } else {
                while (node != null) {
                    if (node.getNext().getKey().equals(key)) {
                        V value = node.getNext().getValue();
                        node.setNext(node.getNext().getNext());
                        size--;
                        return value;
                    } else {
                        node = node.getNext();
                    }
                }
                throw new java.util.NoSuchElementException("the key doesn't exist");
            }
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("the key is null");
        }
        int mod = Math.abs(key.hashCode() % table.length);
        if (table[mod] == null) {
            throw new java.util.NoSuchElementException("the key doesn't exist");
        } else {
            MapEntry<K, V> node = table[mod];
            while (node != null) {
                if (node.getKey().equals(key)) {
                    return node.getValue();
                }
                node = node.getNext();
            }
            throw new java.util.NoSuchElementException("the key doesn't exist");
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("the key is null");
        }
        int mod = Math.abs(key.hashCode() % table.length);
        if (table[mod] == null) {
            return false;
        } else {
            MapEntry<K, V> node = table[mod];
            while (node != null) {
                if (node.getKey().equals(key)) {
                    return true;
                }
                node = node.getNext();
            }
            return false;
        }
    }

    @Override
    public void clear() {
        size = 0;
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keyFromTable = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> node = table[i];
                while (node != null) {
                    keyFromTable.add(node.getKey());
                    node = node.getNext();
                }
            }
        }
        return keyFromTable;
    }

    @Override
    public List<V> values() {
        List<V> valueFromTable = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> node = table[i];
                while (node != null) {
                    valueFromTable.add(node.getValue());
                    node = node.getNext();
                }
            }
        }
        return valueFromTable;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0 || length < size) {
            throw new IllegalArgumentException(
                    "length is non-positive or less"
                            + "than the number of items in the hash map.");
        }
        MapEntry<K, V>[] newTable = (MapEntry<K, V>[]) new MapEntry[length];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> nodeInOldTable = table[i]; //head node
                while (nodeInOldTable != null) {
                    int mod = Math.abs(
                            nodeInOldTable.getKey().hashCode()
                                    % newTable.length);
                    MapEntry<K, V> newEntry
                            = new MapEntry<K, V>(nodeInOldTable.getKey(),
                            nodeInOldTable.getValue(), null);
                    if (newTable[mod] == null) {
                        newTable[mod] = newEntry;
                    } else {
                        newEntry.setNext(newTable[mod]);
                        newTable[mod] = newEntry;
                    }
                    nodeInOldTable = nodeInOldTable.getNext();
                }
            }
        }
        table = newTable;
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
