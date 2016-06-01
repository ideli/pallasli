package com.test;



import java.io.Reader;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibeifeng.ibatis.hello.User;
import com.ibeifeng.ibatis.hello.UserCount;

public class TestHelloIbatis {
    
	SqlMapClient sqlMap;
	@Before
	public void setUp() throws Exception {
		Reader reader = Resources.getResourceAsReader ("orcsql-map-config.xml");
		sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
	}

//	
    @Test
    @Ignore
    public void testInsert(){
    	User user=new User();
//    	user.setId(1);
    	user.setName("zhangsan8");
    	user.setEmail("zs8@163.com");
    	user.setBirthday("12/30/2000 13:43");
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
    	
//    	User user=(User)sqlMap.queryForObject("getUser", "zhangsan6");
    	User user=(User)sqlMap.queryForObject("getUserbyId",2);
      	System.out.println(user.getId()+" "+user.getEmail()+"---"+user.getBirthday());
     
    }
    
 
    
    @Test
    @Ignore
    public void testUpdateUser() throws SQLException{
       	User user=new User();
       	user.setId(5);
    	user.setName("zhangsan555");
    	user.setEmail("zs555@163.com");
    	sqlMap.update("updateUser", user);
    	System.out.println(user.getId()+" "+user.getEmail());
     
    }
    
    
    @Test
//    @Ignore
    public void testUserList() throws SQLException{
        
    	int [] ages={22,36,54};
    	Map map=new HashMap();
    	map.put("name", "zhangsan6");
    	map.put("age", 22);
    	map.put("ages", ages);
    	List list=sqlMap.queryForList("getUserForDyn",map);
     	System.out.println(list.size());
    	for(int i=0;i<list.size();i++){
    		User user=(User)list.get(i);
    	 	System.out.println(user.getId()+"---"+user.getName());
    	}
   
        
    }
    
    @Test
    @Ignore
    public void testUserCount() throws SQLException{
       
    	UserCount num=(UserCount)sqlMap.queryForObject("getUserCount");
        System.out.println(num.getCountnum());
    }
    
    
    @Test
    @Ignore
    public void testDeleteUser() throws SQLException{
       
    	sqlMap.update("deleteUser", 5);
        
    }
    
    
    @After

	public void tearDown() throws Exception {
		sqlMap=null;
	}
}
