package com.test.onetoone;

import java.io.Reader;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibeifeng.ibatis.onetoone.Passport;
import com.ibeifeng.ibatis.onetoone.Person;

public class TestPassport {
	
	SqlMapClient sqlMap;
	@Before
	public void setUp() throws Exception {
		Reader reader = Resources.getResourceAsReader ("orcsql-map-config.xml");
		sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
	}

	
    @Test
    @Ignore
    public void testInsert(){
    	Passport passport=new Passport();
    	passport.setExpiry(11111);
    	passport.setId(1);
    	passport.setSerial("C00001");
    	
    	try {
			sqlMap.insert("passport.createPassport", passport);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
    
    @Test
    public void testGetPassport() throws SQLException{
		Passport passport=(Passport)sqlMap.queryForObject("getPassport",4);
        System.out.println(passport.getExpiry());
        
        Person person=passport.getPerson();
        System.out.println(person.getPname());
    }

}
