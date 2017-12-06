package com.dengzi.collection;

import java.util.Collection;

/**
 * @author Djk
 * @Title: 集合总接口
 * @Time: 2017/12/6.
 * @Version:1.0.0
 */
public interface DzCollection<E> {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    boolean add(E e);

    boolean addAll(Collection<? extends E> c);

    DzIterator<E> iterator();

    boolean removeAll(Collection<?> c);

    boolean remove(Object o);

    void clear();
}
