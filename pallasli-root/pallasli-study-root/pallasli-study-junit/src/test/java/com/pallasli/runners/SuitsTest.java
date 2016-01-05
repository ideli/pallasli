package com.pallasli.runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = { Junit38Test.class, Junit4Test.class,
		ParameterizedTest.class })
public class SuitsTest {

}
