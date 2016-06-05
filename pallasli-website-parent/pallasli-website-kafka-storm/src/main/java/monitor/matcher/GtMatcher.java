package monitor.matcher;

/**
 * ">"关系运算比较器
 * 
 * @author Administrator
 *
 */
public class GtMatcher implements IMatcher {

	private double origValue;

	public GtMatcher(String origValue) {
		this.origValue = Double.parseDouble(origValue);
	}

	@Override
	public boolean match(String destValue) {
		return Double.parseDouble(destValue) > origValue;
	}

}
