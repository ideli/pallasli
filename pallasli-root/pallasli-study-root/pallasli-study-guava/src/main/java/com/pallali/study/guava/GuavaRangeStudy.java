package com.pallali.study.guava;

import com.google.common.collect.BoundType;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

public class GuavaRangeStudy {
	public static void main(String[] args) {
		Range.downTo(4, BoundType.CLOSED);// (a..+∞)或[a..+∞)，取决于boundType
		Range.range(1, BoundType.CLOSED, 4, BoundType.OPEN);// [1..4)，等同于Range.closedOpen(1,
		// 4)
		Range.closed(1, 3).contains(2);// return true
		Range.closed(1, 3).contains(4);// return false
		Range.lessThan(5).contains(5); // return false
		Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3)); // return true
		Range.closedOpen(4, 4).isEmpty(); // returns true
		Range.openClosed(4, 4).isEmpty(); // returns true
		Range.closed(4, 4).isEmpty(); // returns false
		Range.open(4, 4).isEmpty(); // Range.open throws
									// IllegalArgumentException
		Range.closed(3, 10).lowerEndpoint(); // returns 3
		Range.open(3, 10).lowerEndpoint(); // returns 3
		Range.closed(3, 10).lowerBoundType(); // returns CLOSED
		Range.open(3, 10).upperBoundType(); // returns OPEN
		Range.closed(3, 5).isConnected(Range.open(5, 10)); // returns true
		Range.closed(0, 9).isConnected(Range.closed(3, 4)); // returns true
		Range.closed(0, 5).isConnected(Range.closed(3, 9)); // returns true
		Range.open(3, 5).isConnected(Range.open(5, 10)); // returns false
		Range.closed(1, 5).isConnected(Range.closed(6, 10)); // returns false
		Range.closed(3, 5).intersection(Range.open(5, 10)); // returns (5, 5]
		Range.closed(0, 9).intersection(Range.closed(3, 4)); // returns [3, 4]
		Range.closed(0, 5).intersection(Range.closed(3, 9)); // returns [3, 5]
		Range.open(3, 5).intersection(Range.open(5, 10)); // throws IAE
		Range.closed(1, 5).intersection(Range.closed(6, 10)); // throws IAE
		Range.closed(3, 5).span(Range.open(5, 10)); // returns [3, 10)
		Range.closed(0, 9).span(Range.closed(3, 4)); // returns [0, 9]
		Range.closed(0, 5).span(Range.closed(3, 9)); // returns [0, 9]
		Range.open(3, 5).span(Range.open(5, 10)); // returns (3, 10)
		Range.closed(1, 5).span(Range.closed(6, 10)); // returns [1, 10]
		ImmutableSortedSet set = ContiguousSet.create(Range.open(1, 5),
				DiscreteDomain.integers());
		// set包含[2, 3, 4]
		ContiguousSet.create(Range.greaterThan(0), DiscreteDomain.integers());
		// set包含[1, 2, ..., Integer.MAX_VALUE]

	}
}
