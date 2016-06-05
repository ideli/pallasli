package monitor.bolt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import monitor.jdbc.DBManager;
import monitor.utils.PropertyUtil;

/**
 * 将数据写入到mysq的Bolt
 */
public class MysqlBolt extends BaseBasicBolt {

	private static final long serialVersionUID = 2747459780088405879L;

	private static final Logger log = LoggerFactory.getLogger(MysqlBolt.class);

	private String seprator;

	private String tableName;

	private Connection conn;

	private PreparedStatement pstmt;

	private String insertSql;

	public MysqlBolt(String tableName, String filterConfig) {
		this.tableName = tableName;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void prepare(Map stormConf, TopologyContext context) {
		seprator = PropertyUtil.getProperty("seprator");
		try {
			conn = DBManager.getConnection();
			String selectSql = "SELECT * FROM " + tableName;
			insertSql = "INSERT INTO " + tableName;
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			String fields = " (";
			String vals = " (";
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnName(i);
				if (i < columnCount) {
					fields += columnName + ", ";
					vals += "?, ";
				} else {
					fields += columnName + ")";
					vals += "?) ";
				}
			}
			insertSql += (fields + " VALUES " + vals);
		} catch (SQLException e) {
			log.error("获取元数据失败", e);
			throw new RuntimeException("获取元数据失败", e);
		}
	}

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String line = input.getString(0);
		String[] fields = line.split(seprator);
		try {
			pstmt = conn.prepareStatement(insertSql);
			for (int i = 0; i < fields.length; i++) {
				pstmt.setString(i + 1, fields[i]);
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.error("数据插入失败", e);
			throw new RuntimeException("数据插入失败", e);
		}
		collector.emit(new Values(line));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}

	@Override
	public void cleanup() {
		DBManager.closeConnection(conn);
	}
}
