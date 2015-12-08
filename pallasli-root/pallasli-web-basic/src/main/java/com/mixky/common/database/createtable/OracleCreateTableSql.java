package com.mixky.common.database.createtable;

public class OracleCreateTableSql implements ICreateTableSql {

	@Override
	public String getSqlStr(Table table) {
		StringBuffer querystring = new StringBuffer();
		// if(table.getF_fields().size() > 0){
		// querystring.append("CREATE TABLE "+ table.getF_name().toUpperCase()
		// +" (");
		// for(int i = 0; i < table.getF_fields().size(); i++){
		// Field field = (Field) table.getF_fields().get(i);
		// querystring.append(field.getF_key().toUpperCase());
		// switch (field.getF_datatype_db()) {
		// case Field.DBT_LONG:
		// querystring.append(" NUMBER("+field.getF_length()+")");
		// break;
		// case Field.DBT_INT:
		// querystring.append(" NUMBER("+field.getF_length()+")");
		// break;
		// case Field.DBT_FLOAT:
		// querystring.append(" NUMBER("+field.getF_length()+")");
		// break;
		// case Field.DBT_CHAR:
		// querystring.append(" NVARCHAR2("+field.getF_length()+")");
		// break;
		// case Field.DBT_DATETIME:
		// querystring.append(" DATE");
		// break;
		// case Field.DBT_BLOB:
		// querystring.append(" BLOB");
		// break;
		// case Field.DBT_CLOB:
		// querystring.append(" CLOB");
		// break;
		// case Field.DBT_NONE:
		// querystring.append(" BLOB");
		// break;
		// }
		// if(field.getF_allowblank()){
		// querystring.append(",\n");
		// }else{
		// querystring.append(" not null,\n");
		// }
		// }
		// querystring.append("CONSTRAINT "+table.getF_name().toUpperCase()+"_PK UNIQUE(ID)");
		// querystring.append(")");
		// }
		return querystring.toString();
	}
}