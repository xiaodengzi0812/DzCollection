package com.dengzi.collection.list;

import com.dengzi.collection.DzCollection;

/**
 * @author Djk
 * @Title: List总接口
 * @Time: 2017/12/6.
 * @Version:1.0.0
 */
public interface DzList<E> extends DzCollection<E> {
    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);
}
