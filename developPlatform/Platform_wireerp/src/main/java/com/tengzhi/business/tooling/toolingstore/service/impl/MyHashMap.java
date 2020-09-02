package com.tengzhi.business.tooling.toolingstore.service.impl;

import java.util.HashMap;

/**
 * @author 鱼游浅水
 * @create 2020-07-07
 */
public class MyHashMap<K,V> extends HashMap<K,V> {

    K first;
    V second;
    public MyHashMap() {first = null;second = null;}

    @Override
    public V put(K key, V value) {
        first = key;
        second = value;
        return super.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof MyHashMap)){
            return false;
        }
        MyHashMap<K,V> pn = (MyHashMap<K,V>)o;
        return pn.first.equals(first) && pn.second.equals(second);
    }

    @Override
    public int hashCode() {
        return first.hashCode() + second.hashCode();
    }

}
