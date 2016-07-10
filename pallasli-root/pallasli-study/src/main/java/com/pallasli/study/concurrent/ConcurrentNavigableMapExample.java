package com.pallasli.study.concurrent;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 并发导航映射 ConcurrentNavigableMap java.util.concurrent.ConcurrentNavigableMap
 * 是一个支持并发访问的 java.util.NavigableMap，它还能让它的子 map 具备并发访问的能力。所谓的 "子 map" 指的是诸如
 * headMap()，subMap()，tailMap() 之类的方法返回的 map。 NavigableMap 中的方法不再赘述，本小节我们来看一下
 * ConcurrentNavigableMap 添加的方法。 headMap() headMap(T toKey) 方法返回一个包含了小于给定 toKey
 * 的 key 的子 map。 如果你对原始 map 里的元素做了改动，这些改动将影响到子 map 中的元素(译者注：map 集合持有的其实只是对象的引用)。
 * 
 * 
 * headMap 将指向一个只含有键 "1" 的 ConcurrentNavigableMap，因为只有这一个键小于
 * "2"。关于这个方法及其重载版本具体是怎么工作的细节请参考 Java 文档。 tailMap() tailMap(T fromKey)
 * 方法返回一个包含了不小于给定 fromKey 的 key 的子 map。 如果你对原始 map 里的元素做了改动，这些改动将影响到子 map
 * 中的元素(译者注：map 集合持有的其实只是对象的引用)。
 * 
 * 
 * tailMap 将拥有键 "2" 和 "3"，因为它们不小于给定键 "2"。关于这个方法及其重载版本具体是怎么工作的细节请参考 Java 文档。
 * subMap() subMap() 方法返回原始 map 中，键介于 from(包含) 和 to (不包含) 之间的子 map。
 * 
 * 返回的 submap 只包含键 "2"，因为只有它满足不小于 "2"，比 "3" 小。 更多方法 ConcurrentNavigableMap
 * 接口还有其他一些方法可供使用，比如： descendingKeySet() descendingMap() navigableKeySet()
 * 
 * @author lyt1987
 *
 */
public class ConcurrentNavigableMapExample {
	public static void main(String[] args) {
		ConcurrentNavigableMap map = new ConcurrentSkipListMap();

		map.put("0", "one");
		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");
		map.put("4", "three");
		map.put("5", "three");
		map.put("6", "three");

		ConcurrentNavigableMap headMap = map.headMap("2");
		System.out.println(headMap);
		ConcurrentNavigableMap tailMap = map.tailMap("2");
		System.out.println(tailMap);
		ConcurrentNavigableMap subMap = map.subMap("2", "5");
		System.out.println(subMap);

	}
}
