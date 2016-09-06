package mybatis.generator;

import com.pallasli.sql.mybatis.MyBatisGeneratorTool;

/**
 * 动态生成配置文件和类
 * 
 * @author Administrator
 * 
 */
public class Generate {
	public static void main(String[] args) {
		try {
			MyBatisGeneratorTool.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
