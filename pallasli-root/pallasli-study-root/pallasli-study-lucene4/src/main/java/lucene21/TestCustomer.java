package com.test;

import java.io.Reader;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibeifeng.ibatis.batch.Customer;
import com.ibeifeng.ibatis.hello.User;

public class TestCustomer {
	
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
    	Customer customer=new Customer();
    	customer.setAddress("shanghai");
    	customer.setCname("beifeng");
    	customer.setPostcode("200002");
    	customer.setSex("ma");
    	try {
			sqlMap.insert("customer.createCustomer", customer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }	
	    @Test
	    @Ignore
	    public void testLike(){
	    	Customer customer=new Customer();
	    	List list=null;
	    	try {
			 list=sqlMap.queryForList("getCustomerLike", "hai3");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(list.size());
	    }
		
        @Test
        public void testBatch() throws SQLException{
        	
        	sqlMap.startTransaction();
        	sqlMap.startBatch();
        	User user=new User();
//        	user.setId(1);
        	user.setName("zhangsan8");
        	user.setEmail("zs8@163.com");
        	user.setBirthday(new Date());
        	try {
    			sqlMap.insert("createUser", user);
     		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
    		
    		Customer customer=new Customer();
        	customer.setAddress("shanghai");
        	customer.setCname("beifeng");
        	customer.setPostcode("200002");
        	customer.setSex("ma");
        	try {
    			sqlMap.insert("customer.createCustomer", customer);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		sqlMap.executeBatch();
    		sqlMap.commitTransaction();
        }
    

}
