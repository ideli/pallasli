package com.shineyue.htmldesign.contoller;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pallasli.database.SqlPropUtils;
import com.pallasli.sql.mybatis.MyBatisUtil;
import com.shineyue.htmldesign.mapper.UserMapper;
import com.shineyue.htmldesign.model.User;

@Controller
@EnableAutoConfiguration
public class SimpleController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public String hello() {

		SqlSession session = MyBatisUtil.getSession();
		/**
		 * 映射sql的标识字符串，
		 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
		 * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		 */
		// String statement1 =
		// "com.shineyue.htmldesign.mapper.UserMapper.create";// 映射sql的标识字符串
		//
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("sql", statement1);
		// session.update(statement1, map);

		List<String> sqlList = SqlPropUtils.loadSqlFile("init.sql");
		Connection c = session.getConnection();

		try {
			Statement st = c.createStatement();
			for (String sql : sqlList) {
				st.addBatch(sql);
				st.executeUpdate(sql);
				System.out.println(sql);
			}
			// st.executeBatch();
			st.close();

			// DbOper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		User user = session.getMapper(UserMapper.class).getUserById(1);

		// User entity = userMapper.getUser(1);
		return ("name：" + user.getName());
	}

	// @Autowired
	// private UserMapper userMapper;

}