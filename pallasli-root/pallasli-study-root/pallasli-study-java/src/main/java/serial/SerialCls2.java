package serial;

import java.io.Serializable;

public class SerialCls2 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4169640965448946345L;
	private int age;
	private String userName;

	public SerialCls2(String userName, int age) {
		this.userName = userName;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
