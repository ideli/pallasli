import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart_DB {
	public static void main(String[] args) throws Exception {
		// String mobilebrands[] = { "IPhone 5s", "SamSung Grand", "MotoG",
		// "Nokia Lumia" };

		/* Create MySQL Database Connection */
		Class.forName("com.mysql.jdbc.Driver");
		Connection connect = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jf_testdb", "root", "root123");

		Statement statement = connect.createStatement();
		ResultSet resultSet = statement
				.executeQuery("select * from dataset_tb");
		DefaultPieDataset dataset = new DefaultPieDataset();
		while (resultSet.next()) {
			dataset.setValue(resultSet.getString("brandname"),
					Double.parseDouble(resultSet.getString("datavalue")));
		}
		JFreeChart chart = ChartFactory.createPieChart("Mobile Sales", // chart
																		// title
				dataset, // data
				true, // include legend
				true, false);

		int width = 560; /* Width of the image */
		int height = 370; /* Height of the image */
		File pieChart = new File("Pie_Chart.jpeg");
		ChartUtilities.saveChartAsJPEG(pieChart, chart, width, height);
	}
}