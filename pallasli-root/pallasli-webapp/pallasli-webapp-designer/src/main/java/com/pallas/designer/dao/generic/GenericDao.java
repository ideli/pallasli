package com.pallas.designer.dao.generic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lyt.designer.dao.BaseConn;

import com.pallasli.utils.StringUtils;

public class GenericDao<T extends BaseModel> extends BaseDao {
	public T model;

	@SuppressWarnings("unchecked")
	public List<TableFieldValue> getTableFieldsAndValues(T model) {
		List<TableFieldValue> list = new ArrayList<TableFieldValue>();
		Class<T> cls = (Class<T>) model.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			TableFieldValue fv = new TableFieldValue();
			try {
				if (!"serialVersionUID".equals(fieldName)) {
					String methodName = StringUtils.addPreGet(fieldName);
					Method method;

					method = cls.getDeclaredMethod(methodName,
							new Class<?>[] {});
					Object value = (method.invoke(model, new Object[] {}));
					if (value != null) {
						fv.setFieldName(fieldName);
						fv.setFieldType(field.getType());
						fv.setValue(value);
						list.add(fv);
					}
				}
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		return list;
	}

	public void save(T model) throws SQLException {
		if (model.getId() == null) {
			insert(model);
		} else {
			update(model);
		}
	}

	public void delete(T model) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("delete from ");
		sb.append(model.getT_table_name());
		sb.append(" where ");
		sb.append(model.getT_table_idcolumn());
		sb.append("=");
		sb.append(model.getId());
		ps = BaseConn.designCon.prepareStatement(sb.toString());
		ps.execute();
	}

	public void insert(Connection conn, T model) throws SQLException {
		String fields = "";
		String values = "";

		List<TableFieldValue> list = getTableFieldsAndValues(model);
		for (TableFieldValue fv : list) {
			Class<?> cls = fv.getFieldType();
			if (cls.equals(String.class)) {
				fields += fv.getFieldName() + ",";
				values += "'" + fv.getValue() + "',";
			} else if (cls.equals(Integer.class) || cls.equals(int.class)) {
				fields += fv.getFieldName() + ",";
				values += fv.getValue() + ",";
			} else if (cls.equals(Boolean.class)) {
				fields += fv.getFieldName() + ",";
				values += (Boolean.parseBoolean(fv.getValue().toString()) ? 1
						: 0) + ",";
			} else {
			}

		}
		fields = fields.substring(0, fields.length() - 1);
		values = values.substring(0, values.length() - 1);
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append(model.getT_table_name());
		sb.append(" ( ");
		sb.append(fields);
		sb.append(" ) ");
		sb.append(" values( ");
		sb.append(values);
		sb.append(" ) ");
		System.out.println(sb.toString());
		ps = conn.prepareStatement(sb.toString());
		ps.execute();

	}

	public void insert(T model) throws SQLException {
		String fields = "";
		String values = "";

		List<TableFieldValue> list = getTableFieldsAndValues(model);
		for (TableFieldValue fv : list) {
			fields += fv.getFieldName() + ",";
			values += "'" + fv.getValue() + "',";

		}
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append(model.getT_table_name());
		sb.append(" ( ");
		sb.append(fields);
		sb.append(" ) ");
		sb.append(" values( ");
		sb.append(values);
		sb.append(" ) ");
		ps = BaseConn.designCon.prepareStatement(sb.toString());
		ps.execute();

	}

	public void update(T model) throws SQLException {

		String updates = "";

		List<TableFieldValue> list = getTableFieldsAndValues(model);
		for (TableFieldValue fv : list) {
			updates += fv.getFieldName() + "='" + fv.getValue() + "',";

		}
		StringBuffer sb = new StringBuffer();
		sb.append("update ");
		sb.append(model.getT_table_name());
		sb.append(" set ");
		sb.append(updates);
		ps = BaseConn.designCon.prepareStatement(sb.toString());
		ps.execute();
	}

	public void find(T model) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append(model.getT_table_name());
		sb.append(" where ");
		sb.append(model.getT_table_idcolumn());
		sb.append(" = ");
		sb.append(model.getId());
		ps = BaseConn.designCon.prepareStatement(sb.toString());
		ps.executeQuery();

	}

	// public String query(T model, String whereSql) throws SQLException,
	// NoSuchColumnException, UnsupportedConversionException,
	// UnsupportedTypeException, InstantiationException,
	// IllegalAccessException {
	// StringBuffer sb = new StringBuffer();
	// sb.append("select * from ");
	// sb.append(model.getT_table_name());
	// sb.append(" where ");
	// sb.append(whereSql);
	// System.out.println(sb.toString());
	// ps = BaseConn.designCon.prepareStatement(sb.toString());
	// ResultSet rs = ps.executeQuery();
	// return MashalUtil.marshal(RowUtils.toMysqlList(rs, model.getClass()));
	//
	// }
}
