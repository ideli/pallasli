package physics.app.model;

public class DerivedSIUnits {
	private Integer id;
	private String physicalQuantity;
	private String englishNameOfSIUnit;
	private String symbolOfSIUnit;
	private String chineseNameOfSIUnit;

	public Integer getId() {
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

	public String getEnglishNameOfSIUnit() {
		return englishNameOfSIUnit;
	}

	public void setEnglishNameOfSIUnit(String englishNameOfSIUnit) {
		this.englishNameOfSIUnit = englishNameOfSIUnit;
	}

	public String getChineseNameOfSIUnit() {
		return chineseNameOfSIUnit;
	}

	public void setChineseNameOfSIUnit(String chineseNameOfSIUnit) {
		this.chineseNameOfSIUnit = chineseNameOfSIUnit;
	}

	public String getSymbolOfSIUnit() {
		return symbolOfSIUnit;
	}

	public void setSymbolOfSIUnit(String symbolOfSIUnit) {
		this.symbolOfSIUnit = symbolOfSIUnit;
	}
}