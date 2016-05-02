import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		// 创建Spring容器

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring.xml");
		// 获取Spring 容器中的所有Bean实例的名
		System.out.println("---------------------"
				+ java.util.Arrays.toString(ctx.getBeanDefinitionNames()));
	}
}
