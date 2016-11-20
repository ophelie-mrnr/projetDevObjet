package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import DAO.*;
import POJO.CreationBDD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualisationDesVentes extends javax.swing.JPanel{

	// Variables declaration                     
	private javax.swing.JTextField DateDebutTextField;
	private javax.swing.JTextField DateFinTextField;
	private javax.swing.JButton validerButton;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;

	private String[] tab;

	// variables pour le graphe
	private TimeSeries timeSeries;
	private TimeSeriePanel tsp; 
	private ChartPanel chartPanel;

	// End of variables declaration  

	/**
	 * Creates new form NewJPanel
	 */
	public VisualisationDesVentes() {
		initComponents();
	}


	@SuppressWarnings("unchecked")
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		DateDebutTextField = new javax.swing.JTextField();
		DateFinTextField = new javax.swing.JTextField();
		jComboBox1 = new javax.swing.JComboBox();
		jPanel1 = new javax.swing.JPanel();
		validerButton = new javax.swing.JButton();
		

		tsp = new TimeSeriePanel("VENTES :");
		chartPanel = tsp.cp;

		jLabel1.setText("Pays");

		jLabel2.setText("Date de debut");

		jLabel3.setText("Date de fin");

		DateDebutTextField.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
		DateDebutTextField.setText("aaaa-mm-jj");	
		DateDebutTextField.setPreferredSize( new Dimension( 100, 24 ) );

		DateFinTextField.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
		DateFinTextField.setText("aaaa-mm-jj");
		DateFinTextField.setPreferredSize( new Dimension( 100, 24 ) );

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(creationComboBox()));

		JPanel nord = new JPanel();
		nord.add(jLabel1);
		nord.add(jComboBox1);
		nord.add(jLabel2);
		nord.add(DateDebutTextField);
		nord.add(jLabel3);
		nord.add(DateFinTextField);
		nord.add(validerButton);

		this.add(BorderLayout.NORTH, nord);
		JButton enregistrer = new JButton("Enregistrer");
		JButton annuler = new JButton("Annuler");
		
		jPanel1.add(BorderLayout.CENTER,chartPanel);
		chartPanel.setVisible(true);

		this.add(BorderLayout.CENTER, jPanel1);
		this.add(BorderLayout.SOUTH, enregistrer);
		this.add(BorderLayout.SOUTH, annuler);

		validerButton.setText("Valider");
		validerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ValiderActionPerformed(evt);
			}
		});
		
		annuler.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				AnnulerActionPerformed(evt);
			}
		});
	}// </editor-fold>                       

	private void ValiderActionPerformed(java.awt.event.ActionEvent evt) {                                        
		// TODO add your handling code here:
		donneesChartPanel();
		chartPanel.setVisible(true);

	} 

	private void AnnulerActionPerformed(java.awt.event.ActionEvent evt) {                                        
		// TODO add your handling code here:
		tsp.clean();
		chartPanel.setVisible(true);

	} 

	public String[] creationComboBox(){

		java.sql.Statement state;

		try {
			// Liste des product Code
			state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT DISTINCT country FROM customers;";
			ResultSet res =state.executeQuery(query);	

			ArrayList<String> liste = new ArrayList<String>(); 


			while (res.next()){
				String resultat = res.getString("country");
				liste.add(resultat);
			}	
			tab = new String[liste.size()+1];
			tab[0]= "All";
			for(int i = 0; i < liste.size(); i++){
				tab[i+1] = liste.get(i);
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tab;
	}
	
	public void donneesChartPanel(){
		String dateDeb =  DateDebutTextField.getText().trim();
		String dateFin = DateFinTextField.getText().trim();
		String pays = jComboBox1.getSelectedItem().toString();
		
		// remplissage du graphique
		tsp.addNewSeries("ventes", dateDeb, dateFin, pays);
	}
}

