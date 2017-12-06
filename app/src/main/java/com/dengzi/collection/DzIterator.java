package com.dengzi.collection;

/**
 * @author Djk
 * @Title: 迭代器接口
 * @Time: 2017/12/6.
 * @Version:1.0.0
 */
public interface DzIterator<E> {

    boolean hasNext();

    E next();

    void remove();
}
