package com.test.onetomany;

import java.io.Reader;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibeifeng.ibatis.batch.Customer;
import com.ibeifeng.ibatis.onetomany.Orders;

public class TestOnetoMany {
	SqlMapClient sqlMap;
	@Before
	public void setUp() throws Exception {
		Reader reader = Resources.getResourceAsReader ("orcsql-map-config.xml");
		sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
	}

	
    @Test
    @Ignore
    public void testInsertOrders(){
    	Orders orders=new Orders();
    	orders.setCode("O00001");
    	orders.setId(1);
    	orders.setCustomerid(14);
    	
    	try {
			sqlMap.insert("orders.createOrders", orders);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
    
    @Test
//    @Ignore
    public void testGetOrders(){
    	try {
			Orders orders=(Orders)sqlMap.queryForObject("getOrders", 2);
			System.out.println(orders.getCode()+"---"+orders.getCustomerid());
			Customer customer=orders.getCustomer();
			System.out.println(customer.getCname());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    @Ignore
    public void testGetCustomer(){
    	try {
    		Customer customer=(Customer)sqlMap.queryForObject("getCustomer", 14);
			System.out.println(customer.getCname());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    @Ignore
    public void testInsertOntoMany(){
    	Customer customer=new Customer();
    	customer.setAddress("shanghai4");
    	customer.setCname("beifeng4");
    	customer.setPostcode("400004");
    	customer.setSex("ma");
    	try {
			sqlMap.insert("customer.createCustomer", customer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int cid=customer.getId();
		System.out.println("id--->"+cid);
		Orders orders=new Orders();
    	orders.setCode("O00001");
//    	orders.setId(1);
    	orders.setCustomerid(cid);
    	
    	
		Orders orders2=new Orders();
    	orders2.setCode("O00002");
//    	orders.setId(1);
    	orders2.setCustomerid(cid);
    	
    	try {
			sqlMap.insert("orders.createOrders", orders);
			sqlMap.insert("orders.createOrders", orders2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
    
    

}
