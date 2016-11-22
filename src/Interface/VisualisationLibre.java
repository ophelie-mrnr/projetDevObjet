/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.data.time.TimeSeries;

import DAO.MaConnexion;



public class VisualisationLibre extends javax.swing.JPanel {
	
	private static final Logger LOGGER = Logger.getLogger("myLogger");

	// Variables declaration
		private javax.swing.JButton enregistrer;

		private static javax.swing.JPanel jPanelMain;

		private static javax.swing.JPanel jPanelPieChart;
		private static javax.swing.JPanel jPanelTableau;


		private static javax.swing.JTable jTable2;
		private static javax.swing.JTextArea texteRequete;

		public static  String[][] tabstring;
		public static String [] titre;

		// variables pour le graphe
		private TimeSeries timeSeries;
		private static GraphePieChart graphe;
		private static ChartPanel chartPanel;

		private static boolean piechart =false;

		// End of variables declaration
	
	
    /**
     * Creates new form NewJPanel
     */
    public VisualisationLibre() {
        initComponents();
    }

    private void initComponents() {

    	enregistrer = new javax.swing.JButton();

		jPanelMain = new javax.swing.JPanel();
		jPanelPieChart = new javax.swing.JPanel();
		jPanelTableau = new javax.swing.JPanel();

		texteRequete = new javax.swing.JTextArea(5,20);

		jTable2 = new javax.swing.JTable();

		enregistrer.setText("Enregistrer");

		jTable2.setVisible(true);

		JPanel nord = new JPanel();
		nord.add(texteRequete);
		nord.add(enregistrer);
		this.add(BorderLayout.NORTH, nord);
		this.add(BorderLayout.CENTER,jPanelMain);

		this.add(jPanelMain);


		jPanelMain.add(BorderLayout.CENTER, jPanelPieChart);

		enregistrer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				enregistrerButtonActionPerformed(evt);
			}
		});

	}

	private void enregistrerButtonActionPerformed(java.awt.event.ActionEvent evt) {

		// Récupération de la requête de la zone de texte
		String requete = "";
		requete = texteRequete.getText();

		// Test si la requête est valide
		requeteValide(requete);

		// Création de la liste de listes contenant le résultat des requêtes
		List<List> resultatListeTest = new ArrayList<List>();
		resultatListeTest =  getResultat(requete);

		// Nous supprimons l'affichage précédent
		jPanelMain.removeAll();

		// Test si la deuxième liste (donc deuxième colonne du résultat) contient seulement des nombres
		try{
			double test=0;
			for(int i = 0 ; i < resultatListeTest.get(1).size() ; i++)
			{
				test = (double) resultatListeTest.get(1).get(i);
			}

			graphe = new GraphePieChart();
			chartPanel = graphe.getCp();


			jPanelPieChart.add(BorderLayout.CENTER,chartPanel);
			chartPanel.setVisible(true);
			jPanelPieChart.setVisible(true);
			jPanelMain.add(jPanelPieChart);

	}
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			// Sinon, on traite le cas de toutes les autres requêtes

			// Et on crée alors le tableau qui récupère les deux tableaux : tableau de données et tableau de titres

			 jTable2.setModel(new javax.swing.table.DefaultTableModel(
			            donneesJtable(requete),
			           titreJtable(requete)
			        ));

			 // Affichage du tableau avec les en tête

			jPanelTableau.add(jTable2.getTableHeader());
			jPanelMain.add(jPanelTableau);
		    jPanelTableau.add(new JScrollPane(jTable2));
		}

		this.updateUI();
	}

	public static String getRequete(){

		String requete = "";
		return requete = texteRequete.getText();
	}

	public boolean requeteValide(String requete){

		int taille_requete = requete.length();
		List<List> listeResultat = new ArrayList<List>();

		if(((requete.substring(0, 6).equals("SELECT")) ||requete.substring(0,6).equals("select")) && (requete.substring((requete.length())-1,(requete.length())).equals(";"))) {

			listeResultat = getResultat(requete);
			return true;

		}
		else{
			System.out.println("REQUETE SQL NON");
			return false;
		}

	}


	 public static void creationPieChart(){

		piechart = true;

		graphe = new GraphePieChart();
		chartPanel = graphe.getCp();

		jPanelPieChart.add(BorderLayout.CENTER,chartPanel);
		chartPanel.setVisible(true);
		chartPanel.setVisible(true);
		jPanelMain.add(jPanelPieChart);
		jPanelPieChart.setVisible(true);
	}


	public static List<List> getResultat(String query){
		ArrayList<String> resultatListeString = new ArrayList<String>();
		ArrayList<Double> resultatListeNombre = new ArrayList<Double>();
		List<List> resultatListe = new ArrayList<List>();

		String resultatString;
		double resultatNombre2;
		java.sql.Statement state;

		try{

			state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resultatRequete =state.executeQuery(query);

			try{
				while(resultatRequete.next()){

					resultatString = resultatRequete.getString(1);
					resultatNombre2 = resultatRequete.getDouble(2);

					int row = resultatRequete.getRow();

					resultatListeString.add(resultatString);
					resultatListeNombre.add(resultatNombre2);
					resultatListe.add(resultatListeString);
					resultatListe.add(resultatListeNombre);


				}

			}

			catch(Exception ev){
				LOGGER.log(Level.SEVERE, "Exception occur", ev);
			}

		}
		catch(Exception e){
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			System.out.println("TEST SQL PAS BON LOL ");
			JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
		}
		return resultatListe;

	}


	public static String[] titreJtable(String query){
		String resultatProductCode ="";
		String resultatProductName="";
		String resultatProductLine="";
		String resultatProductVendor="";
		String resultatDescription="";
		String resultatQuantity="";
		String resultatPrice="";
		String resultatMSRP="";

		java.sql.Statement state;

		try{
		state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet resPC =state.executeQuery(query);
		ResultSetMetaData metadata = resPC.getMetaData();

		while (resPC.next()){
			titre = new String[metadata.getColumnCount()];

			for (int i =1; i<=metadata.getColumnCount(); i++){


				if (metadata.getColumnName(i).equals("productCode")){
					resultatProductCode = resPC.getString("productCode");
					titre[i-1] = "productCode";
					System.out.println("test des titres");

				}
				if (metadata.getColumnName(i).equals("productName")){
					resultatProductName = resPC.getString("productName");
					titre[i-1] = "productName";
				}
				if (metadata.getColumnName(i).equals("productLine")){
					resultatProductLine = resPC.getString("productLine");
					titre[i-1] = "productLine";
				}

				if (metadata.getColumnName(i).equals("productVendor")){
					resultatProductVendor = resPC.getString("productVendor");
					titre[i-1] = "productVendor";
				}
				if (metadata.getColumnName(i).equals("productDescription")){
					resultatDescription = resPC.getString("productDescription");
					titre[i-1] = "productDescription";
				}
				if (metadata.getColumnName(i).equals("quantityInStock")){
					resultatQuantity = resPC.getString("quantityInStock");
					titre[i-1] = "quantityInStock";
				}
				if (metadata.getColumnName(i).equals("buyPrice")){
					resultatPrice = resPC.getString("buyPrice");
					titre[i-1] ="buyPrice";
				}
				if (metadata.getColumnName(i).equals("MSRP")){
					resultatMSRP = resPC.getString("MSRP");
					titre[i-1] = "MSRP";
				}

					System.out.println("affichage du tableau titre " + titre[i-1]);
			}
		}
	} catch (SQLException e) {
		LOGGER.log(Level.SEVERE, "Exception occur", e);
		e.printStackTrace();
	}
		return titre;
	}


	public static Object[][] donneesJtable(String query){

		java.sql.Statement state;

		try {

			state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resPC =state.executeQuery(query);
			ResultSetMetaData metadata = resPC.getMetaData();
			int nbClumn = metadata.getColumnCount();
			resPC.last();
			int nbLine = resPC.getRow();
			resPC.beforeFirst();
			Object donnees[][] = new Object[nbLine][nbClumn];
			int j=0,i=0;
			String[] titre = new String[nbClumn];
			while(resPC.next())
			{
				for(j = 1;j<nbClumn+1;j++)
				{
					System.out.println("j est égal à" + j);
					System.out.println("nombre de colonnes " + nbClumn);
					System.out.println("test de metadata :" + metadata.getColumnName(j));
					donnees[i][j-1] = resPC.getObject(metadata.getColumnName(j));

				}
				i++;
			}
			for(int k = 0;k<nbClumn;k++)
			{
				titre[k] = metadata.getColumnName(k+1);
			}


			return donnees;

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			e.printStackTrace();
		}
		return null;
	}

}