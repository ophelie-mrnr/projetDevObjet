package Interface;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


public class GraphePieChart  {

	private ChartPanel cp ; 
	
	public ChartPanel getCp() {
		return cp;
	}

	public void setCp(ChartPanel cp) {
		this.cp = cp;
	}

	public JFreeChart getPd() {
		return pd;
	}

	public void setPd(JFreeChart pd) {
		this.pd = pd;
	}

	private JFreeChart pd;	
    
    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private static PieDataset createDataset() {
    	List<List> resultatListe = new ArrayList<List>();
    	String query = VisualisationLibre.getRequete();
    //	resultatListe = VisualisationLibre.getResultat("SELECT productName, buyPrice FROM Products WHERE productCode = 'S10_1678';");
    	resultatListe = VisualisationLibre.getResultat(query);
    	ArrayList<String> resultatListeString = new ArrayList<String>();
    	ArrayList<Double> resultatListeNombre = new ArrayList<Double>();


        DefaultPieDataset dataset = new DefaultPieDataset();
        System.out.println("Test du piechart :");

        for(int j=0; j<resultatListe.size();j++){
        	resultatListeString.addAll(resultatListe.get(0));
        }

        for(int c=0; c<resultatListe.size();c++){
        	resultatListeNombre.addAll(resultatListe.get(1));
        }
        
        for(int i=0; i<resultatListe.size();i++){
        	dataset.setValue(resultatListeString.get(i), new Double(resultatListeNombre.get(i)));                    
       }
        return dataset;
    }


	/**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
            "Pie Chart Demo 1",  // chart title
            dataset,             // data
            true,               // include legend
            true,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
    }

    public GraphePieChart() {
    	JFreeChart chart = createChart(createDataset());
		cp = new ChartPanel(chart);
		
    }

}
