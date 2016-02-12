package com.pallasli.study.junit.hamcrest;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class HamcrestUseTest {
	@Test
	public void test() {
		List<String> values = new ArrayList<String>();
		values.add("0");
		values.add("4");
		values.add("6");
		values.add("7");
		values.add("9");
		assertThat(values, hasItem(anyOf(equalTo("0"), equalTo("1"))));
		assertThat(values, hasItems(equalTo("0"), equalTo("7")));
		assertThat("0", is(anyOf(equalTo("0"), equalTo("1"))));
		// assertThat(values, hasItems(allOf(equalTo("0"), equalTo("7"))));
		// assertThat("afadd", endsWith("dd"));
		// assertThat("afadd", containsString("dd"));
		// assertThat("afadd", startWith("dd"));
		//
		// CoreMatchers.anything(description)
		// CoreMatchers.not(value);
		// CoreMatchers.notNullValue();
		// CoreMatchers.nullValue(type);
		// CoreMatchers.instanceOf(type);
		// JUnitMatchers.both(matcher);
		// JUnitMatchers.either(matcher);
		// JUnitMatchers.everyItem(elementMatcher);

	}
}
