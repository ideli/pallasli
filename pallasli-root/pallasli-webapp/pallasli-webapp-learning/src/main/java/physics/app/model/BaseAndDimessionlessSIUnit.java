package physics.app.model;

public class BaseAndDimessionlessSIUnit {
	private int id;
	private String physicalQuantity;
	private String englishName;
	private String symbol;
	private String chineseName;
	private String comment;

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhysicalQuantity() {
		return physicalQuantity;
	}

	public void setPhysicalQuantity(String physicalQuantity) {
		this.physicalQuantity = physicalQuantity;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
