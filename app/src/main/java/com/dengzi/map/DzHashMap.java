package com.dengzi.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Djk
 * @Title: 手写hashMap（散列链表）
 * @Time: 2017/12/13.
 * @Version:1.0.0
 */
public class DzHashMap<K, V> implements DzMap<K, V> {
    // 默认初始化大小
    static final int DEFAULT_INITIAL_CAPACITY = 4;
    // 最大的大小
    static final int MAXIMUM_CAPACITY = 1 << 30;
    // 增长因子，超过当前数组大小的0.75就开始增长
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 空数组
    static final DzHashMapEntry<?, ?>[] EMPTY_TABLE = {};
    // 一维数组，存放数据
    transient DzHashMapEntry<K, V>[] table = (DzHashMapEntry<K, V>[]) EMPTY_TABLE;
    // 当前大小
    transient int size = 0;
    // 当前数组长度
    int threshold;
    // 当前的增长因子
    final float loadFactor;

    /**
     * 构造 初始化两个参数
     */
    public DzHashMap() {
        // 初始数据 = 4
        threshold = DEFAULT_INITIAL_CAPACITY;
        // 增长因子 = 0.75
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    /**
     * 获取hash值
     *
     * @param var0
     * @return
     */
    static final int hash(Object var0) {
        int var1;
        return var0 == null ? 0 : (var1 = var0.hashCode()) ^ var1 >>> 16;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        // 如果数组为null,则初始化数组
        if (table == EMPTY_TABLE) {
            initTable();
        }
        // 如果当前size > 数组长度*增长因子
        if (size >= threshold * loadFactor) {
            // 双倍扩容
            doubleResize();
        }
        // 获取key的hash值
        int hash = hash(key);
        // 计算当前hash值在数组中的下标
        int index = indexFor(hash, table.length);
        // 判断key是否重复,如果重复就覆盖value
        for (DzHashMapEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.hash == hash && (key == entry.key || key.equals(entry.key))) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        // 添加新的元素
        addEntry(hash, key, value, index);
        return null;
    }

    /**
     * 添加新的元素
     *
     * @param hash
     * @param key
     * @param value
     * @param index
     */
    protected void addEntry(int hash, K key, V value, int index) {
        DzHashMapEntry newEntry = new DzHashMapEntry(hash, key, value, table[index]);
        table[index] = newEntry;
        size++;
    }

    /**
     * 双倍扩容
     */
    private void doubleResize() {
        // 双倍
        threshold = threshold << 1;
        DzHashMapEntry[] newTable = new DzHashMapEntry[threshold];
        // 将当前的table数组全部赋值到新的数组中
        transfer(newTable);
        // 重新赋值
        table = newTable;
    }

    /**
     * 将当前的table数组全部赋值到新的数组中
     *
     * @param newTable
     */
    protected void transfer(DzHashMapEntry[] newTable) {
        int newCapacity = newTable.length;
        // 遍历数组
        for (DzHashMapEntry<K, V> entry : table) {
            // 遍历链表
            for (; entry != null; entry = entry.next) {
                // 重新计算当前数据在数组中的index位置
                int index = indexFor(entry.hash, newCapacity);
                // 创建一个新的Entry,并将它的next指向原来的index位置
                DzHashMapEntry newEntry = new DzHashMapEntry(entry.hash, entry.key, entry.value, newTable[index]);
                // 将数据的index位置设置为新的Entry(添加的数据永远在链表的第0个位置,并把链表的第0个位置赋值给数组的index位置)
                newTable[index] = newEntry;
            }
        }
    }

    /**
     * 计算当前hash值在数组中的下标
     *
     * @param hash   当前hash值
     * @param length 当前数组的长度
     * @return
     */
    protected int indexFor(int hash, int length) {
        // 当前hash值 & 数组长度-1, 得出在数组的下标位置
        return hash & (length - 1);
    }

    /**
     * 初始化table
     */
    private void initTable() {
        table = new DzHashMapEntry[threshold];
    }

    @Override
    public V get(Object key) {
        DzHashMapEntry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        } else {
            return entry.value;
        }
    }

    /**
     * 获取Entry
     *
     * @param key
     * @return
     */
    protected DzHashMapEntry<K, V> getEntry(Object key) {
        // 通过key获取hash值
        int hash = hash(key);
        // 通过hash值获取在数组中的index下标
        int index = indexFor(hash, table.length);
        // 这里就只需要遍历index下标位置的链表就可以找到对应的数据了
        for (DzHashMapEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            // key相同的判断
            if (entry.hash == hash && (key == entry.key || key.equals(entry.key))) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        for (DzHashMapEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.hash == hash && (key == entry.key || key.equals(entry.key))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (DzHashMapEntry<K, V> entry : table) {
            // 遍历链表
            for (; entry != null; entry = entry.next) {
                if (entry.value == value || entry.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V remove(Object key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        // 前一个Entry
        DzHashMapEntry<K, V> prvEntry = null;
        for (DzHashMapEntry<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.hash == hash && (key == entry.key || key.equals(entry.key))) {
                V value = entry.value;
                removeEntry(entry);
                if (prvEntry == null) {
                    table[index] = entry.next;
                } else {
                    prvEntry.next = entry.next;
                }
                return value;
            }
            prvEntry = entry;
        }
        return null;
    }

    protected void removeEntry(DzHashMapEntry<K, V> entry) {

    }

    @Override
    public Set<K> keySet() {
        Set set = new HashSet();
        for (DzHashMapEntry<K, V> entry : table) {
            // 遍历链表
            for (; entry != null; entry = entry.next) {
                set.add(entry.key);
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection collection = new ArrayList();
        for (DzHashMapEntry<K, V> entry : table) {
            // 遍历链表
            for (; entry != null; entry = entry.next) {
                collection.add(entry.value);
            }
        }
        return collection;
    }

    static class DzHashMapEntry<K, V> {
        final K key;
        V value;
        DzHashMapEntry<K, V> next;
        int hash;

        public DzHashMapEntry(int hash, K key, V value, DzHashMapEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final int hashCode() {
            return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
        }
    }
}
