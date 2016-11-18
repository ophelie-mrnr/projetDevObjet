package Interface;

import java.util.Arrays;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.DefaultCategoryItemRenderer;
import org.jfree.data.statistics.DefaultMultiValueCategoryDataset;
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

			TimeSeriesCollection dataset;
			
			/*Pour les données du jfreeChart (jFreeChart) de visualisation des ventes :
				De date de début à date de fin :

				 Pour une certaine date requiredDate de orders,
				  On stocke les n° de orders (OrdersNumber) de cette date dans un tableau de string par   exemple
				   Pour chaque n° de orders (on parcours le tableau)
				    On calcul quantité * prix
				    On stocke ca dans u int (on fait par exemple i+=)

				   Pour une date on aura donc 1 total de ventes
				 On incrément la date pour faire la meme chose à la date suivante. jusqu’a ce qu’on arrive à la date de fin.
			 */
			//dataset.addSeries(timeSeries);
			return timeSeries ;
		}

protected ChartPanel createChart(Map<String, Map<Integer, Integer>> data) {
	// création du graphique vide
	JFreeChart chart = ChartFactory.createLineChart("Titre", "x", "y", null, PlotOrientation.VERTICAL, true, false, false);
 
	// insertion des données
	int index = 0; // identifie les différentes courbes
	for (String curveTitle : data.keySet()) {
		// on récupère les données d'une courbe
		DefaultMultiValueCategoryDataset dataset = new DefaultMultiValueCategoryDataset();
		Map<Integer, Integer> instanceData = data.get(curveTitle);
		for (Map.Entry<Integer, Integer> entry : instanceData.entrySet()) {
			// insertion du point, notez la nécessité de
			// faire une liste, ce qui n'est pas naturel
			int x = entry.getKey();
			int y = entry.getValue();
			dataset.add(Arrays.asList(y), curveTitle, x);
		}
 
		// on insère les données dans le graphique
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setDataset(index, dataset);
 
		// création d'un renderer pour chaque courbe
		// sinon seule la première est affichée
		CategoryItemRenderer renderer = new DefaultCategoryItemRenderer();
		plot.setRenderer(index, renderer);
 
		index++;
	}
	return new ChartPanel(chart);
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