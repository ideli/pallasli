package physics.app.model;

import com.lyt.pallas.jdbc.model.Entity;

public class CompositeUnit extends Entity {
	private static final long serialVersionUID = 1L;

	/**
	 * This field was generated by Apache iBATIS Ibator. This field corresponds
	 * to the database column COMPOSITE_UNIT.ID
	 * 
	 * @ibatorgenerated Wed Jul 21 13:23:32 CST 2010
	 */
	private Long id;

	/**
	 * This field was generated by Apache iBATIS Ibator. This field corresponds
	 * to the database column COMPOSITE_UNIT.EXPRESSION
	 * 
	 * @ibatorgenerated Wed Jul 21 13:23:32 CST 2010
	 */
	private String expression;

	/**
	 * This method was generated by Apache iBATIS Ibator. This method returns
	 * the value of the database column COMPOSITE_UNIT.ID
	 * 
	 * @return the value of COMPOSITE_UNIT.ID
	 * 
	 * @ibatorgenerated Wed Jul 21 13:23:32 CST 2010
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method sets the
	 * value of the database column COMPOSITE_UNIT.ID
	 * 
	 * @param id
	 *            the value for COMPOSITE_UNIT.ID
	 * 
	 * @ibatorgenerated Wed Jul 21 13:23:32 CST 2010
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method returns
	 * the value of the database column COMPOSITE_UNIT.EXPRESSION
	 * 
	 * @return the value of COMPOSITE_UNIT.EXPRESSION
	 * 
	 * @ibatorgenerated Wed Jul 21 13:23:32 CST 2010
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method sets the
	 * value of the database column COMPOSITE_UNIT.EXPRESSION
	 * 
	 * @param expression
	 *            the value for COMPOSITE_UNIT.EXPRESSION
	 * 
	 * @ibatorgenerated Wed Jul 21 13:23:32 CST 2010
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method
	 * corresponds to the database table COMPOSITE_UNIT
	 * 
	 * @ibatorgenerated Wed Jul 21 13:23:32 CST 2010
	 */
	
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof CompositeUnit)) {
			return false;
		}
		CompositeUnit other = (CompositeUnit) that;
		return (this.getId() == null ? other.getId() == null : this.getId()
				.equals(other.getId()))
				&& (this.getExpression() == null ? other.getExpression() == null
						: this.getExpression().equals(other.getExpression()));
	}

	/**
	 * This method was generated by Apache iBATIS Ibator. This method
	 * corresponds to the database table COMPOSITE_UNIT
	 * 
	 * @ibatorgenerated Wed Jul 21 13:23:32 CST 2010
	 */
	
	public int hashCode() {
		int hash = 23;
		if (getId() != null) {
			hash *= getId().hashCode();
		}
		if (getExpression() != null) {
			hash *= getExpression().hashCode();
		}
		return hash;
	}
}