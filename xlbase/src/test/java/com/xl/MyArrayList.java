package com.xl;

import java.util.*;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-10-24
 * @time 9:28
 * To change this template use File | Settings | File Templates.
 */
public class MyArrayList<E> implements List<E> {
    /**
     * List集合大小
     */
    private int size;
    /**
     * 数组的最大长度
     */
    private int arrayLength;
    /**
     * 集合的具体值
     */
    private E[] objects;
    
    public MyArrayList() {
        size = 0;
        arrayLength = 10;
        objects = (E[]) new Object[arrayLength];
    }
    
    @Test
    void name() {
        List<String> list = new MyArrayList<>();
        list.add("测试");
        list.add("你会");
        list.add("hello");
        System.out.println(list.toArray());
        System.out.println(list.get(1));
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
    public Iterator iterator() {
        return null;
    }
    
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(objects, size);
    }
    
    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
    
    @Override
    public boolean add(E o) {
        objects[size++] = o;
        return true;
    }
    
    @Override
    public boolean remove(Object o) {
        return false;
    }
    
    @Override
    public boolean containsAll(Collection c) {
        return false;
    }
    
    @Override
    public boolean addAll(Collection c) {
        return false;
    }
    
    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }
    
    @Override
    public boolean removeAll(Collection c) {
        return false;
    }
    
    @Override
    public boolean retainAll(Collection c) {
        return false;
    }
    
    @Override
    public void clear() {
    }
    
    @Override
    public E get(int index) {
        return objects[index];
    }
    
    @Override
    public Object set(int index, Object element) {
        return null;
    }
    
    @Override
    public void add(int index, E element) {
        objects[index++] = element;
    }
    
    @Override
    public E remove(int index) {
        return null;
    }
    
    @Override
    public int indexOf(Object o) {
        return 0;
    }
    
    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }
    
    @Override
    public ListIterator listIterator() {
        return null;
    }
    
    @Override
    public ListIterator listIterator(int index) {
        return null;
    }
    
    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }
}
