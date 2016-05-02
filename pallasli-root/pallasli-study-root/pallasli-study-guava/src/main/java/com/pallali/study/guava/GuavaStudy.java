package com.pallali.study.guava;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.primitives.Ints;

public class GuavaStudy {
	public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
			"red", "orange", "yellow", "green", "blue", "purple");

	class Foo {
		Set<Bar> bars;

		Foo(Set<Bar> bars) {
			this.bars = ImmutableSet.copyOf(bars); // defensive copy!
		}
	}

	// 不可变集合可以用如下多种方式创建
	public void createImmutableSet() {
		// builder
		final ImmutableSet<Color> GOOGLE_COLORS = ImmutableSet
				.<Color> builder().add(new Color(0, 191, 255)).build();
		// 对有序不可变集合来说，排序是在构造集合的时候完成的
		ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
		// coptyOf
		ImmutableList<Color> defensiveCopy = ImmutableList
				.copyOf(GOOGLE_COLORS);

		// 可变集合接口 属于JDK还是Guava 不可变版本
		// Collection JDK ImmutableCollection
		// List JDK ImmutableList
		// Set JDK ImmutableSet
		// SortedSet/NavigableSet JDK ImmutableSortedSet
		// Map JDK ImmutableMap
		// SortedMap JDK ImmutableSortedMap
		// Multiset Guava ImmutableMultiset
		// SortedMultiset Guava ImmutableSortedMultiset
		// Multimap Guava ImmutableMultimap
		// ListMultimap Guava ImmutableListMultimap
		// SetMultimap Guava ImmutableSetMultimap
		// BiMap Guava ImmutableBiMap
		// ClassToInstanceMap Guava ImmutableClassToInstanceMap
		// Table Guava ImmutableTable

	}

	public void utils() {

		// 集合接口 属于JDK还是Guava 对应的Guava工具类
		// Collection JDK Collections2：不要和java.util.Collections混淆
		// List JDK Lists
		// Set JDK Sets
		// SortedSet JDK Sets
		// Map JDK Maps
		// SortedMap JDK Maps
		// Queue JDK Queues
		// Multiset Guava Multisets
		// Multimap Guava Multimaps
		// BiMap Guava Maps
		// Table Guava Tables

		// 封装初始化
		List<Color> list = Lists.newArrayList();
		Map<String, Color> map = Maps.newLinkedHashMap();
		Set<Color> copySet = Sets.newHashSet(new Color(0, 191, 255), new Color(
				0, 191, 255), new Color(0, 191, 255));
		List<String> theseElements = Lists.newArrayList("alpha", "beta",
				"gamma");
		// 通过为工厂方法命名（Effective Java第一条），我们可以提高集合初始化大小的可读性：
		//
		// 查看源代码打印帮助
		List<Color> exactly100 = Lists.newArrayListWithCapacity(100);
		List<Color> approx100 = Lists.newArrayListWithExpectedSize(100);
		Set<Color> approx100Set = Sets.newHashSetWithExpectedSize(100);
		// Guava引入的新集合类型没有暴露原始构造器，也没有在工具类中提供初始化方法。
		// 而是直接在集合类中提供了静态工厂方法
		Multiset<String> multiset = HashMultiset.create();
		// Lists
		List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);
		List<Integer> countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}
		List<List<Integer>> parts = Lists.partition(countUp, 2);// {{1,2},

		// Sets
		Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two",
				"three", "six", "seven", "eight");
		Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");
		SetView<String> intersection = Sets.intersection(primes,
				wordsWithPrimeLength);
		// intersection包含"two", "three", "seven"
		intersection.immutableCopy();// 可以使用交集，但不可变拷贝的读取效率更高
										// {3,4}, {5}
		Set<String> animals = ImmutableSet.of("gerbil", "hamster");
		Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");
		Set<List<String>> product = Sets.cartesianProduct(animals, fruits);
		// {{"gerbil", "apple"}, {"gerbil", "orange"}, {"gerbil", "banana"},
		// {"hamster", "apple"}, {"hamster", "orange"}, {"hamster", "banana"}}
		Set<Set<String>> animalSets = Sets.powerSet(animals);
		// {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}}
		// Maps
		List<String> l = Lists.newArrayList("ad", "da");
		ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(
				l.iterator(), new Function<String, Integer>() {
					public Integer apply(String string) {
						return string.length();
					}
				});
		Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
		Map<String, Integer> right = ImmutableMap.of("a", 1, "b", 2, "c", 3);
		MapDifference<String, Integer> diff = Maps.difference(left, right);

		diff.entriesInCommon(); // {"b" => 2}
		diff.entriesInCommon(); // {"b" => 2}
		diff.entriesOnlyOnLeft(); // {"a" => 1}
		diff.entriesOnlyOnRight(); // {"d" => 5}
		// Multisets
		Multiset<String> multiset1 = HashMultiset.create();
		multiset1.add("a", 2);
		Multiset<String> multiset2 = HashMultiset.create();
		multiset2.add("a", 5);
		multiset1.containsAll(multiset2); // 返回true；因为包含了所有不重复元素，
		// 虽然multiset1实际上包含2个"a"，而multiset2包含5个"a"
		Multisets.containsOccurrences(multiset1, multiset2); // returns false
		multiset2.remove(multiset1, 2); // multiset2 现在包含3个"a"
		multiset2.removeAll(multiset1);// multiset2移除所有"a"，虽然multiset1只有2个"a"
		multiset2.isEmpty(); // returns true
		ImmutableMultiset highestCountFirst = Multisets
				.copyHighestCountFirst(multiset);
		// highestCountFirst，包括它的entrySet和elementSet，按{"b", "a", "c"}排列元素

	}

	public void newType() {
		// Multiset
		// 方法 描述
		// count(E) 给定元素在Multiset中的计数
		// elementSet() Multiset中不重复元素的集合，类型为Set<E>
		// entrySet()
		// 和Map的entrySet类似，返回Set<Multiset.Entry<E>>，其中包含的Entry支持getElement()和getCount()方法
		// add(E, int) 增加给定元素在Multiset中的计数
		// remove(E, int) 减少给定元素在Multiset中的计数
		// setCount(E, int) 设置给定元素在Multiset中的计数，不可以为负数
		// size() 返回集合元素的总个数（包括重复的元素）

		// Guava提供了多种Multiset的实现，大致对应JDK中Map的各种实现：
		// Map 对应的Multiset 是否支持null元素
		// HashMap HashMultiset 是
		// TreeMap TreeMultiset 是（如果comparator支持的话）
		// LinkedHashMap LinkedHashMultiset 是
		// ConcurrentHashMap ConcurrentHashMultiset 否
		// ImmutableMap ImmutableMultiset 否

		// Multimap提供了多种形式的实现。在大多数要使用Map<K, Collection<V>>的地方，你都可以使用它们：
		// 实现 键行为类似 值行为类似
		// ArrayListMultimap HashMap ArrayList
		// HashMultimap HashMap HashSet
		// LinkedListMultimap* LinkedHashMap* LinkedList*
		// LinkedHashMultimap** LinkedHashMap LinkedHashMap
		// TreeMultimap TreeMap TreeSet
		// ImmutableListMultimap ImmutableMap ImmutableList
		// ImmutableSetMultimap ImmutableMap ImmutableSet
	}
}
