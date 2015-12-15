//JDBC驱动程序将其发送到数据库之前的Java数据类型转换为相应的JDBC类型。它采用了默认的映射对于大多数数据类型。例如，一个Java整型转换为SQL INTEGER。创建默认映射提供的驱动程序之间的一致性。
//
//下表总结了Java数据类型转换为当调用PreparedStatement中的setXXX()方法或CallableStatement对象或ResultSet.updateXXX()方法的默认JDBC数据类型。
//
//SQL	JDBC/Java	setXXX	updateXXX
//VARCHAR	java.lang.String	setString	updateString
//CHAR	java.lang.String	setString	updateString
//LONGVARCHAR	java.lang.String	setString	updateString
//BIT	boolean	setBoolean	updateBoolean
//NUMERIC	java.math.BigDecimal	setBigDecimal	updateBigDecimal
//TINYINT	byte	setByte	updateByte
//SMALLINT	short	setShort	updateShort
//INTEGER	int	setInt	updateInt
//BIGINT	long	setLong	updateLong
//REAL	float	setFloat	updateFloat
//FLOAT	float	setFloat	updateFloat
//DOUBLE	double	setDouble	updateDouble
//VARBINARY	byte[ ]	setBytes	updateBytes
//BINARY	byte[ ]	setBytes	updateBytes
//DATE	java.sql.Date	setDate	updateDate
//TIME	java.sql.Time	setTime	updateTime
//TIMESTAMP	java.sql.Timestamp	setTimestamp	updateTimestamp
//CLOB	java.sql.Clob	setClob	updateClob
//BLOB	java.sql.Blob	setBlob	updateBlob
//ARRAY	java.sql.Array	setARRAY	updateARRAY
//REF	java.sql.Ref	SetRef	updateRef
//STRUCT	java.sql.Struct	SetStruct	updateStruct
//JDBC3.0增强了对BLOB，CLOB，ARRAY和REF数据类型的支持。 ResultSet 对象现在有UPDATEBLOB()，updateCLOB()，updateArray()，和updateRef()方法，可以直接操作服务器上的相应数据。
//
//setXXX()和updateXXX()方法能够将特定Java类型转换为特定的JDBC数据类型。该方法setObject()和updateObject()，能够将几乎任何Java类型映射到JDBC数据类型。
//
//ResultSet对象为每个数据类型来检索列值对应的getXXX()方法。每个方法可用于与列名或由它的序数位置。
//
//SQL	JDBC/Java	setXXX	getXXX
//VARCHAR	java.lang.String	setString	getString
//CHAR	java.lang.String	setString	getString
//LONGVARCHAR	java.lang.String	setString	getString
//BIT	boolean	setBoolean	getBoolean
//NUMERIC	java.math.BigDecimal	setBigDecimal	getBigDecimal
//TINYINT	byte	setByte	getByte
//SMALLINT	short	setShort	getShort
//INTEGER	int	setInt	getInt
//BIGINT	long	setLong	getLong
//REAL	float	setFloat	getFloat
//FLOAT	float	setFloat	getFloat
//DOUBLE	double	setDouble	getDouble
//VARBINARY	byte[ ]	setBytes	getBytes
//BINARY	byte[ ]	setBytes	getBytes
//DATE	java.sql.Date	setDate	getDate
//TIME	java.sql.Time	setTime	getTime
//TIMESTAMP	java.sql.Timestamp	setTimestamp	getTimestamp
//CLOB	java.sql.Clob	setClob	getClob
//BLOB	java.sql.Blob	setBlob	getBlob
//ARRAY	java.sql.Array	setARRAY	getARRAY
//REF	java.sql.Ref	SetRef	getRef
//STRUCT	java.sql.Struct	SetStruct	getStruct 日期和时间数据类型：
//java.sql.Date中的类映射到SQL DATE类型，以及java.sql.Time和java.sql.Timestamp类映射分别到SQL TIME和SQL TIMESTAMP数据类型。
//
//下面的例子显示了日期和时间格式类标准的Java日期和时间值相匹配的SQL数据类型的要求。


public class SqlDateTime {
	public static void main(String[] args) {
		// Get standard date and time
		java.util.Date javaDate = new java.util.Date();
		long javaTime = javaDate.getTime();
		System.out.println("The Java Date is:" + javaDate.toString());

		// Get and display SQL DATE
		java.sql.Date sqlDate = new java.sql.Date(javaTime);
		System.out.println("The SQL DATE is: " + sqlDate.toString());

		// Get and display SQL TIME
		java.sql.Time sqlTime = new java.sql.Time(javaTime);
		System.out.println("The SQL TIME is: " + sqlTime.toString());
		// Get and display SQL TIMESTAMP
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
		System.out.println("The SQL TIMESTAMP is: " + sqlTimestamp.toString());

	}// end main
}// end SqlDateTime