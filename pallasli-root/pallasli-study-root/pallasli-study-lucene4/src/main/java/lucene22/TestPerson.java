package com.test.onetoone;

import java.io.Reader;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibeifeng.ibatis.onetoone.Passport;
import com.ibeifeng.ibatis.onetoone.Person;

public class TestPerson {
	
	SqlMapClient sqlMap;
	@Before
	public void setUp() throws Exception {
		Reader reader = Resources.getResourceAsReader ("orcsql-map-config.xml");
		sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
	}

	
    @Test
//    @Ignore
    public void testInsert(){
    	Person person=new Person();
    	person.setPname("zhangsan4");
    
    	person.setAge(24);
    	
    	try {
			sqlMap.insert("person.createPerson", person);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Passport passport=new Passport();
    	passport.setExpiry(11111);
    	passport.setId(person.getId());
    	passport.setSerial("C00004");
    	
    	try {
			sqlMap.insert("passport.createPassport", passport);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(person.getId());
    }
    
    @Test
    public void testGetPassport() throws SQLException{
    	Person person=(Person)sqlMap.queryForObject("getperson",4);
        System.out.println(person.getPname());
        Passport passport=person.getPassport();
        System.out.println(passport.getExpiry());
    }


}
