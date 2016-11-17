package Interface;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class GrapheVisualisationVentes {

	ChartPanel chartPanel ; 
	TimeSeriesCollection timeSeriesCollection;
	JFreeChart jFreeChart;
	

	public  GrapheVisualisationVentes (String charTitle){
		timeSeriesCollection= new TimeSeriesCollection();

		jFreeChart = createTimeChart(timeSeriesCollection, charTitle);
		chartPanel = new ChartPanel(jFreeChart);

	}

	public void addNewSeries(String title){
		timeSeriesCollection.addSeries(createTimeSeries(title));
	}

	public void clean(){
		timeSeriesCollection.removeAllSeries();
	}

	public static TimeSeries createTimeSeries(String title){
		if(title == null || title.length()==0)
				title = "Ventes";
			TimeSeries timeSeries = new TimeSeries(title);

			//dataset.addSeries(timeSeries);
			return timeSeries ;
	}

	public static JFreeChart createTimeChart(TimeSeriesCollection dataset, String title){
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				title,
				"Dates",
				"Ventes",
				dataset);

		return chart;

	}

	private static int remplissageValeurs(){
		/*int valeur = ;
		return valeur;
				*/
		return 0;
	}
}