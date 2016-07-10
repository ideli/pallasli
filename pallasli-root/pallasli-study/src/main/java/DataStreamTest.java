import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 必须先使用DataOutputStream写入数据，然后使用DataInputStream读取数据方可。 DataOutputStream
 * 数据输出流允许应用程序以适当方式将基本 Java 数据类型写入输出流中。然后应用程序可以使用数据输入流将数据读入。 DataOutputStream
 * 数据输入流允许应用程序以与机器无关方式从底层输入流中读取基本 Java
 * 数据类型。应用程序可以使用数据输出流写入稍后由数据输入流读取的数据。对于多线程访问不一定是安全的。 线程安全是可选的，它由此类方法的使用者负责。
 * 
 * @author 徐越
 * 
 */
public class DataStreamTest {
	public static void main(String[] args) throws Exception {
		DataStreamTest t = new DataStreamTest();
		t.write();
		t.read();
	}

	public void write() throws Exception {
		String path = this.getClass().getClassLoader().getResource("test.txt")
				.toURI().getPath();
		OutputStream os = new FileOutputStream(path);
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeDouble(Math.random());
		dos.writeBoolean(true);
		dos.writeInt(1000);
		dos.writeInt(2000);
		dos.flush();
		os.close();
		dos.close();
	}

	public void read() throws Exception {
		InputStream instream = this.getClass().getClassLoader()
				.getResourceAsStream("test.txt");
		DataInputStream dis = new DataInputStream(instream);
		double d = dis.readDouble();
		boolean b = dis.readBoolean();
		// 先写的先被读出来
		int i1 = dis.readInt();
		int i2 = dis.readInt();
		instream.close();
		dis.close();
		System.out.println(d);
		System.out.println(b);
		System.out.println(i1);
		System.out.println(i2);
	}
}