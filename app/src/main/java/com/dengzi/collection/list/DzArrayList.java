package com.dengzi.collection.list;

import com.dengzi.collection.DzIterator;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Djk
 * @Title: 自己实现的 ArrayList
 * @Time: 2017/12/6.
 * @Version:1.0.0
 */
public class DzArrayList<E> implements DzList<E> {
    private static final int DEFAULT_CAPACITY = 10;// 默认增长因子，第一次的时候增长10个
    private static final Object[] EMPTY_ELEMENTDATA = {};// 空数组
    transient Object[] elementData;// 存放数据的数组
    private int size = 0;// 当前大小

    public DzArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldE = (E) elementData[index];
        // 插入数据
        elementData[index] = element;
        return oldE;
    }

    @Override
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        ensureCapacityInternal(size + 1);
        // 将index后面的数据后移
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        // 插入数据
        elementData[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E oldE = (E) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return oldE;
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
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int newNum = a.length;
        ensureCapacityInternal(size + newNum);
        System.arraycopy(a, 0, elementData, size, newNum);
        size += newNum;
        return true;
    }

    @Override
    public DzIterator<E> iterator() {
        return new DzArrayIterator();
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] newObj = new Object[size];
        System.arraycopy(elementData, 0, newObj, 0, size);
        return newObj;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new NullPointerException("out of index size !");
        }
    }

    /**
     * 保证容量
     *
     * @param minCapacity
     */
    private void ensureCapacityInternal(int minCapacity) {
        // 当前数组如果为null
        if (elementData == EMPTY_ELEMENTDATA) {
            // 获取要创建的最大值
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        // 扩容
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        // 要扩容大小大于现在的大小才去扩容
        if (minCapacity > elementData.length) {
            // 原来数组的长度
            int oldCapacity = elementData.length;
            // 新的长度，我们给他扩容1.5倍
            int newCapactity = oldCapacity + (oldCapacity >> 1);
            // 如果要扩容的大小是不是比我们的1.5位还大，则用要要扩容的大小
            if (newCapactity < minCapacity) {
                newCapactity = minCapacity;
            }
            // 数组拷贝
            elementData = Arrays.copyOf(elementData, newCapactity);
        }
    }

    /**
     * 自己实现的迭代器
     */
    private class DzArrayIterator implements DzIterator {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            currentIndex++;
            return currentIndex < size;
        }

        @Override
        public Object next() {
            return elementData[currentIndex];
        }

        @Override
        public void remove() {
            DzArrayList.this.remove(currentIndex);
            currentIndex--;
        }
    }

}
