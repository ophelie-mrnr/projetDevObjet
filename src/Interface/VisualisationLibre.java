/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

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
		texteRequete.setLineWrap(true); // textArea fixe

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
				LOGGER.log(Level.INFO, "Clique sur le bouton Enregistrer de la page Visualisation libre");
				enregistrerButtonActionPerformed(evt);
			}
		});

	}

	private void enregistrerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		jPanelPieChart.removeAll();
		jPanelTableau.removeAll();

		LOGGER.log(Level.INFO, "Le bouton Enregistrer de la page Visualisation libre fait son action");

		// R�cup�ration de la requ�te de la zone de texte
		String requete = "";
		requete = texteRequete.getText();

		// Test si la requ�te est valide
		requeteValide(requete);

		// Cr�ation de la liste de listes contenant le r�sultat des requ�tes
		List<List> resultatListeTest = new ArrayList<List>();
		resultatListeTest =  getResultat(requete);

		// Nous supprimons l'affichage pr�c�dent

		// Test si la deuxi�me liste (donc deuxi�me colonne du r�sultat) contient seulement des nombres
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

		}
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			// Sinon, on traite le cas de toutes les autres requ�tes

			// Et on cr�er alors le tableau qui r�cup�re les deux tableaux : tableau de donn�es et tableau de titres


			Object[][] donnees = donneesJtable(requete);
			Object[] titres = titreJtable(requete);

			jTable2.setModel(new javax.swing.table.DefaultTableModel(
					donnees, titres

					));


			// on parcours cellule par cellule
			for(int i = 0; i < donnees.length; i++){
				for(int j = 0; j < donnees[i].length; j++){
					Object testObject = donnees[i][j];
					if (testObject!=null) { // si la cellule n'est pas vide
						if (testObject instanceof ImageIcon) { // si la cellule contient une image
							jTable2.getColumnModel().getColumn(j).setCellRenderer(new ImageRenderer()); // on indique que le contenu de la cellule est une image
						}
					}
				}
			}
			// Affichage du tableau avec les en t�te

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
			LOGGER.log(Level.SEVERE, "Syntaxe de la requete SQL non valide");
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
			LOGGER.log(Level.SEVERE, "TEST SQL PAS BON");
			System.out.println("TEST SQL PAS BON");
			JOptionPane.showMessageDialog(null, "Entrez une bonne requ�te SQL, patate.");
		}
		return resultatListe;

	}


	public static String[] titreJtable(String query){
		java.sql.Statement state;
		try{
			state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet resPC =state.executeQuery(query);
			ResultSetMetaData metadata = resPC.getMetaData();

			while (resPC.next()){
				titre = new String[metadata.getColumnCount()];
				for (int i =1; i<=metadata.getColumnCount(); i++){
					titre[i-1]=metadata.getColumnName(i);
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
						System.out.println("j est �gal �" + j);
						System.out.println("nombre de colonnes " + nbClumn);
						System.out.println("test de metadata :" + metadata.getColumnName(j));
						if (metadata.getColumnName(j).equals("photo")) {
							System.out.println("toto");
							if (resPC.getObject(metadata.getColumnName(j))!=null) {
								System.out.println("titi");
								Blob blob = resPC.getBlob(j);

								//Transform Blob into ImageIcon
								BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());
								ImageIcon resultatPhoto = new ImageIcon(ImageIO.read(is));
								donnees[i][j-1] = resultatPhoto;
							}
						} else {
							donnees[i][j-1] = resPC.getObject(metadata.getColumnName(j));
						}

					}
					i++;
				}
				for(int k = 0;k<nbClumn;k++)
				{
					titre[k] = metadata.getColumnName(k+1);
				}


				return donnees;

			} catch (SQLException | IOException e) {
				LOGGER.log(Level.SEVERE, "Exception occur", e);
				e.printStackTrace();
			}
			return null;
		}

	}