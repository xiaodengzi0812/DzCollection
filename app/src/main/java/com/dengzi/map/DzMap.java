package com.dengzi.map;

import java.util.Collection;
import java.util.Set;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/13.
 * @Version:1.0.0
 */
public interface DzMap<K, V> {
    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    V get(Object key);

    V put(K key, V value);

    V remove(Object key);

    Set<K> keySet();

    Collection<V> values();
}
