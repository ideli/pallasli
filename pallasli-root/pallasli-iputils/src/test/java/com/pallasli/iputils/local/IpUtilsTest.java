package com.pallasli.iputils.local;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
@Ignore
public class IpUtilsTest {

	private final String ip;

	public IpUtilsTest(String ip) {
		this.ip = ip;
	}

	@Parameters
	public static Collection<String[]> getTestParameters() {
		return Arrays.asList(new String[][] {

				{ "159.226.115.22" } // , { "222.11.122.34" }, {
										// "199.226.115.22" }
		});
	}

	@Test
	public void getCountryCode() throws IOException {

		String val = IpUtils.getCountryCode(ip);
		assertEquals("CN", val);
	}

	@Test
	public void getCountry() throws IOException {

		String val = IpUtils.getCountry(ip);
		assertEquals("China", val);
	}

	@Test
	public void getCity() throws IOException {

		String val = IpUtils.getCity(ip);
		assertEquals("Beijing", val);
	}

	@Test
	public void getArea() throws IOException {

		int val = IpUtils.getArea(ip);
		assertEquals(0, val);
	}

	@Test
	public void getLongitude() throws IOException {

		float val = IpUtils.getLongitude(ip);
		assertEquals(116.388306, val, 5);
	}

	@Test
	public void getLatitude() throws IOException {

		float val = IpUtils.getLatitude(ip);
		assertEquals(39.928894, val, 5);
	}

	@Test
	public void getPostalCode() throws IOException {

		String val = IpUtils.getPostalCode(ip);
		assertEquals(null, val);
	}

	@Test
	public void getMetro_code() throws IOException {

		int val = IpUtils.getMetro_code(ip);
		assertEquals(0, val);
	}

	@Test
	public void getRegion() throws IOException {

		String val = IpUtils.getRegion(ip);
		assertEquals("Beijing", val);
	}

	@Test
	public void getDistance() throws IOException {

		String ip2 = "159.226.115.23";
		double val = IpUtils.getDistance(ip, ip2);
		assertTrue(0 == val);
	}

	@Test
	public void timeZoneByCountryAndRegion() throws IOException {

		String val = IpUtils.getCity(ip);
		assertEquals("Beijing", val);
		val = IpUtils.timeZoneByCountryAndRegion(ip);
		assertEquals("Asia/Chongqing", val);

	}

}
