package com.dengzi.collection.list;

import com.dengzi.collection.DzIterator;

import java.util.Collection;

/**
 * @author Djk
 * @Title: 自己实现的 LinkedList
 * @Time: 2017/12/11.
 * @Version:1.0.0
 */
public class DZLinkedList<E> implements DzList<E> {
    private transient int size;
    private transient Node<E> first;
    private transient Node<E> last;

    /**
     * 在首位添加
     *
     * @param e
     */
    private void linkFirst(E e) {
        final Node<E> f = first;
        Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    /**
     * 在末尾添加
     *
     * @param e
     */
    private void linkLast(E e) {
        final Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * 在succ之前添加
     *
     * @param e
     * @param succ
     */
    private void linkBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prev;
        Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }

    /**
     * 删除首位
     *
     * @param f
     * @return
     */
    private E unlinkFirst(Node<E> f) {
        final E item = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        return item;
    }

    /**
     * 删除末尾
     *
     * @param l
     * @return
     */
    private E unlinkLast(Node<E> l) {
        final E item = l.item;
        final Node<E> prev = l.prev;
        last = prev;
        l.prev = null;
        l.item = null;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return item;
    }

    /**
     * 删除一个数据
     *
     * @param x
     * @return
     */
    private E unlink(Node<E> x) {
        final E item = x.item;
        final Node<E> prev = x.prev;
        final Node<E> next = x.next;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        return item;
    }

    private Node<E> node(int index) {
        if (index < size >> 1) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return node(index).item;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> oldNode = node(index);
        oldNode.item = element;
        return oldNode.item;
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        Node<E> indexItem = node(index);
        linkBefore(element, indexItem);
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Node<E> indexItem = node(index);
        return unlink(indexItem);
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
        Node<E> f = first;
        for (int i = 0; i < size; i++) {
            if (f.item.equals(o)) {
                return true;
            }
            f = f.next;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public DzIterator<E> iterator() {
        return new DzLinkedIterator();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> x = first;
        for (int i = 0; i < size; i++) {
            if (x.item.equals(o)) {
                return true;
            }
            x = x.next;
        }
        if (x != null) {
            unlink(x);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.prev = null;
            x.next = null;
            x.next = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[size];
        Node<E> f = first;
        for (int i = 0; i < size; i++) {
            objects[i] = f.item;
            f = f.next;
        }
        return objects;
    }

    /**
     * 获取First数据
     *
     * @return
     */
    public E peek() {
        final Node<E> f = first;
        return f == null ? null : f.item;
    }

    /**
     * 获取First数据,并删除
     *
     * @return
     */
    public E poll() {
        final Node<E> f = first;
        return f == null ? null : unlinkFirst(f);
    }

    private static class Node<E> {
        Node<E> prev;
        E item;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new NullPointerException("out of index size !");
        }
    }

    private class DzLinkedIterator implements DzIterator {
        private int nextIndex = 0;
        private Node<E> next;
        private Node<E> lastReturned = null;


        public DzLinkedIterator() {
            next = first;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public Object next() {
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        @Override
        public void remove() {
            unlink(lastReturned);
            nextIndex--;
            lastReturned = null;
        }
    }
}
