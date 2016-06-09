import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Spring4Study {

	/**
	 * PropertyPlaceholderConfigurer:
	 * 对spring配置文件引用的properties文件属性进行处理，如在properties文件中对用户名，密码等信息进行加密处理，然后在自定义的
	 * CustomPropertyPlaceholderConfigurer类中进行转换处理等。
	 * 
	 * 
	 * ClassPathXmlApplicationContext：加载spring配置文件，获得上下文
	 * 
	 * 
	 * ClassPathXmlApplicationContext是spring读取xml最常用的类。
	 * 而我们一般操作的是她的接口ApplicationContext。BeanFactory和ApplicationContext区别不大，
	 * BeanFactory不在自动 BeanPostProcessor 和自动 BeanFactoryPostProcessor
	 * 上注册。尽量用ApplicationContext。 ApplicationContext ctx = new
	 * ClassPathXmlApplicationContext("beans.xml"); UserService UserSrv =
	 * (UserService)ctx.getBean("userService"); ApplicationContext
	 * 改成BeanFactory，没有问题。AbstractApplicationContext有时也用这个。
	 * 除了也继承自BeanFactory和ApplicationContext外，
	 * 还有一个方法registerShutdownHook（），它会让你的Spring IoC容器恰当关闭。当然如果在web系的话，也会自动关闭。
	 * 
	 * 
	 * 一、简单的用ApplicationContext做测试的话,获得Spring中定义的Bean实例(对象).可以用:
	 * 
	 * ApplicationContext ac = new
	 * ClassPathXmlApplicationContext("applicationContext.xml"); RegisterDAO
	 * registerDAO = (RegisterDAO)ac.getBean("RegisterDAO");
	 * 
	 * 如果是两个以上: ApplicationContext ac = new ClassPathXmlApplicationContext(new
	 * String[]{"applicationContext.xml","dao.xml"});
	 * 
	 * 或者用通配符: ApplicationContext ac = new
	 * ClassPathXmlApplicationContext("classpath:/*.xml");
	 * 
	 * 
	 * 二、ClassPathXmlApplicationContext[只能读放在web-info/classes目录下的配置文件]
	 * 和FileSystemXmlApplicationContext的区别
	 * 
	 * classpath:前缀是不需要的,默认就是指项目的classpath路径下面; 如果要使用绝对路径,需要加上file:前缀表示这是绝对路径;
	 * 
	 * 对于FileSystemXmlApplicationContext: 默认表示的是两种:
	 * 
	 * 1.没有盘符的是项目工作路径,即项目的根目录; 2.有盘符表示的是文件绝对路径.
	 * 
	 * 如果要使用classpath路径,需要前缀classpath:
	 */
	protected static final Log log = LogFactory.getLog(Spring4Study.class);

	public static void main(String[] args) {
		// Resource resource = new ClassPathResource("appcontext.xml");
		// BeanFactory factory = new XmlBeanFactory(resource);

		// 用classpath路径
		// ApplicationContext factory = new
		// ClassPathXmlApplicationContext("classpath:appcontext.xml");
		// ApplicationContext factory = new
		// ClassPathXmlApplicationContext("appcontext.xml");

		// ClassPathXmlApplicationContext使用了file前缀是可以使用绝对路径的
		// ApplicationContext factory = new
		// ClassPathXmlApplicationContext("file:F:/workspace/example/src/appcontext.xml");

		// 用文件系统的路径,默认指项目的根路径
		// ApplicationContext factory = new
		// FileSystemXmlApplicationContext("src/appcontext.xml");
		// ApplicationContext factory = new
		// FileSystemXmlApplicationContext("webRoot/WEB-INF/appcontext.xml");

		// 使用了classpath:前缀,这样,FileSystemXmlApplicationContext也能够读取classpath下的相对路径
		// ApplicationContext factory = new
		// FileSystemXmlApplicationContext("classpath:appcontext.xml");
		// ApplicationContext factory = new
		// FileSystemXmlApplicationContext("file:F:/workspace/example/src/appcontext.xml");

		// 不加file前缀
		ApplicationContext factory = new FileSystemXmlApplicationContext("F:/workspace/example/src/appcontext.xml");
		// IHelloWorld hw = (IHelloWorld)factory.getBean("helloworldbean");
		// log.info(hw.getContent("luoshifei"));
	}
}
