package com.dengzi.collection.list;

import com.dengzi.collection.DzIterator;

import java.util.Collection;

/**
 * @author Djk
 * @Title:
 * @Time: 2017/12/6.
 * @Version:1.0.0
 */
public class DzArrayList<E> implements DzList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    transient Object[] elementData;
    private int size;

    public DzArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    @Override
    public E get(int index) {
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        Object[] oldElement = elementData;
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
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
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public DzIterator<E> iterator() {
        return null;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public void clear() {

    }

    /**
     * 保证容量
     *
     * @param minCapacity
     */
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
    }
}
