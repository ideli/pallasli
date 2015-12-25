package physics.app.model;

public class ConversionOfUnitToSIUnits {
	private int id;
	private int fromUnitId;
	private int toUnitId;
	private double mutiplyBy;// from/to

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromUnitId() {
		return fromUnitId;
	}

	public void setFromUnitId(int fromUnitId) {
		this.fromUnitId = fromUnitId;
	}

	public int getToUnitId() {
		return toUnitId;
	}

	public void setToUnitId(int toUnitId) {
		this.toUnitId = toUnitId;
	}

	public double getMutiplyBy() {
		return mutiplyBy;
	}

	public void setMutiplyBy(double mutiplyBy) {
		this.mutiplyBy = mutiplyBy;
	}
}