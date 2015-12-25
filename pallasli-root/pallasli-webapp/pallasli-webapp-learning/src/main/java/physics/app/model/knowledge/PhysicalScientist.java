package physics.app.model.knowledge;

public class PhysicalScientist {
	private Integer id;
	private String englishName;
	private String chineseName;
	private String importantContribute;// 贡献
	private String synopsis;// 简介

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getImportantContribute() {
		return importantContribute;
	}

	public void setImportantContribute(String importantContribute) {
		this.importantContribute = importantContribute;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
}
