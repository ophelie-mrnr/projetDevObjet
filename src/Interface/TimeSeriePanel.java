package Interface;
import java.util.Random;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class TimeSeriePanel{

	static Random random = new Random();
	
	ChartPanel cp ; 
	TimeSeriesCollection tsc;
	JFreeChart tc;
	
	public  TimeSeriePanel (String charTitle){
		tsc= new TimeSeriesCollection();
		
		tc = createTimeChart(tsc, charTitle);
		cp = new ChartPanel(tc);
		
	}
	
	public void addNewSeries(String title){
		tsc.addSeries(createTimeSeries(title));
	}
	
	public void clean(){
		tsc.removeAllSeries();
	}
	
	public static TimeSeries createTimeSeries(String title){
		if(title == null || title.length()==0)
			title = "sale "+randomValue(1, 999);
		TimeSeries timeSeries = new TimeSeries(title);
		
		for(int i = 0; i < randomValue(10, 30); i++)
			timeSeries.addOrUpdate(new Day(randomValue(1,28), randomValue(1, 12), randomValue(2011, 2014)), randomValue(1, 999));
		
		return timeSeries ;
	}
	
	public static JFreeChart createTimeChart(TimeSeriesCollection dataset, String title){
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				title,
				"Date",
				"Sales",
				dataset);
		
		return chart;
		
	}
	
	private static int randomValue(int min, int max){
		int randV = random.nextInt(max - min +1)+min;
		return (randV > 0) ? randV : 1; 
	}
}
