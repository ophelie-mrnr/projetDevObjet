/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import DAO.DAOProducts;
import DAO.MaConnexion;
import POJO.Products;


public class GestionsProduits extends javax.swing.JPanel {

	private static final Logger LOGGER = Logger.getLogger("myLogger");
	
	 // Variables declaration 
	
    private javax.swing.JButton montrerButton;
    private javax.swing.JButton enregistrerButton;
    private javax.swing.JButton annulerButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel MSRPREs;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel CodeProduitResultat;
    private javax.swing.JLabel ProductLinesDesRes;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel fournisseurRes;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea DescriptionRes;
    private javax.swing.JTextField productNameResultat;
    private javax.swing.JTextField PriceRes;
    private javax.swing.JTextField QuantityInStockRes;
    
    public String[] tableauProductCode;
	public String[] tableauProductName;
	public String[] tableauQuantityInStock;
	public String[] tableauBuyPrice;
	public  String[][] tabstring;

    // End of variables declaration 
	
	
    /**
     * Creates new form NewJPanel
     */
    public GestionsProduits() {
        initComponents();
    }
                        
    private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        montrerButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        enregistrerButton = new javax.swing.JButton();
        annulerButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        DescriptionRes = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        fournisseurRes = new javax.swing.JLabel();
        QuantityInStockRes = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CodeProduitResultat = new javax.swing.JLabel();
        PriceRes = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        productNameResultat = new javax.swing.JTextField();
        ProductLinesDesRes = new javax.swing.JLabel();
        MSRPREs = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        
        
        	jTable1.addMouseListener(new MouseAdapter(){
        		public void mouseClicked(MouseEvent me) { 
        			LOGGER.log(Level.INFO, "Clique sur un objet du tableau");
        			afficherProduit();
        		}
        	}   
        		);
        	
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            creationJtable(),
            new String [] {"Code du produit", "Nom du produit", "Quantit� en stock", "Prix d'achat"}
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        montrerButton.setText("Montrer");
        montrerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	LOGGER.log(Level.INFO, "Clique sur le bouton Montrer");
            	montrerButtonActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(153, 0, 102));
        jLabel14.setToolTipText("");

        jLabel13.setText("Photo");

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	LOGGER.log(Level.INFO, "Clique sur le bouton Annuler");
                annulerButtonActionPerformed(evt);
            }
        });

        enregistrerButton.setText("Enregistrer");
        enregistrerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	LOGGER.log(Level.INFO, "Clique sur le bouton Enregistrer");
                enregistrerButtonActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(enregistrerButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(annulerButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel13))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(enregistrerButton)
                        .addGap(18, 18, 18)
                        .addComponent(annulerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(annulerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enregistrerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        DescriptionRes.setColumns(20);
        DescriptionRes.setRows(5);
        jScrollPane2.setViewportView(DescriptionRes);

        jLabel12.setText("Description :");

        jLabel1.setText("Code du produit");
        
		PriceRes.setPreferredSize( new Dimension( 100, 24 ) );
        QuantityInStockRes.setPreferredSize( new Dimension( 100, 24 ) );
        productNameResultat.setPreferredSize( new Dimension( 200, 24 ) );

        jLabel3.setText("Quantit� en stock :");

        jLabel5.setText("ProductLines.TextDescription :");

        jLabel2.setText("Nom du produit");

        jLabel10.setText("MSRP :");

        jLabel4.setText("Prix d'achat : ");

        jLabel8.setText("Fournisseur :");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setVisible(false);
        montrerButton.setVisible(false);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
                jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(402, 402, 402)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MSRPREs))
                            .addComponent(jLabel12)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ProductLinesDesRes))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fournisseurRes))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(productNameResultat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CodeProduitResultat))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PriceRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(QuantityInStockRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(464, Short.MAX_VALUE)))
            );
            jLayeredPane1Layout.setVerticalGroup(
                jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLayeredPane2)
                    .addContainerGap())
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(CodeProduitResultat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(productNameResultat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(QuantityInStockRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(PriceRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ProductLinesDesRes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(fournisseurRes))
                        .addGap(10, 10, 10)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(MSRPREs))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
        jLayeredPane1.setLayer(jLayeredPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(fournisseurRes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(QuantityInStockRes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(CodeProduitResultat, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(PriceRes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(productNameResultat, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ProductLinesDesRes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(MSRPREs, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(montrerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(montrerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }                        

    private void montrerButtonActionPerformed(java.awt.event.ActionEvent evt) {   
    	LOGGER.log(Level.INFO, "Le bouton montrer devient visible");
    	jLayeredPane1.setVisible(true);
    }                                        

    private void enregistrerButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	// enregistrerButton = Enregistrer

    	DAOProducts d_p = new DAOProducts(MaConnexion.getInstance());
    	Products prod = new Products();
    	String id2 =  (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
    	prod = d_p.read(id2);

    	String productLineNew = prod.getProductLine();
    	String productVendorNew = prod.getProductVendor();
    	double MSRPNew = prod.getMSRP();

    	// Champs modifiables
    	prod.setBuyPrice(Double.parseDouble(PriceRes.getText().toString()));
    	prod.setProductName(productNameResultat.getText().toString());
    	prod.setQuantityInStock(Integer.parseInt(QuantityInStockRes.getText().toString()));
    	prod.setProductDescription(DescriptionRes.getText());


    	// Champs non modifiables
    	prod.setProductLine(productLineNew);
    	prod.setProductVendor(productVendorNew);
    	prod.setMSRP(MSRPNew);

    	prod.setProductCode(CodeProduitResultat.getText().toString());
    	d_p.update(prod);

    	// reinitialisation de la fenêtre
    	jLayeredPane1.setVisible(false);
    	montrerButton.setVisible(false);
    	jTable1.setVisible(false);    	
    	 jTable1.setModel(new javax.swing.table.DefaultTableModel(
    	            creationJtable(),new String [] {
    	                "Code du produit", "Nom du produit", "Quantite en stock", "Prix d'achat"
    	            }
    	        ));
    	 jTable1.setVisible(true);
    	
    }                                        

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	// enregistrerButton = Annuler
    	afficherProduit();
    }                                        

    private static String productName;
    
    private void afficherProduit(){
    	
 	// on affiche le bouton "Montrer" quand nous selectionnons une ligne dans la Jtable
 		montrerButton.setVisible(true);
 	// On instancie nos differentes variables utilisees pour le produit
 	String id;
 	String productLine;
 	ImageIcon photo;
 	String productVendor;
 	String productDescription;
 	int quantityInStock;
 	double buyPrice;
 	double MSRP;
 	Products produit = new Products();
 	DAOProducts dao_product = new DAOProducts(MaConnexion.getInstance());

 	// nous recupperons le product Code du produit selectionné dans la jtable
 	id =  (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);

 	// On récupére dans la base de données le produit lié au productCode récupéré
 	produit = dao_product.read(id);

 	// On donne les valeur aux variable
 	productName = produit.getProductName();
 	productLine = produit.getProductLine();
 	photo = produit.getPhoto();
 	productVendor = produit.getProductVendor();
 	productDescription = produit.getProductDescription();
 	quantityInStock = produit.getQuantityInStock();
 	buyPrice = produit.getBuyPrice();
 	MSRP = produit.getMSRP();

 	// On donne les valeurs récupérer aux objets
 	CodeProduitResultat.setText(id);
 	PriceRes.setText(""+buyPrice);
 	fournisseurRes.setText(productVendor);
 	ProductLinesDesRes.setText(productLine);
 	productNameResultat.setText(productName);
 	MSRPREs.setText(""+MSRP);
 	QuantityInStockRes.setText(""+quantityInStock);
 	DescriptionRes.setText(productDescription);
 	jLabel14.setIcon(photo);

    }
    
    public String[][] creationJtable(){
    	
    	
        java.sql.Statement state;
        
    	try {
    		 // Liste des product Code
		state = MaConnexion.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String queryPC = "SELECT productCode FROM Products";
        ResultSet resPC =state.executeQuery(queryPC);	
        
		DAOProducts dao_product = new DAOProducts(MaConnexion.getInstance());
		
        ArrayList<String> listePC = new ArrayList<String>(); 
        ArrayList<String> listePN = new ArrayList<String>(); 
        ArrayList<String> listeQ = new ArrayList<String>(); 
        ArrayList<String> listeB = new ArrayList<String>(); 
        
        
        while (resPC.next()){
        	String resultat = resPC.getString("productCode");
        	listePC.add(resultat);
        	String productName = dao_product.read(resultat).getProductName();
        	String quantity = ""+dao_product.read(resultat).getQuantityInStock();
        	String prix = ""+dao_product.read(resultat).getBuyPrice();
        	listePN.add(productName);
        	listeQ.add(quantity);
        	listeB.add(prix);
        }	
        tableauProductCode = new String[listePC.size()];
        tableauProductName = new String[listePC.size()];
        tableauQuantityInStock = new String[listePC.size()];
        tableauBuyPrice = new String[listeB.size()];
        for(int i = 0; i < listeQ.size(); i++){
        	tableauProductCode[i] = listePC.get(i);
        	tableauProductName[i] = listePN.get(i);
        	tableauQuantityInStock[i] = listeQ.get(i);
            tableauBuyPrice[i] = listeB.get(i);
        }
        
        tabstring = new String[tableauProductCode.length][4];
        for(int i = 0 ; i < tableauProductCode.length ; i++){
        	tabstring[i][0] = tableauProductCode[i] ;
        	tabstring[i][1] = tableauProductName[i] ;
        	tabstring[i][2] = tableauQuantityInStock[i] ;
        	tabstring[i][3] = tableauBuyPrice[i] ;
        }
        
	} catch (SQLException e) {
		e.printStackTrace();
	}
    	
		return tabstring;
    }               
}
