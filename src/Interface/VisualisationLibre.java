/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.TimeSeries;

import DAO.MaConnexion;


public class VisualisationLibre extends javax.swing.JPanel {

	 // Variables declaration
    private javax.swing.JButton enregistrer;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea texteRequete;     
	
	// variables pour le graphe
	private TimeSeries timeSeries;
	private GraphePieChart graphe; 
	private ChartPanel chartPanel;
			
    // End of variables declaration
	
	
    /**
     * Creates new form NewJPanel
     */
    public VisualisationLibre() {
        initComponents();
    }

    private void initComponents() {

        enregistrer = new javax.swing.JButton();

        texteRequete = new javax.swing.JTextArea(5,20);
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();

        enregistrer.setText("jButton1");

        graphe = new GraphePieChart();        
		chartPanel = graphe.getCp();

		JPanel nord = new JPanel();
		nord.add(texteRequete);
		nord.add(enregistrer);
		this.add(BorderLayout.NORTH, nord);	
		
		jPanel1.add(BorderLayout.CENTER,chartPanel);
		chartPanel.setVisible(true);

		this.add(BorderLayout.CENTER, jPanel1);
		
		
		jPanel1.add(BorderLayout.CENTER,chartPanel);
		chartPanel.setVisible(true);
		this.add(jPanel1);
		
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        enregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enregistrerButtonActionPerformed(evt);
            }
        });

}


    private void enregistrerButtonActionPerformed(java.awt.event.ActionEvent evt) {

          String requete = "";
        requete = texteRequete.getText();
        requeteValide(requete);

        System.out.println("La requete est :" + requete);

    }

    public boolean requeteValide(String requete){


         int taille_requete = requete.length();
         String requete_from ="";
         ArrayList<String> listeResultat = new ArrayList<String>();


         if(((requete.substring(0, 6).equals("SELECT")) ||requete.substring(0,6).equals("select")) && (requete.substring((requete.length())-1,(requete.length())).equals(";"))) {
        	listeResultat = getResultat();
        	System.out.println("Test est ce qu'il marche");
        	System.out.println("Taille : " + listeResultat.size());
        	for(int i=0; i<listeResultat.size();i++){
        		System.out.println("Affichage du tableau de resultat : ");
        		System.out.println(listeResultat.get(i).toString());

        	}

        	 System.out.println("REQUETE SQL OK");
             return true;

         }
         else{
             System.out.println("REQUETE SQL NON");
             return false;
         }


    }


    public ArrayList<String> getResultat()
    {

    	// SELECT ProductName, buyPrice FROM Products WHERE ProductCode = 'S10_1949

  		String query = "SELECT ProductName, buyPrice FROM Products WHERE ProductCode = 'S10_1949';";
  		String resultatString ="";
  		String resultatString2 ="";
  		String resultatStringFinal ="";
  		int i=1;
        ArrayList<String> listeResultat = new ArrayList<String>();

          try
          {
        	java.sql.Statement state;
          	state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultatRequete =state.executeQuery(query);
            resultatString = resultatRequete.getString(1);
            resultatString2 = resultatRequete.getString(2);
            while(resultatRequete.next()){

            	listeResultat.add(i,resultatString2);

            }

          //  resultatRequete.next();

          //  resultatStringFinal = " " +resultatString + ", " + resultatString2;
            resultatRequete.close();
            state.close();
            System.out.println("Coucou c'est cool");
          }
          catch(Exception e){ resultatStringFinal = "Introuvable";
          System.out.println("non c'est pas cool");}

          return listeResultat;
    }
}
