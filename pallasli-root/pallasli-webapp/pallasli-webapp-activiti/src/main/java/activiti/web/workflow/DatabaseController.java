package activiti.web.workflow;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.pallas.activiti.model.Menu;

@Controller
@RequestMapping(value = "/workflow/database")
public class DatabaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	IdentityService identityService;
	private SqlSessionFactory getSessionFactory() {
		SqlSessionFactory sessionFactory = null;
		String resource = "configuration.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessionFactory;
	}
	@RequestMapping(value = "listActivitiTables")
	public void listActivitiTables(HttpServletResponse response) {
		

		List<Menu> list = new ArrayList<Menu>();
		SqlSession sqlSession = getSessionFactory().openSession();
		String sql = "show tables";
		try {
			ResultSet rs = sqlSession.getConnection().createStatement()
					.executeQuery(sql);
			long i=1;
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setId(i++);
				menu.setMenuKey(rs.getString("Tables_in_activiti"));
				menu.setMenuName(rs.getString("Tables_in_activiti"));
				menu.setMenuCaption(rs.getString("Tables_in_activiti"));
				list.add(menu);
			}
			response.getWriter().write(new Gson().toJsonTree(list).toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
