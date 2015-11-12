package com.pallasli.jdbc;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.pallasli.jdbc.exception.UnsupportedConversionException;

public abstract class Column {
	protected String name;
	protected boolean bNull;

	public Column(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isNull() {
		return bNull;
	}

	public void setNull(boolean value) {
		this.bNull = value;
	}

	public boolean isNumber() {
		String clsName = this.getClass().getName()
				.substring(this.getClass().getName().lastIndexOf(".") + 1);
		return clsName.equals("BigDecimalColumn")
				|| clsName.equals("DoubleColumn")
				|| clsName.equals("FloatColumn") || clsName.equals("IntColumn")
				|| clsName.equals("LongColumn")
				|| clsName.equals("ShortColumn");
	}

	public BigDecimal getBigDecimal() throws UnsupportedConversionException {
		throw new UnsupportedConversionException(
				"No conversion to BigDecimal [" + name + "]");
	}

	public boolean getBoolean() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to boolean ["
				+ name + "]");
	}

	public byte getByte() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to byte ["
				+ name + "]");
	}

	public byte[] getBytes() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to byte[] ["
				+ name + "]");
	}

	public Date getDate() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to Date ["
				+ name + "]");
	}

	public double getDouble() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to double ["
				+ name + "]");
	}

	public float getFloat() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to float ["
				+ name + "]");
	}

	public int getInt() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to int ["
				+ name + "]");
	}

	public long getLong() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to long ["
				+ name + "]");
	}

	public short getShort() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to short ["
				+ name + "]");
	}

	public abstract String getString();

	public Time getTime() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to Time ["
				+ name + "]");
	}

	public Timestamp getTimestamp() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to Timestamp ["
				+ name + "]");
	}

	public Object getObject() throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to Object ["
				+ name + "]");
	}

	public String toString() {
		return getString();
	}

	// --------------

	public void setBigDecimal(BigDecimal value)
			throws UnsupportedConversionException {
		throw new UnsupportedConversionException(
				"No conversion to BigDecimal [" + name + "]");
	}

	public void setBoolean(boolean value) throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to boolean ["
				+ name + "]");
	}

	public void setByte(byte value) throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to byte ["
				+ name + "]");
	}

	public void setBytes(byte[] value) throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to byte[] ["
				+ name + "]");
	}

	public void setDate(Date value) throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to Date ["
				+ name + "]");
	}

	public void setDouble(double value) throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to double ["
				+ name + "]");
	}

	public void setFloat(float value) throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to float ["
				+ name + "]");
	}

	public void setInt(int value) throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to int ["
				+ name + "]");
	}

	public void setLong(long value) throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to long ["
				+ name + "]");
	}

	public void setShort(short value) throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to short ["
				+ name + "]");
	}

	public void setTime(Time value) throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to Time ["
				+ name + "]");
	}

	public void setTimestamp(Timestamp value)
			throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to Timestamp ["
				+ name + "]");
	}

	public void setObject(Object value) throws UnsupportedConversionException {
		throw new UnsupportedConversionException("No conversion to Object ["
				+ name + "]");
	}

}
