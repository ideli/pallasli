package physics.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import physics.app.model.Unit;

/**
 * 处理单位
 * 
 * @author Administrator
 * 
 */
public class UnitRalation {
	/**
	 * 将单位Json转换为Map 单位为key 指数为value
	 * 
	 * @param unitJson
	 * @return
	 */
	public static Map<String, String> getMapFromUnitJson(String unitJson) {
		Map<String, String> rtnMap = new HashMap<String, String>();
		if (unitJson != null) {
			String[] keyAndValue = unitJson.split(";");
			for (String kv : keyAndValue) {
				String[] kvs = kv.split(":");
				rtnMap.put(kvs[0], kvs[1]);
			}
		}
		return rtnMap;
	}

	/**
	 * 根据单位json串从unitlist中获取相应单位
	 * 
	 * @param unitList
	 * @param jsonMap
	 * @return
	 */
	public static Unit getUnitFromJsonMap(List<Unit> unitList,
			Map<String, String> jsonMap) {
		for (Unit u : unitList) {
			Map<String, String> unitMap = getMapFromUnitJson(u
					.getJsonDataOfUnitIdAndPower());
			if (unitMap.equals(jsonMap)) {
				return u;
			}
		}
		return null;
	}

	/**
	 * 单位乘法
	 * 
	 * @param unitOne
	 * @param unitTwo
	 * @return
	 */
	public static Map<String, String> getUnitJsonOneMutiplyTwo(Unit unitOne,
			Unit unitTwo) {
		Map<String, String> mapOne = new HashMap<String, String>();
		if (unitOne != null) {
			mapOne = getMapFromUnitJson(unitOne.getJsonDataOfUnitIdAndPower());
		}
		Map<String, String> mapTwo = new HashMap<String, String>();
		if (unitTwo != null) {
			mapTwo = getMapFromUnitJson(unitTwo.getJsonDataOfUnitIdAndPower());
		}
		Map<String, String> mapRtn = getUnitJsonOneMutiplyTwo(mapOne, mapTwo);
		return mapRtn;
	}

	public static Map<String, String> getUnitJsonOneMutiplyTwo(
			Map<String, String> mapOne, Map<String, String> mapTwo) {

		Map<String, String> mapRtn = new HashMap<String, String>();
		Set<String> setOne = new HashSet<String>();
		if (mapOne != null) {
			setOne = mapOne.keySet();
		}
		Set<String> setTwo = new HashSet<String>();
		if (mapTwo != null) {
			setTwo = mapTwo.keySet();
		}
		Iterator<String> itOne = setOne.iterator();
		Iterator<String> itTwo = setTwo.iterator();
		int valueOne = 0, valueTwo = 0, valueRtn = 0;
		while (itOne.hasNext()) {
			String keyOne = itOne.next();
			try {
				valueOne = Integer.parseInt(mapOne.get(keyOne));
				if (mapTwo.get(keyOne) != null) {
					valueTwo = Integer.parseInt(mapTwo.get(keyOne));
				}
				valueRtn = valueOne + valueTwo;
				mapRtn.put(keyOne, String.valueOf(valueRtn));
			} catch (NumberFormatException e) {
				return null;
			}
		}
		while (itTwo.hasNext()) {
			String keyTwo = itTwo.next();
			if (mapOne.get(keyTwo) == null) {
				String value2 = mapTwo.get(keyTwo);
				try {
					Integer.parseInt(value2);
					mapRtn.put(keyTwo, value2);
				} catch (NumberFormatException e) {
					return null;
				}
			}
		}
		return mapRtn;
	}

	/**
	 * 单位除法
	 * 
	 * @param base
	 *            除数
	 * @param division
	 *            被除数921of
	 * @return
	 */
	public static Map<String, String> getUnitJsonBaseDivideByDivision(
			Unit base, Unit division) {
		Map<String, String> mapBase = new HashMap<String, String>();
		if (base != null) {
			mapBase = getMapFromUnitJson(base.getJsonDataOfUnitIdAndPower());
		}
		Map<String, String> mapDivision = new HashMap<String, String>();
		if (division != null) {
			mapDivision = getMapFromUnitJson(division
					.getJsonDataOfUnitIdAndPower());
		}
		Set<String> setDivision = mapDivision.keySet();
		Iterator<String> itDivision = setDivision.iterator();
		while (itDivision.hasNext()) {
			String key = itDivision.next();
			try {
				int power = Integer.parseInt(mapDivision.get(key));
				mapDivision.put(key, String.valueOf(0 - power));
			} catch (NumberFormatException e) {
				return null;
			}
		}
		Map<String, String> mapRtn = getUnitJsonOneMutiplyTwo(mapBase,
				mapDivision);
		return mapRtn;
	}

	/**
	 * 计算division 是base 的几次方
	 * 
	 * @param base除数
	 * @param division
	 *            被除数
	 * @return
	 */
	public static int getMultiple(Unit base, Unit division) {
		int rtn = -1;
		int temp = -1;
		Map<String, String> baseMap = new HashMap<String, String>();
		if (base != null) {
			baseMap = getMapFromUnitJson(base.getJsonDataOfUnitIdAndPower());
		}
		Map<String, String> divisionMap = new HashMap<String, String>();
		if (division != null) {
			divisionMap = getMapFromUnitJson(division
					.getJsonDataOfUnitIdAndPower());
		}
		Set<String> baseSet = baseMap.keySet();
		Set<String> divSet = divisionMap.keySet();
		Iterator<String> baseIt = baseSet.iterator();
		if (divSet.size() == baseSet.size()) {
			while (baseIt.hasNext()) {
				String baseKey = baseIt.next();
				String basePower = baseMap.get(baseKey);
				String divisionPower = divisionMap.get(baseKey);
				try {
					temp = Integer.parseInt(divisionPower)
							/ Integer.parseInt(basePower);
					if (rtn == -1) {
						rtn = temp;
					} else {
						if (temp != rtn) {
							return -1;
						}
					}

				} catch (NumberFormatException e) {
					return -1;
				} catch (ArithmeticException e) {
					return -1;
				}

			}
		}
		return rtn;
	}

	public static void main(String[] s) {
		Unit base = new Unit();
		Unit div = new Unit();
		Unit div2 = new Unit();
		base.setJsonDataOfUnitIdAndPower("m:1;s:-2;kg:77");
		div.setJsonDataOfUnitIdAndPower("m:1;s:-2;kg:1;a:0");
		div2.setJsonDataOfUnitIdAndPower("m:2;s:-4;kg:78;a:0");
		List<Unit> list = new ArrayList<Unit>();
		list.add(base);
		list.add(div);
		list.add(div2);
		Unit rtn = getUnitFromJsonMap(list, getUnitJsonOneMutiplyTwo(base, div));

		if (rtn != null) {
			System.out.println(rtn.getJsonDataOfUnitIdAndPower());
		}
		System.out.println(getUnitJsonBaseDivideByDivision(base, null));
	}
}
