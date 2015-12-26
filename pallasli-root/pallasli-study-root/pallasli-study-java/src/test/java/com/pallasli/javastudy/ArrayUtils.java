package com.pallasli.javastudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ArrayUtils {

	@Test
	public void printArray() {
		int array[] = { 2, 5, -2, 6, -3, 8, 0, -7, -9, 4 };
		Arrays.sort(array);
		int index = Arrays.binarySearch(array, 2);
		System.out.println("Found 2 @ " + index);
		System.out.println("Sorted array" + ": [length: " + array.length + "]");
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				System.out.print(", ");
			}
			System.out.print(array[i]);
		}
		System.out.println();
	}

	@Test
	public void testEqual() {
		int[] ary = { 1, 2, 3, 4, 5, 6 };
		int[] ary1 = { 1, 2, 3, 4, 5, 6 };
		int[] ary2 = { 1, 2, 3, 4 };
		System.out.println("Is array 1 equal to array 2?? "
				+ Arrays.equals(ary, ary1));
		System.out.println("Is array 1 equal to array 3?? "
				+ Arrays.equals(ary, ary2));
	}

	@Test
	public void testContains() {
		ArrayList<String> objArray = new ArrayList<String>();
		ArrayList<String> objArray2 = new ArrayList<String>();
		objArray2.add(0, "common1");
		objArray2.add(1, "common2");
		objArray2.add(2, "notcommon");
		objArray2.add(3, "notcommon1");
		objArray.add(0, "common1");
		objArray.add(1, "common2");
		System.out.println("Array elements of array1" + objArray);
		System.out.println("Array elements of array2" + objArray2);
		System.out.println("Array 1 contains String common2?? "
				+ objArray.contains("common1"));
		System.out.println("Array 2 contains Array1?? "
				+ objArray2.contains(objArray));
	}

	@Test
	public void findSameElement() {
		ArrayList<String> objArray = new ArrayList<String>();
		ArrayList<String> objArray2 = new ArrayList<String>();
		objArray2.add(0, "common1");
		objArray2.add(1, "common2");
		objArray2.add(2, "notcommon");
		objArray2.add(3, "notcommon1");
		objArray.add(0, "common1");
		objArray.add(1, "common2");
		objArray.add(2, "notcommon2");
		System.out.println("Array elements of array1" + objArray);
		System.out.println("Array elements of array2" + objArray2);
		objArray.retainAll(objArray2);
		System.out
				.println("Array1 after retaining common  elements of array2 & array1"
						+ objArray);
	}

	@Test
	public void slidArray() {
		ArrayList<String> objArray = new ArrayList<String>();
		ArrayList<String> objArray2 = new ArrayList<String>();
		objArray2.add(0, "common1");
		objArray2.add(1, "common2");
		objArray2.add(2, "notcommon");
		objArray2.add(3, "notcommon1");
		objArray.add(0, "common1");
		objArray.add(1, "common2");
		objArray.add(2, "notcommon2");
		System.out.println("Array elements of array1" + objArray);
		System.out.println("Array elements of array2" + objArray2);
		objArray.removeAll(objArray2);
		System.out.println("Array1 after removing   array2 from array1"
				+ objArray);
	}

	@Test
	public void slidElement() {
		ArrayList<String> objArray = new ArrayList<String>();
		objArray.clear();
		objArray.add(0, "0th element");
		objArray.add(1, "1st element");
		objArray.add(2, "2nd element");
		System.out.println("Array before removing an element" + objArray);
		objArray.remove(1);
		objArray.remove("0th element");
		System.out.println("Array after removing an element" + objArray);
	}

	@Test
	public void sort() {
		int array[] = { 2, 5, -2, 6, -3, 8, 0, -7, -9, 4 };
		Arrays.sort(array);
	}

	@Test
	public void extend() {
		String[] names = new String[] { "A", "B", "C" };
		String[] extended = new String[5];
		extended[3] = "D";
		extended[4] = "E";
		System.arraycopy(names, 0, extended, 0, names.length);
		for (String str : extended) {
			System.out.println(str);
		}
	}

	@Test
	public void fill() {
		int array[] = new int[6];
		Arrays.fill(array, 100);
		for (int i = 0, n = array.length; i < n; i++) {
			System.out.println(array[i]);
		}
		System.out.println();
		Arrays.fill(array, 3, 6, 50);
		for (int i = 0, n = array.length; i < n; i++) {
			System.out.println(array[i]);
		}
	}

	@Test
	public void merge() {
		String a[] = { "A", "E", "I" };
		String b[] = { "O", "U" };
		List<String> list = new ArrayList<String>(Arrays.asList(a));
		list.addAll(Arrays.asList(b));
		Object[] c = list.toArray();
		System.out.println(Arrays.toString(c));
	}

	@Test
	public void max() {
		Integer[] numbers = { 8, 2, 7, 1, 4, 9, 5 };
		int max = Collections.max(Arrays.asList(numbers));
		System.out.println("Max number: " + max);
	}

	@Test
	public void min() {
		Integer[] numbers = { 8, 2, 7, 1, 4, 9, 5 };
		int min = Collections.min(Arrays.asList(numbers));
		System.out.println("Min number: " + min);

	}

	@Test
	public void reverse() {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("A");
		arrayList.add("B");
		arrayList.add("C");
		arrayList.add("D");
		arrayList.add("E");
		System.out.println("Before Reverse Order: " + arrayList);
		Collections.reverse(arrayList);
		System.out.println("After Reverse Order: " + arrayList);
	}

}
