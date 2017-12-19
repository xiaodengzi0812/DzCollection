package com.dengzi.collection.set;

import com.dengzi.collection.DzCollection;

/**
 * @author Djk
 * @Title: Set总接口
 * @Time: 2017/12/11.
 * @Version:1.0.0
 */
public interface DzSet<E> extends DzCollection<E> {
    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);
}
