package test.hello;


import java.io.Reader;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibeifeng.ibatis.hello.User;

public class TestHelloIbatis {
    
	SqlMapClient sqlMap;
	@Before
	public void setUp() throws Exception {
		Reader reader = Resources.getResourceAsReader ("sql-map-config.xml");
		sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
	}

//	
    @Test
    @Ignore
    public void testInsert(){
    	User user=new User();
    	user.setName("zhangsan2");
    	user.setEmail("zs@163.com");
    	user.setBirthday(new Date());
    	try {
			sqlMap.insert("createUser", user);
			System.out.println("0000000");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    
    
    @Test
    @Ignore
    public void testGetUser() throws SQLException{
 
    	User user=(User)sqlMap.queryForObject("getUser", "zhangsan1");
    	System.out.println(user.getId()+" "+user.getEmail());
     
    }
    
    
    @Test
    @Ignore
    public void testUpdateUser() throws SQLException{
       	User user=new User();
       	user.setId(1);
    	user.setName("zhangsan111");
    	user.setEmail("zs11@163.com");
    	sqlMap.update("updateUser", user);
    	System.out.println(user.getId()+" "+user.getEmail());
     
    }
    
    
    @Test
//    @Ignore
    public void testDeleteUser() throws SQLException{
       
    	sqlMap.update("deleteUser", 1);
        
    }
    
    
    @After

	public void tearDown() throws Exception {
		sqlMap=null;
	}
}
