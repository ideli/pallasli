package physics.app.model.knowledge;

public class PhysicalLaws {
	private Integer id;
	private String lawName;
	private String chineseName;
	private String forlmula;
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getForlmula() {
		return forlmula;
	}

	public void setForlmula(String forlmula) {
		this.forlmula = forlmula;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
