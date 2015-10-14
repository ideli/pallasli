package com.pallas.designer.dao.generic;


public class GenericDaoFactory {

	public static <T extends BaseModel> GenericDao<T> createGenericDao(
			Class<T> cls) {

		GenericDao<T> modelDao = new GenericDao<T>();
		return modelDao;
	}

}
