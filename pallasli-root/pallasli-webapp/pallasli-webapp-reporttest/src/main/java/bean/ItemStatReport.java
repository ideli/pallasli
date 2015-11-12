package bean;

public class ItemStatReport {
	private String name;
	private String sex;
	private String age;
	private String desc;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	private int seqNumber;

	public int getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(int seqNumber) {
		this.seqNumber = seqNumber;
	}

	private String stockOrgName;

	public String getStockOrgName() {
		return stockOrgName;
	}

	public void setStockOrgName(String stockOrgName) {
		this.stockOrgName = stockOrgName;
	}
}
