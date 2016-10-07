package com.pallasli.base.other;


public class RowUtils {
	// public static JsonObject toJsonObject(Row r)
	// throws NoSuchColumnException,
	// UnsupportedConversionException {
	// JsonObject data = new JsonObject();
	// Column[] columns = r.getColumns();
	// for (Column c : columns) {
	// String columnName = c.getName();
	// Object value = null;
	// int type = r.getColumnType(columnName);
	// switch (type) {
	// case FieldType.ORACLE_STRING:
	// value = r.getTrimString(columnName);
	// break;
	// case FieldType.ORACLE_BIGDECIMAL:
	// value = r.getDefDouble(columnName);
	// break;
	// case FieldType.ORACLE_DOUBLE:
	// value = r.getDefDouble(columnName);
	// break;
	// case FieldType.ORACLE_LONG:
	// value = r.getDefLong(columnName);
	// break;
	// case FieldType.ORACLE_FLOAT:
	// value = r.getDefDouble(columnName);
	// break;
	// case FieldType.ORACLE_INT:
	// value = r.getDefInt(columnName);
	// break;
	// case FieldType.ORACLE_TIMESTAMP:
	// value = r.getDefDateToString(columnName);
	// break;
	// default:
	// value = r.getTrimString(columnName);
	// }
	// if (value != null) {
	// data.addProperty(columnName, value.toString());
	// } else {
	// System.out.println("************null***" + columnName);
	// System.out.println("************null***" + type);
	// }
	// }
	// return data;
	// }
	//
	// public static <T extends Object> T toObject(Row r, Class<T> cls)
	// throws NoSuchColumnException, UnsupportedConversionException,
	// InstantiationException, IllegalAccessException {
	// T obj = cls.newInstance();
	// Field[] fields = cls.getFields();
	// for (Field f : fields) {
	// String name = f.getName();
	// }
	// JsonObject data = new JsonObject();
	// Column[] columns = r.getColumns();
	// for (Column c : columns) {
	// String columnName = c.getName();
	// Object value = null;
	// int type = r.getColumnType(columnName);
	// switch (type) {
	// case FieldType.ORACLE_STRING:
	// value = r.getTrimString(columnName);
	// break;
	// case FieldType.ORACLE_BIGDECIMAL:
	// value = r.getDefDouble(columnName);
	// break;
	// case FieldType.ORACLE_DOUBLE:
	// System.out.println(c.getName() + "   DOUBLE");
	// break;
	// case FieldType.ORACLE_LONG:
	// System.out.println(c.getName() + "   LONG");
	// break;
	// case FieldType.ORACLE_FLOAT:
	// System.out.println(c.getName() + "   float");
	// break;
	// case FieldType.ORACLE_INT:
	// System.out.println(c.getName() + "   int");
	// break;
	// case FieldType.ORACLE_TIMESTAMP:
	// System.out.println(c.getName() + "   TIMESTAMP");
	// break;
	// default:
	// System.out.println(c.getName() + "   default");
	// }
	//
	// }
	// return obj;
	// }
	//
	// public static <T extends Object> List<T> toObjectList(List<Row> l,
	// Class<T> cls) throws NoSuchColumnException,
	// UnsupportedConversionException, InstantiationException,
	// IllegalAccessException {
	// List<T> ls = new ArrayList<T>();
	// for (Row r : l) {
	//
	// ls.add(toObject(r, cls));
	// }
	// return ls;
	// }
	//
	// public static JsonArray toJsonArray(List<Row> l)
	// throws NoSuchColumnException, UnsupportedConversionException {
	// JsonArray jsonArray = new JsonArray();
	// for (Row r : l) {
	//
	// jsonArray.add(toJsonObject(r));
	// }
	// return jsonArray;
	// }
	//
	// public static JsonArray toJsonArray(ResultSet rs)
	// throws NoSuchColumnException, UnsupportedConversionException,
	// SQLException, UnsupportedTypeException {
	// List<Row> l = new ArrayList<Row>();
	// while ((rs != null) && (rs.next())) {
	// Row row = new Row(rs);
	// l.add(row);
	// }
	// return toJsonArray(l);
	// }
	//
	// public static <T extends Object> List<T> toList(ResultSet rs, Class<T>
	// cls)
	// throws NoSuchColumnException, UnsupportedConversionException,
	// SQLException, UnsupportedTypeException, InstantiationException,
	// IllegalAccessException {
	// List<Row> l = new ArrayList<Row>();
	// while ((rs != null) && (rs.next())) {
	// Row row = new Row(rs);
	// l.add(row);
	// }
	// return toObjectList(l, cls);
	// }
	//
	// public static <T extends Object> T toMysqlObject(Row r, Class<T> cls)
	// throws NoSuchColumnException, UnsupportedConversionException,
	// InstantiationException, IllegalAccessException {
	// T obj = cls.newInstance();
	// Field[] fields = cls.getDeclaredFields();
	// for (Field field : fields) {
	// String fieldName = field.getName();
	// TableFieldValue fv = new TableFieldValue();
	//
	// try {
	// Column[] columns = r.getColumns();
	// for (Column c : columns) {
	// String columnName = c.getName();
	// Object value = null;
	// int type = r.getColumnType(columnName);
	// Class ct = null;
	// Object v = null;
	// switch (type) {
	// case 1:
	// ct = String.class;
	// v = r.getString(columnName);
	// break;
	// case 3:
	// ct = Integer.class;
	// v = r.getInt(columnName);
	// break;
	// case 4:
	// ct = Integer.class;
	// v = r.getInt(columnName);
	// break;
	// default:
	// break;
	// }
	// if (columnName.replaceAll("_", "").equalsIgnoreCase(
	// fieldName)) {
	// String methodName = StringUtils.addPreSet(fieldName);
	// Method method;
	//
	// method = cls.getDeclaredMethod(methodName,
	// new Class<?>[] { ct });
	// method.invoke(obj, new Object[] { v });
	// }
	// }
	// } catch (SecurityException e1) {
	// e1.printStackTrace();
	// } catch (NoSuchMethodException e1) {
	// e1.printStackTrace();
	// } catch (IllegalArgumentException e1) {
	// e1.printStackTrace();
	// } catch (IllegalAccessException e1) {
	// e1.printStackTrace();
	// } catch (InvocationTargetException e1) {
	// e1.printStackTrace();
	// }
	// }
	// return obj;
	// }
	//
	// public static <T extends Object> List<T> toMysqlList(ResultSet rs,
	// Class<T> cls) throws NoSuchColumnException,
	// UnsupportedConversionException, SQLException,
	// UnsupportedTypeException, InstantiationException,
	// IllegalAccessException {
	// List<Row> l = new ArrayList<Row>();
	// while ((rs != null) && (rs.next())) {
	// Row row = new Row(rs);
	// l.add(row);
	// }
	// List<T> ls = new ArrayList<T>();
	// for (Row r : l) {
	//
	// ls.add(toMysqlObject(r, cls));
	// }
	// return ls;
	// }
}
