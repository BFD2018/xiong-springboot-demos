package com.xjt.javase.collections.list.customArrayList;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<E>{
    //初始容量
    private static final int DEFAULT_CAPACITY = 10;
    //空数组
    private static final Object[] EMPTY_ELEMENTDATA = {};

    //实际存数据的数组
    transient Object[] elementData;

    //数组中元素个数
    private int size;

    //无参构造
    public MyArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object o){
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o){
        return indexOfRange(o,0,size);
    }

    int indexOfRange(Object o,int start,int end){
        Object[] es = elementData;
        if(o == null){
            for (int i = start; i < end; i++) {
                if(es[i] == null){
                    return i;
                }
            }
        }else{
            for (int i = start; i < end; i++) {
                if(o.equals(es[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    //带容量的构造
    public MyArrayList(int initialCapacity) {
        if(initialCapacity>0){
            this.elementData = new Object[initialCapacity];
        }else if(initialCapacity == 0){
            this.elementData = EMPTY_ELEMENTDATA;
        }else{
            throw new IllegalArgumentException("非法参数："+initialCapacity);
        }
    }

    //带初始集合的构造
    public MyArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if((size = elementData.length) != 0){
            if(elementData.getClass() != Object[].class){
                elementData = Arrays.copyOf(elementData,size,Object[].class);
            }
        }else{
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    //扩容1.5倍
    private void grow(){
        if(elementData == EMPTY_ELEMENTDATA){
            elementData = new Object[DEFAULT_CAPACITY];
        }
        //当前容器内元素个数等于数组长度
        if(size == elementData.length){
            System.out.println("==================>执行一次扩容");
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);

            Object[] obj = new Object[newCapacity];
            //拷贝元素
            System.arraycopy(elementData,0,obj,0,elementData.length);
            //把新数组的地址赋值给elementData
            elementData = obj;
        }
    }

    //toString方法
    public String toString(){
        if(size == 0){
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if(i == size-1){
                sb.append(elementData[i]).append("]");
            }else{
                sb.append(elementData[i]).append(", ");
            }
        }
        return sb.toString();
    }

    public E set(int index,E element){
        checkIndex(index);
        E value = (E) elementData[index];
        elementData[index] = element;
        //返回原来位置的数据
        return value;
    }

    private void checkIndex(int index){
        if(index <0 || index >= size){
            throw new IndexOutOfBoundsException("索引越界了!");
        }
    }

    public E remove(int index){
        checkIndex(index);
        E value = (E) elementData[index];
        //计算出要移动元素的个数
        int numMoved = size - index - 1;
        if(numMoved > 0){
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        //把最后一个位置上的元素置为null
        elementData[--size] = null;
        return value;
    }

    //根据索引获取元素
    public E get(int index){
        checkIndex(index);
        return (E) elementData[index];
    }

    public boolean add(E e){
        grow();
        elementData[size++] = e;
        return true;
    }

}
