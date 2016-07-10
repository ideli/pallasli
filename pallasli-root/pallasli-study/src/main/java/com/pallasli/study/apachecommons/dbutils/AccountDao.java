package com.pallasli.study.apachecommons.dbutils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

/*account测试表
create table account(
    id int primary key auto_increment,
    name varchar(40),
    money float
)character set utf8 collate utf8_general_ci;

insert into account(name,money) values('A',1000);
insert into account(name,money) values('B',1000);
insert into account(name,money) values('C',1000);

*/

/**
 * @ClassName: AccountDao
 * @Description: 针对Account对象的CRUD
 * @author: 孤傲苍狼
 * @date: 2014-10-6 下午4:00:42
 *
 */
public class AccountDao {

	// 接收service层传递过来的Connection对象
	private Connection conn = null;

	public AccountDao(Connection conn) {
		this.conn = conn;
	}

	public AccountDao() {

	}

	/**
	 * @Method: update
	 * @Description:更新
	 * @Anthor:孤傲苍狼
	 *
	 * @param account
	 * @throws SQLException
	 */
	public void update(Account account) throws SQLException {

		QueryRunner qr = new QueryRunner();
		String sql = "update account set name=?,money=? where id=?";
		Object params[] = { account.getName(), account.getMoney(), account.getId() };
		// 使用service层传递过来的Connection对象操作数据库
		qr.update(conn, sql, params);

	}

	/**
	 * @Method: find
	 * @Description:查找
	 * @Anthor:孤傲苍狼
	 *
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Account find(int id) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "select * from account where id=?";
		// 使用service层传递过来的Connection对象操作数据库
		return (Account) qr.query(conn, sql, id, new BeanHandler(Account.class));
	}
}