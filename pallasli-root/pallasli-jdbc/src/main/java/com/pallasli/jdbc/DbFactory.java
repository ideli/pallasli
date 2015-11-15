package com.pallasli.jdbc;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.pallasli.jdbc.exception.CallDbException;

public class DbFactory {
	private static final Object AbstractDbInterface = null;

	public DbFactory() {
	}

	public String toString() {
		return getClass().getName();
	}

	public AbstractDbInterface getDbInterface(Class objClass,
			String procedureName) throws CallDbException {

		AbstractDbInterface object = null;
		try {
			object = (AbstractDbInterface) objClass.newInstance();
		} catch (Exception ex) {
			throw new CallDbException("���ù��Լ����δ�����δʵ��!");
		}
		return object;
	}

	// --------------------------------------------------------------------------

	public String[] getAllProcedureName() {
		String[] procName = null;
		try {
			ArrayList<String> list = new ArrayList<String>();
			Class<? extends DbFactory> cls = this.getClass();
			Field fieldlist[] = cls.getDeclaredFields();
			for (int i = 0; i < fieldlist.length; i++) {
				Field fld = fieldlist[i];
				if (fld.getType().toString()
						.equals("class [Ljava.lang.String;")) {
					Field field = cls.getDeclaredField(fld.getName());
					String[] tmp = (String[]) field.get(null);
					for (int j = 0; j < tmp.length; j++) {
						list.add(tmp[j]);
					}
				}
			}
			procName = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				procName[i] = list.get(i);
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
		return procName;
	}
}
