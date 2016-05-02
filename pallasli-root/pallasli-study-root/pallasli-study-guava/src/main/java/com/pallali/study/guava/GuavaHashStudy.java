package com.pallali.study.guava;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;

public class GuavaHashStudy {
	public static void main(String[] args) {
		HashFunction hf = Hashing.md5();
		Funnel<? super Person> personFunnel = new Funnel<Person>() {

			public void funnel(Person person, PrimitiveSink into) {
				into.putInt(person.id)
						.putString(person.firstName, Charsets.UTF_8)
						.putString(person.lastName, Charsets.UTF_8).putInt(10);
			}
		};
		HashCode hc = hf.newHasher().putLong(122).putString("", Charsets.UTF_8)
				.putObject(new Person(), personFunnel).hash();

		// Hashing类
		// Hashing类提供了若干散列函数，以及运算HashCode对象的工具方法。
		//
		// 已提供的散列函数
		//
		// md5() murmur3_128() murmur3_32() sha1()
		// sha256() sha512() goodFastHash(int bits)
		// HashCode运算
		//
		// 方法 描述
		// HashCode combineOrdered( Iterable<HashCode>)
		// 以有序方式联接散列码，如果两个散列集合用该方法联接出的散列码相同，那么散列集合的元素可能是顺序相等的
		// HashCode combineUnordered( Iterable<HashCode>)
		// 以无序方式联接散列码，如果两个散列集合用该方法联接出的散列码相同，那么散列集合的元素可能在某种排序下是相等的
		// int consistentHash( HashCode, int buckets)
		// 为给定的”桶”大小返回一致性哈希值。当”桶”增长时，该方法保证最小程度的一致性哈希值变化。详见一致性哈希。

	}
}

class Person {
	final int id = 1;
	final String firstName = "";
	final String lastName = "";
	final int birthYear = 1;
}
