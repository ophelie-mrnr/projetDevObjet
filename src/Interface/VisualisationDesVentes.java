package Interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;

import DAO.MaConnexion;

public class VisualisationDesVentes extends javax.swing.JPanel implements ActionListener{

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
    private javax.swing.JButton enregistrer;
    
    // variables pour le graphe
    private javax.swing.JPanel jPanelGraphe;
    private javax.swing.JPanel actionPanel; 
   	private javax.swing.JPanel titlePanel; 
   	private javax.swing.JPanel buttonPanel; 
   	private ChartPanel chartPanel;
   	private GrapheVisualisationVentes tsp; 
    
    // End of variables declaration  
    
    /**
     * Creates new form NewJPanel
     */
    public VisualisationDesVentes() {
        initComponents();
        initGrapheVisualisationVentes();
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
        jPanelGraphe = new javax.swing.JPanel();
        

        jLabel1.setText("Pays");

        jLabel2.setText("Date de début");

        jLabel3.setText("Date de fin");

        DateDebutTextField.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        DateDebutTextField.setText("Rentrez date de début");

        DateFinTextField.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        DateFinTextField.setText("Rentrez date de fin");
        DateFinTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DateFinTextFieldActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 738, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        validerButton.setText("Valider");
        validerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	actionPerformed(evt);
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateDebutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DateFinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(validerButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(319, 319, 319)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(DateDebutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateFinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validerButton))
                .addGap(18, 18, 18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>    
    
    private void initGrapheVisualisationVentes() { 
    	/*GrapheVisualisationVentes testGraphe = new GrapheVisualisationVentes("nouveau graphe"); 
        ChartPanel graphic = testGraphe.getChartPanel(); 
       
        //jPanelGraphe.setLayout(new java.awt.BorderLayout()); 
        //jPanelGraphe.add(graphic1, BorderLayout.CENTER); 
      
        graphic.validate();
    	jPanelGraphe.add(graphic);  	
    	 
      	jPanelGraphe.setVisible(true);
      	graphic.setVisible(true);*/
    	
    	tsp = new GrapheVisualisationVentes("Ventes :");
		chartPanel = tsp.cp;
		
		jPanelGraphe.add(BorderLayout.CENTER, chartPanel);
		jPanelGraphe.add(BorderLayout.SOUTH, actionPanel);
    }    
        

    private void DateFinTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                 
       
    }                                                             
    
	
	public void actionPerformed(ActionEvent evt) {
		// A FAIRE   	
    	/*
    	 * if (Country) 
    	 */   	
    	//jPanelGraphe.setVisible(true);
    	jPanelGraphe.validate(); 
    	System.out.println("clique sur bouton validerButton.");
		
	}  
	




private void ValiderActionPerformed(java.awt.event.ActionEvent evt) {                                        
		// TODO add your handling code here:
		JButton enregistrer = new JButton("Enregistrer"); 
		jPanel1.setSize(700,700);
		jPanel1.add(BorderLayout.SOUTH,enregistrer);

		
		// A FAIRE   	
				/*
				 * if (Country) 
				 */   	
				//JFrame coucou = new JFrame();
				//jPanel1.add(coucou);
				jPanel1.setVisible(true);
				System.out.println("clique sur bouton validerButton.");


		/*JFrame nouvelle = new JFrame();
    	JPanel nouveau = new JPanel();
    	nouveau.add(chartPanel);
    	nouvelle.setSize(700, 700);
    	nouvelle.getRootPane().getContentPane().add(BorderLayout.NORTH,nouveau);
	    nouvelle.setVisible(true);

	    enregistrer.setVisible(true);*/

		// JFileChooser save=new JFileChooser();
		// save.setSelectedFile(new File("fichier.xls"));
		//    int retour=save.showSaveDialog(tableau.table.getParent());

		// nouvelle.add(save);

		/*	JPanel nouveau = new JPanel();
    	nouveau.setSize(new Dimension(700,700));
    	nouveau.add(chartPanel);
    //	this.setSize(800, 800);
    //	super.setSize(1000,1000);
    	this.getRootPane().getContentPane().add(BorderLayout.SOUTH, chartPanel);
    	this.setVisible(true);
    	//jPanel1.setSize(800, 800);
    	//jPanel1.setVisible(true);
    	/*nouvelle.add(nouveau);
    	nouvelle.setVisible(true);*/

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



	
	
}