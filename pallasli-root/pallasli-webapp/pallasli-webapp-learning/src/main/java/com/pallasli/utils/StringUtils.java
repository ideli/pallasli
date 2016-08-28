package com.pallasli.utils;

public class StringUtils {

	public static String leftStr(String source, int maxByteLen, int flag) throws Exception {
		// log.debug("leftStr: source=" + source);
		if (source == null || maxByteLen <= 0) {
			return "";
		}
		byte[] bStr = source.getBytes("utf-8");
		if (maxByteLen >= bStr.length)
			return source;
		String cStr = new String(bStr, maxByteLen - 1, 2);
		if (cStr.length() == 1 && source.contains(cStr)) {
			maxByteLen += flag;
		}
		return new String(bStr, 0, maxByteLen);
	}

	public static String repeat(String str, int num) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append(str);
		}
		return sb.toString();
	}

	public static String repeat(char ch, int num) {
		return repeat(String.valueOf(ch), num);
	}

	public static String FillWithChar(String src, int len, char ch, boolean left) {
		if (src.length() >= len) {
			try {
				return leftStr(src, len, -1);// src.substring(0, len);
			} catch (Exception e) {
				return src;
			}
		} else {
			StringBuffer sb = new StringBuffer();
			if (left) {
				sb.append(repeat(ch, len - src.getBytes().length)).append(src);
			} else {
				sb.append(src).append(repeat(ch, len - src.getBytes().length));
			}
			return sb.toString();
		}
	}

	public static String replaceComma(String source) {
		String token = ":";
		int beginIndex = source.indexOf(token);
		String comma = ",";
		source = source + " ";
		String rtn = " ";
		while (source.indexOf(token, beginIndex + 1) != -1) {
			if (source.length() - 1 != beginIndex) {
				int endIndex = source.indexOf(token, beginIndex + 1);

				endIndex = source.indexOf(token, beginIndex + 1);
				String interceptString = source.substring(beginIndex, endIndex);
				int commaIndex = interceptString.lastIndexOf(comma);
				if (commaIndex != -1) {
					interceptString = interceptString.substring(0, commaIndex);
				}
				beginIndex = endIndex;

			}
		}
		return rtn.trim();
	}

	public static String replaceBetween(String source, String token, int beginIndex) {
		if (source.length() - 1 != beginIndex) {
			int endIndex = source.indexOf(token, beginIndex + 1);
			source = source.substring(beginIndex, endIndex);

		}
		return source;
	}

	public static String removeQuotationBeginAndEnd(String source) {
		if (source.startsWith("\"") || source.startsWith("\'")) {
			source = source.substring(1);
		}
		if (source.endsWith("\"") || source.endsWith("\'")) {
			source = source.substring(0, source.length() - 1);
		}
		return source;
	}

	/**
	 * 获取字段名
	 * 
	 * @param methodName
	 * @return String fieldName
	 */
	public static String removePreGetOrSet(String methodName) {
		if (methodName != null) {
			if (methodName.startsWith("get") || methodName.startsWith("set")) {
				methodName = alterFirstCharToLower(methodName.substring(3));
			}
		}
		return methodName;
	}

	/**
	 * 获取get放法
	 * 
	 * @param fieldName
	 * @return String get methodName
	 */
	public static String addPreGet(String fieldName) {
		if (fieldName != null && fieldName.length() > 1) {
			boolean flag = Character.isUpperCase(fieldName.charAt(1));
			if (flag) {

				fieldName = "get" + fieldName;
			} else {
				fieldName = "get" + alterFirstCharToUpper(fieldName);
			}
		}
		return fieldName;
	}

	/**
	 * 获取set方法
	 * 
	 * @param fieldName
	 * @return String set methodName
	 */
	public static String addPreSet(String fieldName) {
		if (fieldName != null && fieldName.length() > 1) {
			boolean flag = Character.isUpperCase(fieldName.charAt(1));
			if (flag) {

				fieldName = "set" + fieldName;
			} else {
				fieldName = "set" + alterFirstCharToUpper(fieldName);
			}
		}
		return fieldName;
	}

	/**
	 * 将首字母转换为小写
	 * 
	 * @param str
	 * @return String first latter to lower
	 */
	public static String alterFirstCharToLower(String str) {
		if (str != null && str.length() != 0) {
			char c = str.charAt(0);
			if (c > 65 && c < 91) {
				String s = String.valueOf(c);
				s = s.toLowerCase();
				if (str.length() != 1) {
					str = s + str.substring(1);
				} else {
					str = s;
				}
			}

		}
		return str;
	}

	/**
	 * 将首字母转换为大写
	 * 
	 * @param str
	 * @return String first latter to upper
	 */
	public static String alterFirstCharToUpper(String str) {
		if (str != null && str.length() != 0) {
			char c = str.charAt(0);
			if (c > 96 && c < 123) {
				String s = String.valueOf(c);
				s = s.toUpperCase();
				if (str.length() != 1) {
					str = s + str.substring(1);
				} else {
					str = s;
				}
			}

		}
		return str;
	}

	/**
	 * 移除字符串中最后一个标识串
	 * 
	 * @param str
	 * @param mark
	 * @return String remains by remove last mark
	 */
	public static String removeLastMark(String str, String mark) {
		if (str != null && mark != null && mark.length() != 0 && str.length() != 0) {
			int markStart = str.lastIndexOf(mark);
			int markLength = mark.length();
			str = str.substring(0, markStart) + str.substring(markStart + markLength, str.length());

		}
		return str;
	}

	/**
	 * 替换字符串中最后一个标识串
	 * 
	 * @param str
	 * @param mark
	 * @param replace
	 * @return String remains by replace last mark
	 */
	public static String replaceLastMark(String str, String mark, String replace) {
		if (str != null && mark != null && mark.length() != 0 && str.length() != 0) {

			int markStart = str.lastIndexOf(mark);
			if (replace == null) {
				replace = "";
			}
			int markLength = mark.length();
			str = str.substring(0, markStart) + replace + str.substring(markStart + markLength, str.length());
		}

		return str;
	}

	/**
	 * 添加CDATA注释
	 * 
	 * @param string
	 * @return
	 */
	public static String addCDATA(String string) {
		if (string != null) {
			String returnStr = "<![CDATA[";
			returnStr += string;
			returnStr += "]]>";
			string = returnStr;
		}
		return string;
	}

	/**
	 * 拼音全集
	 */
	private static String[] name = { "zuo", "zun", "zui", "zuan", "zu", "zou", "zong", "zi", "zhuo", "zhun", "zhui",
			"zhuang", "zhuan", "zhuai", "zhua", "zhu", "zhou", "zhong", "zhi", "zheng", "zhen", "zhe", "zhao", "zhang",
			"zhan", "zhai", "zha", "zeng", "zen", "zei", "ze", "zao", "zang", "zan", "zai", "za", "yun", "yue", "yuan",
			"yu", "you", "yong", "yo", "ying", "yin", "yi", "ye", "yao", "yang", "yan", "ya", "xun", "xue", "xuan",
			"xu", "xiu", "xiong", "xing", "xin", "xie", "xiao", "xiang", "xian", "xia", "xi", "wu", "wo", "weng", "wen",
			"wei", "wang", "wan", "wai", "wa", "tuo", "tun", "tui", "tuan", "tu", "tou", "tong", "ting", "tie", "tiao",
			"tian", "ti", "teng", "te", "tao", "tang", "tan", "tai", "ta", "suo", "sun", "sui", "suan", "su", "sou",
			"song", "si", "shuo", "shun", "shui", "shuang", "shuan", "shuai", "shua", "shu", "shou", "shi", "sheng",
			"shen", "she", "shao", "shang", "shan", "shai", "sha", "seng", "sen", "se", "sao", "sang", "san", "sai",
			"sa", "ruo", "run", "rui", "ruan", "ru", "rou", "rong", "ri", "reng", "ren", "re", "rao", "rang", "ran",
			"qun", "que", "quan", "qu", "qiu", "qiong", "qing", "qin", "qie", "qiao", "qiang", "qian", "qia", "qi",
			"pu", "po", "ping", "pin", "pie", "piao", "pian", "pi", "peng", "pen", "pei", "pao", "pang", "pan", "pai",
			"pa", "ou", "o", "nuo", "nue", "nuan", "nv", "nu", "nong", "niu", "ning", "nin", "nie", "niao", "niang",
			"nian", "ni", "neng", "nen", "nei", "ne", "nao", "nang", "nan", "nai", "na", "mu", "mou", "mo", "miu",
			"ming", "min", "mie", "miao", "mian", "mi", "meng", "men", "mei", "me", "mao", "mang", "man", "mai", "ma",
			"luo", "lun", "lue", "luan", "lv", "lu", "lou", "long", "liu", "ling", "lin", "lie", "liao", "liang",
			"lian", "lia", "li", "leng", "lei", "le", "lao", "lang", "lan", "lai", "la", "kuo", "kun", "kui", "kuang",
			"kuan", "kuai", "kua", "ku", "kou", "kong", "keng", "ken", "ke", "kao", "kang", "kan", "kai", "ka", "jun",
			"jue", "juan", "ju", "jiu", "jiong", "jing", "jin", "jie", "jiao", "jiang", "jian", "jia", "ji", "huo",
			"hun", "hui", "huang", "huan", "huai", "hua", "hu", "hou", "hong", "heng", "hen", "hei", "he", "hao",
			"hang", "han", "hai", "ha", "guo", "gun", "gui", "guang", "guan", "guai", "gua", "gu", "gou", "gong",
			"geng", "gen", "gei", "ge", "gao", "gang", "gan", "gai", "ga", "fu", "fou", "fo", "feng", "fen", "fei",
			"fang", "fan", "fa", "er", "en", "e", "duo", "dun", "dui", "duan", "du", "dou", "****", "diu", "ding",
			"die", "diao", "dian", "di", "deng", "de", "dao", "dang", "dan", "dai", "da", "cuo", "cun", "cui", "cuan",
			"cu", "cou", "cong", "ci", "chuo", "chun", "chui", "chuang", "chuan", "chuai", "chu", "chou", "chong",
			"chi", "cheng", "chen", "che", "chao", "chang", "chan", "chai", "cha", "ceng", "ce", "cao", "cang", "can",
			"cai", "ca", "bu", "bo", "bing", "bin", "bie", "biao", "bian", "bi", "beng", "ben", "bei", "bao", "bang",
			"ban", "bai", "ba", "ao", "ang", "an", "ai", "a" };

	/**
	 * gbk2312汉字按拼音排序初始值集合
	 */
	private static int[] code = { -10254, -10256, -10260, -10262, -10270, -10274, -10281, -10296, -10307, -10309,
			-10315, -10322, -10328, -10329, -10331, -10519, -10533, -10544, -10587, -10764, -10780, -10790, -10800,
			-10815, -10832, -10838, -11014, -11018, -11019, -11020, -11024, -11038, -11041, -11045, -11052, -11055,
			-11067, -11077, -11097, -11303, -11324, -11339, -11340, -11358, -11536, -11589, -11604, -11781, -11798,
			-11831, -11847, -11861, -11867, -12039, -12058, -12067, -12074, -12089, -12099, -12120, -12300, -12320,
			-12346, -12359, -12556, -12585, -12594, -12597, -12607, -12802, -12812, -12829, -12831, -12838, -12849,
			-12852, -12858, -12860, -12871, -12875, -12888, -13060, -13063, -13068, -13076, -13091, -13095, -13096,
			-13107, -13120, -13138, -13147, -13318, -13326, -13329, -13340, -13343, -13356, -13359, -13367, -13383,
			-13387, -13391, -13395, -13398, -13400, -13404, -13406, -13601, -13611, -13658, -13831, -13847, -13859,
			-13870, -13878, -13894, -13896, -13905, -13906, -13907, -13910, -13914, -13917, -14083, -14087, -14090,
			-14092, -14094, -14097, -14099, -14109, -14112, -14122, -14123, -14125, -14135, -14137, -14140, -14145,
			-14149, -14151, -14159, -14170, -14345, -14353, -14355, -14368, -14379, -14384, -14399, -14407, -14429,
			-14594, -14630, -14645, -14654, -14663, -14668, -14670, -14674, -14678, -14857, -14871, -14873, -14882,
			-14889, -14894, -14902, -14908, -14914, -14921, -14922, -14926, -14928, -14929, -14930, -14933, -14937,
			-14941, -15109, -15110, -15117, -15119, -15121, -15128, -15139, -15140, -15141, -15143, -15144, -15149,
			-15150, -15153, -15158, -15165, -15180, -15183, -15362, -15363, -15369, -15375, -15377, -15385, -15394,
			-15408, -15416, -15419, -15435, -15436, -15448, -15454, -15625, -15631, -15640, -15652, -15659, -15661,
			-15667, -15681, -15701, -15707, -15878, -15889, -15903, -15915, -15920, -15933, -15944, -15958, -15959,
			-16155, -16158, -16169, -16171, -16180, -16187, -16202, -16205, -16212, -16216, -16220, -16393, -16401,
			-16403, -16407, -16412, -16419, -16423, -16427, -16429, -16433, -16448, -16452, -16459, -16465, -16470,
			-16474, -16647, -16657, -16664, -16689, -16706, -16708, -16733, -16915, -16942, -16970, -16983, -17185,
			-17202, -17417, -17427, -17433, -17454, -17468, -17482, -17487, -17496, -17676, -17683, -17692, -17697,
			-17701, -17703, -17721, -17730, -17733, -17752, -17759, -17922, -17928, -17931, -17947, -17950, -17961,
			-17964, -17970, -17988, -17997, -18012, -18181, -18183, -18184, -18201, -18211, -18220, -18231, -18237,
			-18239, -18446, -18447, -18448, -18463, -18478, -18490, -18501, -18518, -18526, -18696, -18697, -18710,
			-18722, -18731, -18735, -18741, -18756, -18763, -18773, -18774, -18783, -18952, -18961, -18977, -18996,
			-19003, -19006, -19018, -19023, -19038, -19212, -19218, -19224, -19227, -19235, -19238, -19242, -19243,
			-19249, -19261, -19263, -19270, -19275, -19281, -19288, -19289, -19467, -19479, -19484, -19500, -19515,
			-19525, -19531, -19540, -19715, -19725, -19728, -19739, -19741, -19746, -19751, -19756, -19763, -19774,
			-19775, -19784, -19805, -19976, -19982, -19986, -19990, -20002, -20026, -20032, -20036, -20051, -20230,
			-20242, -20257, -20265, -20283, -20292, -20295, -20304, -20317, -20319 };

	/**
	 * 将ISO8859-1转换为GBK
	 * 
	 * @param strvalue
	 * @return
	 */
	public static String iso8859Rgbk(String strvalue) {
		try {
			if (strvalue == null)
				return strvalue;

			strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
			return strvalue;
		} catch (Exception e) {
		}
		return strvalue;
	}

	/**
	 * 获取GBK字符串的拼音
	 * 
	 * @param gb2312
	 * @return
	 */
	public static String getPinyin(String gb2312) {
		if ((null == gb2312) || ("".equals(gb2312.trim())))
			return gb2312;

		char[] chars = gb2312.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		int i = 0;
		for (int Len = chars.length; i < Len; ++i) {
			retuBuf.append(getPinyin(chars[i]));
		}

		return retuBuf.toString();
	}

	/**
	 * 获取GBK字符串的拼音首字母
	 * 
	 * @param gb2312
	 * @return
	 */
	public static String getSimplePinyin(String gb2312) {
		if ((null == gb2312) || ("".equals(gb2312.trim())))
			return gb2312;

		char[] chars = gb2312.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		int i = 0;
		for (int Len = chars.length; i < Len; ++i) {
			retuBuf.append(getSimplePinyin(chars[i]));
		}

		return retuBuf.toString();
	}

	/**
	 * 获取GBK字符的拼音
	 * 
	 * @param gb2312
	 * @return
	 */
	public static String getPinyin(char gb2312) {
		int ascii = getCnAscii(gb2312);
		if (ascii == 0)
			return String.valueOf(gb2312);

		String spell = getSpellByAscii(ascii);
		if (spell == null)
			return String.valueOf(gb2312);

		return spell;
	}

	/**
	 * 获取GBK字符的拼音首字母
	 * 
	 * @param gb2312
	 * @return
	 */
	public static String getSimplePinyin(char gb2312) {
		int ascii = getCnAscii(gb2312);
		if (ascii == 0)
			return String.valueOf(gb2312);

		String spell = getSimpleSpellByAscii(ascii);
		if (spell == null)
			return String.valueOf(gb2312);

		return spell;
	}

	/**
	 * 获取GBK字符的编码
	 * 
	 * @param gb2312
	 * @return
	 */
	private static int getCnAscii(char cn) {
		byte[] bytes = null;
		try {
			bytes = String.valueOf(cn).getBytes("GB2312");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((bytes == null) || (bytes.length > 2) || (bytes.length <= 0))
			return 0;

		if (bytes.length == 1)
			return bytes[0];

		int hightByte = 256 + bytes[0];
		int lowByte = 256 + bytes[1];
		int ascii = 256 * hightByte + lowByte - 65536;
		return ascii;
	}

	/**
	 * 根据GBK编码获取拼音
	 * 
	 * @param gb2312
	 * @return
	 */
	private static String getSpellByAscii(int ascii) {
		if ((ascii > 0) && (ascii < 160))
			return String.valueOf((char) ascii);

		if ((ascii < -20319) || (ascii > -10247)) {
			return "?";
		}
		int ind = 0;
		for (ind = 0; ind < code.length; ++ind)
			if (ascii >= code[ind])
				break;

		return name[ind];
	}

	/**
	 * 根据GBK编码获取拼音首字母
	 * 
	 * @param gb2312
	 * @return
	 */
	private static String getSimpleSpellByAscii(int ascii) {
		if ((ascii > 0) && (ascii < 160))
			return String.valueOf((char) ascii);

		if ((ascii < -20319) || (ascii > -10247)) {
			return "?";
		}
		int ind = 0;
		for (ind = 0; ind < code.length; ++ind)
			if (ascii >= code[ind])
				break;
		String pinyin = name[ind];
		String rtn = "";
		if (pinyin != null && !"".equals(pinyin)) {
			rtn = String.valueOf(pinyin.charAt(0));
		}
		return rtn;
	}

	public static void main(String[] s) {
		System.out.println("我");
		System.out.println(getSimplePinyin("泶疶泶壆蒆乴斈學踅血"));
		System.out.println(getSimplePinyin("我哈健康"));
	}

	public static boolean updateSpellAndSimpleSpellOfTable(String spellColumn, String simpleSpellColumn,
			String chineseColumn, String table) {
		boolean rtn = false;
		StringBuffer sql = new StringBuffer();
		sql.append("update ").append(table).append(" set ").append(spellColumn).append("=")
				.append(getPinyin(chineseColumn)).append(",set ").append(simpleSpellColumn).append("=")
				.append(getSimplePinyin(chineseColumn));
		return rtn;
	}

	public static String parseFirstToUpper(String str) {
		String s = String.valueOf(str.charAt(0)).toUpperCase().concat(str.substring(1));
		return s;
	}

	public static String parseFirstToLower(String str) {
		String s = String.valueOf(str.charAt(0)).toLowerCase().concat(str.substring(1));
		return s;
	}

	public static String parseToGetField(String field) {
		String s = "get".concat(parseFirstToUpper(field));
		return s;
	}

	public static String parseToSetField(String field) {
		String s = "set".concat(parseFirstToUpper(field));
		return s;
	}

	public static String parseToField(String method) {
		if (method.substring(0, 3).equals("set")) {
			String s = parseFirstToUpper(method.substring(3));
			return s;
		}
		return null;
	}

	public static String replaceInString(String in, String from, String to) {
		if (in == null || from == null || to == null) {
			return in;
		}

		StringBuffer newValue = new StringBuffer();
		char[] inChars = in.toCharArray();
		int inLen = inChars.length;
		char[] fromChars = from.toCharArray();
		int fromLen = fromChars.length;

		for (int i = 0; i < inLen; i++) {
			if (inChars[i] == fromChars[0] && (i + fromLen) <= inLen) {
				boolean isEqual = true;
				for (int j = 1; j < fromLen; j++) {
					if (inChars[i + j] != fromChars[j]) {
						isEqual = false;
						break;
					}
				}
				if (isEqual) {
					newValue.append(to);
					i += fromLen - 1;
				} else {
					newValue.append(inChars[i]);
				}
			} else {
				newValue.append(inChars[i]);
			}
		}
		return newValue.toString();
	}

	/**
	 * ȥ���ַ���߿ո�
	 * 
	 * @param source
	 *            Դ�ַ�
	 * @return
	 */
	public static String LTrim(String source) {
		if (source == null || source.length() == 0) {
			return "";
		}
		if (source.charAt(0) != ' ') {
			return source;
		} else {
			int i;
			for (i = 0; i < source.length(); i++) {
				if (source.charAt(i) != ' ') {
					break;
				}
			}
			return source.substring(i);
		}
	}

	/**
	 * ȥ���ַ��ұ߿ո�
	 * 
	 * @param source
	 * @return
	 */
	public static String RTrim(String source) {
		if (source == null || source.length() == 0) {
			return "";
		}
		if (source.charAt(source.length() - 1) != ' ') {
			return source;
		} else {
			int i;
			for (i = source.length() - 1; i > 0; i--) {
				if (source.charAt(i) != ' ') {
					break;
				}
			}
			return source.substring(0, i + 1);
		}
	}

	/**
	 * ȥ���ַ�ǰ��ո�
	 * 
	 * @param source
	 * @return
	 */
	public static String Trim(String source) {
		return LTrim(RTrim(source));
	}

	/**
	 * �����ظ�������ַ�
	 * 
	 * @param str
	 *            Ҫ�ظ����ַ�
	 * @param number
	 *            �ظ�����
	 * @return
	 */
	public static String repeatString(String str, int number) {
		str = getNullString(str);
		if (number < 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer(str.length() * number);
		for (int i = 0; i < number; i++) {
			sb.append(str);
		}
		return sb.toString();
	}

	/**
	 * ��������ǰ̨alert��ʾ���ַ�
	 * 
	 * @param str
	 * @return
	 */
	public static String getAlertString(String str) {
		str = getNullString(str);
		return str.replace((char) 10, ' ').replace((char) 13, ' ').replace('"', '\'');
	}

	public static String getNullString(String str) {
		return (str == null) ? "" : str;
	}

	/**
	 * ��HEX�����ֽ������е�ֵ
	 * 
	 * @param bytes
	 * @return
	 */
	public static final String encodeHex(byte[] bytes) {
		StringBuffer buff = new StringBuffer(bytes.length * 2);
		String b;
		for (int i = 0; i < bytes.length; i++) {
			b = Integer.toHexString(bytes[i]);
			// byte�������ֽڵģ��������Integer.toHexString����ֽ���չΪ4���ֽ�
			buff.append(b.length() > 2 ? b.substring(6, 8) : b);
			buff.append(" ");
		}
		return buff.toString();
	}

}
