package com.pallali.study.guava;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.base.Splitter;
import com.google.common.base.Splitter.MapSplitter;
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
	public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of("red", "orange", "yellow", "green", "blue",
			"purple");

	class Foo {
		Set<Bar> bars;

		Foo(Set<Bar> bars) {
			this.bars = ImmutableSet.copyOf(bars); // defensive copy!
		}
	}

	// 不可变集合可以用如下多种方式创建
	public void createImmutableSet() {
		// builder
		final ImmutableSet<Color> GOOGLE_COLORS = ImmutableSet.<Color> builder().add(new Color(0, 191, 255)).build();
		// 对有序不可变集合来说，排序是在构造集合的时候完成的
		ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
		// coptyOf
		ImmutableList<Color> defensiveCopy = ImmutableList.copyOf(GOOGLE_COLORS);

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
		Set<Color> copySet = Sets.newHashSet(new Color(0, 191, 255), new Color(0, 191, 255), new Color(0, 191, 255));
		List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");
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
		Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
		Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");
		SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength);
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
		ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(l.iterator(), new Function<String, Integer>() {
			@Override
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
		ImmutableMultiset highestCountFirst = Multisets.copyHighestCountFirst(multiset);
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

	public static void main(String[] args) throws IOException {
		// 使用Joiner类：
		//
		// 例如我们想将字符串列表通过一个分隔符链接起来，以前的方式就是迭代，append等操作，使用Joiner可以更方便。
		// Joiner一旦创建不可变，符合不变性，因此线程安全。
		// 看一些测试用例：
		//
		// 链接字符串列表：
		String delimiter = ",";
		// Joiner类一旦创建则不可变，满足不可变性，因此线程安全
		Joiner joiner = Joiner.on(delimiter);
		List<String> langs = new ArrayList<>();
		// 忽略null
		String excludeNullString = joiner.skipNulls().join(langs);
		// 将null替代为empty字符串
		String replaceNullString = joiner.useForNull("empty").join(langs);
		System.out.println("excludeNullString: " + excludeNullString);
		System.out.println("replaceNullString: " + replaceNullString);

		// 不对null处理，默认会抛NullPointerException
		String defaultNullString = joiner.join(langs); // langs为List<String>
		System.out.println("defaultNullString: " + defaultNullString);
		// 链接多个字符串：
		// Joiner类一旦创建则不可变，满足不可变性，因此线程安全
		joiner = Joiner.on(delimiter).skipNulls();
		// joiner.useForNull("empty"); //重复定义null操作会抛异常
		String res = joiner.join(null, "foo", "bar");
		System.out.println(res); // foo,bar
		// appendTo到实现了Appendable接口的类中：
		// append到StringBuilder
		StringBuilder stringBuilder = new StringBuilder();
		joiner = Joiner.on(",").skipNulls();
		joiner.appendTo(stringBuilder, "appendTo", "StringBuilder");
		System.out.println(stringBuilder); // appendTo,StringBuilder

		// append到输出流
		FileWriter writer = new FileWriter("append_text.txt");
		joiner.appendTo(writer, "appendTo", "FileWriter");
		writer.close();
		// 通过MapJoiner链接Map对象：
		Map<String, String> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		MapJoiner mapJoiner = Joiner.on(",").withKeyValueSeparator("=");
		String str = mapJoiner.join(map);
		System.out.println(str);// 结果如:key3=value3,key2=value2,key1=value1
		// 使用Splitter类：

		// Splitter功能与Joiner相反，其对字符串进行分割操作。
		// 分割字符串，返回Iterable<String>对象：
		str = "try ,do , your , best";
		Splitter splitter1 = Splitter.on(",").trimResults(); // 用逗号分割且去掉每个字符串周围的空格
		// splitter.trimResults(); //这样是不会去掉各个元素空格的, 它仅返回一个新的Splitter
		Iterable<String> res1 = splitter1.split(str);
		System.out.println(res); // [try, do, your, best]
		// 使用MapSplitter分割字符串，返回Map<String, String>对象：
		str = "key3=value3,key2=value2,key1=value1";
		MapSplitter splitter = Splitter.on(",").withKeyValueSeparator("=");
		map = splitter.split(str);
		System.out.println(map);// {key3=value3, key2=value2, key1=value1}
		// 使用Strings类：

		// Strings类有用的API:
		// 用padChar填充string后面, 使string最小长度为minLength
		// padEnd(String string, int minLength, char padChar)
		// 用padChar填充string前面, 使string最小长度为minLength
		// padStart(String string, int minLength, char padChar)
		// null转换为""
		// nullToEmpty(String string)
		// ""转换为null
		// emptyToNull(String string)
		// true, 如果字符串为null或者""
		// isNullOrEmpty(Strng string)
		// 使用CharMatcher类：

		// 替换多个连续的空格为单个空格。
		String tabsAndSpaces = "String  with      spaces     and tabs";
		// 将多个连续的空格替换为一个
		String scrubbed = CharMatcher.WHITESPACE.collapseFrom(tabsAndSpaces, ' ');
		System.out.println(scrubbed); // String with spaces and tabs
		// 有时我们还想将头尾的空格去掉：
		tabsAndSpaces = "    String  with     spaces     and tabs";
		scrubbed = CharMatcher.WHITESPACE.trimAndCollapseFrom(tabsAndSpaces, ' ');
		System.out.println(scrubbed); // String with spaces and tabs
		// 保留制定字符，如数字，字母等：
		String lettersAndNumbers = "foo989yxbar234";
		String retained = CharMatcher.JAVA_DIGIT.retainFrom(lettersAndNumbers); // 保留数字
		System.out.println(retained); // 989234
		// 你也可以通过Or组合多个CharMatcher:
		CharMatcher cm = CharMatcher.JAVA_DIGIT.or(CharMatcher.WHITESPACE);
		retained = cm.retainFrom("foo9 89y xbar 234");// 保留数字和空格
		System.out.println(retained); // 9 89 234
		// 使用Preconditions类：
		// 这是一个用于判断条件的一个工具，类似junit中Aseert功能。
		// checkNotNull(obj, "检查对象不能为空");
		// checkElementIndex(index, arr.length, "检查数组索引");
		// checkArgument(valueToSet <= 100, "检查参数值");
		// checkState(validateObjectState(), "检查对象状态");

	}
}
