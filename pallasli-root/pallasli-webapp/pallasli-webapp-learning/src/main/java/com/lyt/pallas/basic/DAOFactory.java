package com.lyt.pallas.basic;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
	private static Map<Class<?>, GenericDAOImpl<? extends Entity>> daoMap = new HashMap<Class<?>, GenericDAOImpl<? extends Entity>>();

	@SuppressWarnings("unchecked")
	public static <T extends Entity> GenericDAO<T> getGenericDao(
			Class<T> modelClass) {
		GenericDAOImpl<T> answer = (GenericDAOImpl<T>) daoMap.get(modelClass);
		return answer;
	}
}
