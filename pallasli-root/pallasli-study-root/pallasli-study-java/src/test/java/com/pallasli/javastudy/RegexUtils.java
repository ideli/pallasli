package com.pallasli.javastudy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexUtils {

	@Test
	public void firstToUpperCase() {
		String str = "this is a java test";
		System.out.println(str);
		StringBuffer stringbf = new StringBuffer();
		Matcher m = Pattern
				.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(
						str);
		while (m.find()) {
			m.appendReplacement(stringbf, m.group(1).toUpperCase()
					+ m.group(2).toLowerCase());
		}
		System.out.println(m.appendTail(stringbf).toString());
	}

	@Test
	public void replaceMatcher() {
		Pattern p = Pattern.compile("hello");
		String instring = "hello hello hello.";
		System.out.println("initial String: " + instring);
		Matcher m = p.matcher(instring);
		String tmp = m.replaceAll("Java");
		System.out.println("String after replacing 1st Match: " + tmp);
	}

	@Test
	public void email() {
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String email1 = "user@domain.com";
		Boolean b = email1.matches(EMAIL_REGEX);
		System.out.println("is e-mail: " + email1 + " :Valid = " + b);
		String email2 = "user^domain.co.in";
		b = email2.matches(EMAIL_REGEX);
		System.out.println("is e-mail: " + email2 + " :Valid = " + b);
	}

	@Test
	public void date() {

		boolean isDate = false;
		String date1 = "8-05-1988";
		String date2 = "08/04/1987";
		String datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}";
		isDate = date1.matches(datePattern);
		System.out.println("Date :" + date1
				+ ": matches with the this date Pattern:" + datePattern
				+ "Ans:" + isDate);
		isDate = date2.matches(datePattern);
		System.out.println("Date :" + date2
				+ ": matches with the this date Pattern:" + datePattern
				+ "Ans:" + isDate);
	}

	@Test
	public void replaceFirstMatcher() {
		Pattern p = Pattern.compile("hello");
		String instring = "hello hello hello.";
		System.out.println("initial String: " + instring);
		Matcher m = p.matcher(instring);
		String tmp = m.replaceFirst("Java");
		System.out.println("String after replacing 1st Match: " + tmp);
	}

	@Test
	public void patternSplit() {
		Pattern p = Pattern.compile(" ");
		String tmp = "this is the Java example";
		String[] tokens = p.split(tmp);
		for (int i = 0; i < tokens.length; i++) {
			System.out.println(tokens[i]);
		}
	}

	@Test
	public void search() {
		Pattern p = Pattern.compile("j(ava)");
		String candidateString = "This is a java program.  This is another java program.";
		Matcher matcher = p.matcher(candidateString);
		int nextIndex = matcher.start(1);
		System.out.println(candidateString);
		System.out.println("The index for java is:" + nextIndex);
	}

	@Test
	public void groupSearch() {
		Pattern p = Pattern.compile("J(ava)");
		String candidateString = "This is Java.  This is a Java example.";
		Matcher matcher = p.matcher(candidateString);
		int numberOfGroups = matcher.groupCount();
		System.out.println("numberOfGroups =" + numberOfGroups);
	}

	@Test
	public void testPhone() {
		isPhoneValid("1-999-585-4009");
		isPhoneValid("999-585-4009");
		isPhoneValid("1-585-4009");
		isPhoneValid("585-4009");
		isPhoneValid("1.999-585-4009");
		isPhoneValid("999 585-4009");
		isPhoneValid("1 585 4009");
		isPhoneValid("111-Java2s");
	}

	public static boolean isPhoneValid(String phone) {
		boolean retval = false;
		String phoneNumberPattern = "(\\d-)?(\\d{3}-)?\\d{3}-\\d{4}";
		retval = phone.matches(phoneNumberPattern);
		String msg = "NO MATCH: pattern:" + phone + " regex: "
				+ phoneNumberPattern;
		if (retval) {
			msg = " MATCH: pattern:" + phone + "regex: " + phoneNumberPattern;
		}
		System.out.println(msg + "");
		return retval;
	}

	@Test
	public void removeDuplicateWhitespace() {
		String inputStr = "This is a Java program.  This is another Java Program.";
		String patternStr = "\\s+";
		String replaceStr = " ";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(inputStr);
		matcher.replaceAll(replaceStr);
	}

	@Test
	public void searchInFile() throws IOException {
		Pattern patt = Pattern.compile("[A-Za-z][a-z]+");
		BufferedReader r = new BufferedReader(new FileReader("ReaderIter.java"));
		String line;
		while ((line = r.readLine()) != null) {
			Matcher m = patt.matcher(line);
			while (m.find()) {
				System.out.println(m.group(0));
				int start = m.start(0);
				int end = m.end(0);
				// CharacterIterator.substring(offset, end);
				System.out.println(line.substring(start, end));
			}
		}
		r.close();
	}

	@Test
	public void searchLast() {
		String candidateString = "This is a Java example. This is another Java example.";
		Pattern p = Pattern.compile("Java");
		Matcher matcher = p.matcher(candidateString);
		matcher.find();
		int nextIndex = matcher.end();
		System.out.print("The last index of Java is:");
		System.out.println(nextIndex);
	}

	@Test
	public void searchMatcher() {
		String candidate = "this is a test, A TEST.";
		String regex = "\\ba\\w*\\b";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(candidate);
		String val = null;
		System.out.println("INPUT: " + candidate);
		System.out.println("REGEX: " + regex + " ");
		while (m.find()) {
			val = m.group();
			System.out.println("MATCH: " + val);
		}
		if (val == null) {
			System.out.println("NO MATCHES: ");
		}
	}

	@Test
	public void countSearchResult() {
		String candidate = "this is a test, A TEST.";
		String regex = "\\ba\\w*\\b";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(candidate);
		String val = null;
		System.out.println("INPUT: " + candidate);
		System.out.println("REGEX: " + regex + " ");
		while (m.find()) {
			val = m.group();
			System.out.println("MATCH: " + val);
		}
		if (val == null) {
			System.out.println("NO MATCHES: ");
		}
	}

	@Test
	public void searchSame() {
		String duplicatePattern = "\\b(\\w+) \\1\\b";
		Pattern p = Pattern.compile(duplicatePattern);
		int matches = 0;
		String phrase = " this is a test ";
		Matcher m = p.matcher(phrase);
		String val = null;
		while (m.find()) {
			val = ":" + m.group() + ":";
			matches++;
		}
		System.out.println(val);
		if (matches > 0)
			System.out.println("The string has matched with the pattern.");
		else
			System.out.println("The string has not matched with the pattern.");
	}

	@Test
	public void resetRegex() {
		Matcher m = Pattern.compile("[frb][aiu][gx]").matcher(
				"fix the rug with bags");
		while (m.find())
			System.out.println(m.group());
		m.reset("fix the rig with rags");
		while (m.find())
			System.out.println(m.group());
	}

}
