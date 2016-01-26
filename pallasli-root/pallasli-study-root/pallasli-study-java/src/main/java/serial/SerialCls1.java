package serial;

import java.io.Serializable;

public class SerialCls1 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4169640965448946345L;
	private int age;
	private String userName1;

	public SerialCls1(String userName1, int age) {
		this.userName1 = userName1;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserName1() {
		return userName1;
	}

	public void setUserName(String userName1) {
		this.userName1 = userName1;
	}

}
