package physics.app.gto;

public class SolarSystem {
	private Integer id;
	private Integer planetId;
	private String planetName;
	private String chineseName;
	private Double equatorialDiameter;
	private Integer eDUnitId;
	private Double meanDistanceFromSun;
	private Integer mDFSUnitId;
	private Double siderealPeriod;
	private Integer sPUnitId;
	private String sPUnit;
	private String eDUnit;
	private String mDFSUnit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlanetId() {
		return planetId;
	}

	public void setPlanetId(Integer planetId) {
		this.planetId = planetId;
	}

	public Double getEquatorialDiameter() {
		return equatorialDiameter;
	}

	public void setEquatorialDiameter(Double equatorialDiameter) {
		this.equatorialDiameter = equatorialDiameter;
	}

	public Double getMeanDistanceFromSun() {
		return meanDistanceFromSun;
	}

	public void setMeanDistanceFromSun(Double meanDistanceFromSun) {
		this.meanDistanceFromSun = meanDistanceFromSun;
	}

	public Double getSiderealPeriod() {
		return siderealPeriod;
	}

	public void setSiderealPeriod(Double siderealPeriod) {
		this.siderealPeriod = siderealPeriod;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public Integer getEDUnitId() {
		return eDUnitId;
	}

	public void setEDUnitId(Integer unitId) {
		eDUnitId = unitId;
	}

	public Integer getMDFSUnitId() {
		return mDFSUnitId;
	}

	public void setMDFSUnitId(Integer unitId) {
		mDFSUnitId = unitId;
	}

	public Integer getSPUnitId() {
		return sPUnitId;
	}

	public void setSPUnitId(Integer unitId) {
		sPUnitId = unitId;
	}

	public String getSPUnit() {
		return sPUnit;
	}

	public void setSPUnit(String unit) {
		sPUnit = unit;
	}

	public String getEDUnit() {
		return eDUnit;
	}

	public void setEDUnit(String unit) {
		eDUnit = unit;
	}

	public String getMDFSUnit() {
		return mDFSUnit;
	}

	public void setMDFSUnit(String unit) {
		mDFSUnit = unit;
	}
}