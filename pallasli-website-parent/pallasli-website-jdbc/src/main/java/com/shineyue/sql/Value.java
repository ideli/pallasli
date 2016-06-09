package com.shineyue.sql;

import java.math.*;
import java.sql.*;

public abstract class Value {
  public BigDecimal getBigDecimal()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to BigDecimal");
  }

  public boolean getBoolean()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to boolean");
  }

  public byte getByte()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to byte");
  }

  public byte[] getBytes()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to byte[]");
  }

  public Date getDate()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to Date");
  }

  public double getDouble()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to double");
  }

  public float getFloat()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to float");
  }

  public int getInt()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to int");
  }

  public long getLong()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to long");
  }

  public short getShort()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to short");
  }

  public Time getTime()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to Time");
  }

  public Timestamp getTimestamp()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to Timestamp");
  }

  public Object getObject()
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to Object");
  }

//---------
  public void setBigDecimal(BigDecimal value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to BigDecimal");
  }

  public void setBoolean(boolean value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to boolean");
  }

  public void setByte(byte value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to byte");
  }

  public void setBytes(byte[] value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to byte[]");
  }

  public void setDate(Date value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to Date");
  }

  public void setDouble(double value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to double");
  }

  public void setFloat(float value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to float");
  }

  public void setInt(int value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to int");
  }

  public void setLong(long value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to long");
  }

  public void setShort(short value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to short");
  }

  public void setTime(Time value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to Time");
  }

  public void setTimestamp(Timestamp value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to Timestamp");
  }

  public void setObject(Object value)
	  throws UnsupportedConversionException {
	throw new UnsupportedConversionException("No conversion to Object");
  }

  /**
   * 必须被所有子类实现的方法
   * 所有数据类型都能转化成字符串
   */
  public abstract String getString();
}
