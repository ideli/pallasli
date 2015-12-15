import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class TimeSeriesChart {
	public static void main(final String[] args) throws Exception {
		final TimeSeries series = new TimeSeries("Random Data");
		Second current = new Second();
		double value = 100.0;
		for (int i = 0; i < 4000; i++) {
			try {
				value = value + Math.random() - 0.5;
				series.add(current, new Double(value));
				current = (Second) current.next();
			} catch (SeriesException e) {
				System.err.println("Error adding to series");
			}
		}
		final XYDataset dataset = (XYDataset) new TimeSeriesCollection(series);
		JFreeChart timechart = ChartFactory.createTimeSeriesChart(
				"Computing Test", "Seconds", "Value", dataset, false, false,
				false);

		int width = 560; /* Width of the image */
		int height = 370; /* Height of the image */
		File timeChart = new File("TimeChart.jpeg");
		ChartUtilities.saveChartAsJPEG(timeChart, timechart, width, height);
	}
}