package com.pallas.designer.dao.generic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import lyt.designer.dao.BaseConn;

public class BaseDao {
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;

	public BaseDao() {
		BaseConn.getDesignCon("designer");
		BaseConn.getKnowledgeCon();
	}
}
