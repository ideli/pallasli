package serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 1.非序列化对象，序列化后无法被反序列化
 * 
 * 2.序列化序列号不同，无法被反序列化
 * 
 * 3.序列号相同，类名不同也无法被序列化
 * 
 * 4.反序列化的条件，类名相同，序列号相同。自动生成的序列号由类名加字段名等根据算法生成，所以不指定序列号时，字段改变后序列号改变。
 * 
 * 是否指定序列号，就看在反序列化时是否需要兼容
 * 
 * @author lyt1987
 *
 */
public class SerialUtil {

	private static void SerializeSerialCls1() throws FileNotFoundException,
			IOException {
		SerialCls1 customer = new SerialCls1("gacl", 25);
		// ObjectOutputStream 对象输出流
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
				new File("/Users/lyt1987/Desktop/tmp/SerialCls1.txt")));
		oo.writeObject(customer);
		System.out.println("SerialCls1对象序列化成功！");
		oo.close();
	}

	private static SerialCls1 DeserializeSerialCls1() throws Exception,
			IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				new File("/Users/lyt1987/Desktop/tmp/SerialCls1.txt")));
		SerialCls1 customer = (SerialCls1) ois.readObject();
		System.out.println("SerialCls1对象反序列化成功！");
		return customer;
	}

	public static void main(String[] args) throws Exception {
		// SerializeSerialCls1();
		DeserializeSerialCls1();
	}
}
