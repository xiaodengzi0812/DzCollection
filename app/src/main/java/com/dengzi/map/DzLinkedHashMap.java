package com.dengzi.map;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Djk
 * @Title: 手写LinkedHashMap(双向回环散列链表), 这个算法也是lruCache里的关键算法
 * @Time: 2017/12/13.
 * @Version:1.0.0
 */
public class DzLinkedHashMap<K, V> extends DzHashMap<K, V> {
    private transient DzLinkedHashMapEntry<K, V> header;

    public DzLinkedHashMap() {
        super();
        // 初始化header
        header = new DzLinkedHashMapEntry<>(-1, null, null, null);
        // 首尾相连
        header.before = header.after = header;
    }

    /**
     * 将当前的table数组全部赋值到新的数组中
     *
     * @param newTable
     */
    @Override
    protected void transfer(DzHashMapEntry[] newTable) {
        int newCapacity = newTable.length;
        // 获取第一个元素地址
        DzLinkedHashMapEntry<K, V> first = header.after;
        // 将header重置
        header.before = header.after = header;
        // 遍历原来的链表
        for (; first != header; first = first.after) {
            // 计算新的index下标
            int index = indexFor(first.hash, newCapacity);
            // 创建新的Entry
            DzLinkedHashMapEntry newEntry = new DzLinkedHashMapEntry(first.hash, first.key, first.value, newTable[index]);
            // 将数据的index位置设置为新的Entry(添加的数据永远在链表的第0个位置,并把链表的第0个位置赋值给数组的index位置)
            newTable[index] = newEntry;
            // 将此元素永远添加到header的前面(这样操作就是将原来的链表顺序保持,新加进来的永远在header的前面,相当于永远在后面添加)
            addBeforeHeader(newEntry);
        }
    }

    /**
     * 添加到Header的前面
     *
     * @param entry
     */
    private void addBeforeHeader(DzLinkedHashMapEntry<K, V> entry) {
        DzLinkedHashMapEntry last = header.before;
        last.after = entry;
        entry.before = last;
        entry.after = header;
        header.before = entry;
    }

    @Override
    protected void removeEntry(DzHashMapEntry<K, V> removeEntry) {
        DzLinkedHashMapEntry<K, V> entry = (DzLinkedHashMapEntry<K, V>) removeEntry;
        entry.remove();
    }

    @Override
    protected DzHashMapEntry<K, V> getEntry(Object key) {
        DzLinkedHashMapEntry<K, V> entry = (DzLinkedHashMapEntry<K, V>) super.getEntry(key);
        // 这里就是通过查询算法来排序链表的位置,让最近查询的放到最后
        if (entry != null) {
            entry.remove();
            addBeforeHeader(entry);
        }
        return entry;
    }

    /**
     * 添加新的元素
     *
     * @param hash
     * @param key
     * @param value
     * @param index
     */
    @Override
    protected void addEntry(int hash, K key, V value, int index) {
        DzLinkedHashMapEntry newEntry = new DzLinkedHashMapEntry(hash, key, value, table[index]);
        table[index] = newEntry;
        size++;
        // 这里就是插入算法来排序链表的位置,让最近插入的放到最后
        addBeforeHeader(newEntry);
    }

    /**
     * 这个是我随便写的一个,为了方便查看排序算法是否生效
     *
     * @return
     */
    public List<K> keyList() {
        List<K> list = new ArrayList<>();
        // 将链表顺序输出
        for (DzLinkedHashMapEntry<K, V> entry = header.after; entry != header; entry = entry.after) {
            list.add(entry.key);
        }
        return list;
    }

    static class DzLinkedHashMapEntry<K, V> extends DzHashMapEntry<K, V> {
        DzLinkedHashMapEntry<K, V> before, after;

        public DzLinkedHashMapEntry(int hash, K key, V value, DzHashMapEntry<K, V> next) {
            super(hash, key, value, next);
        }

        /**
         * 移除当前元素
         */
        private void remove() {
            before.after = after;
            after.before = before;
        }

    }
}
