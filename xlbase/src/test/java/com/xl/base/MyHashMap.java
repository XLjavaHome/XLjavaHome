package com.xl.base;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-08-15
 * @time 21:35
 * To change this template use File | Settings | File Templates.
 */
public class MyHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {
    @Test
    void testMap() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, "测试");
        System.out.println(myHashMap);
    }
    
    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }
    
    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
    
    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        MyHashMap.Node<K, V> next;
        
        Node(int hash, K key, V value, MyHashMap.Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        public final K getKey() {
            return key;
        }
        
        public final V getValue() {
            return value;
        }
        
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
        
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
        
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue())) {
                    return true;
                }
            }
            return false;
        }
        
        public final String toString() {
            return key + "=" + value;
        }
    }
}
