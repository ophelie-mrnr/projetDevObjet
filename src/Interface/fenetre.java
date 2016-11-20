/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAO.GO;
import GestionLog.MyFormatter;

public class fenetre extends javax.swing.JFrame {

	private static final Logger LOGGER = Logger.getLogger("myLogger");
	
    // Variables declaration
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel gestion;
    private javax.swing.JPanel visualisationVentes;

	private javax.swing.JPanel visualisationLibre;

    // End of variables declaration
	
    /**
     * Creates new form fenetre
     */
    public fenetre() {
    	new GO();
        
        initComponents();
    }

    private void initComponents() {

    	Handler fh;
    	try {
    		LOGGER.setUseParentHandlers(false); // POUR NE PAS AFFICHER SUR LA CONSOLE (PAR DEFAUT)
			fh = new FileHandler("myLog.log", true); // pour envoyer le flux de sortie vers le fichier myLog.log
			fh.setFormatter(new MyFormatter());
			LOGGER.addHandler(fh);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        LOGGER.log(Level.INFO, "Ouverture de la page principale");
        
        gestion = new GestionsProduits();
    	visualisationVentes = new VisualisationDesVentes();
    	visualisationLibre = new VisualisationLibre();

    	this.add(gestion);
    	this.add(visualisationVentes);
    	this.add(visualisationLibre);

        setJMenuBar(jMenuBar1);
           
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Gestion des produits");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Visualisations des ventes");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Visualisations libres");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );
        
        
        
        
        pack();
    }

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {
    	LOGGER.log(Level.INFO, "Ouverture du premier onglet sur la gestion des produits");
    	gestion.setVisible(true);
    	javax.swing.MenuSelectionManager.defaultManager().clearSelectedPath();
    	this.setContentPane(gestion);
    	this.revalidate();
    }
    
    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {
    	LOGGER.log(Level.INFO, "Ouverture du deuxieme onglet sur la visualisation des ventes");
        visualisationVentes.setVisible(true);
    	javax.swing.MenuSelectionManager.defaultManager().clearSelectedPath();
        this.setContentPane(visualisationVentes);
    	this.revalidate();
    }
    
    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {
    	LOGGER.log(Level.INFO, "Ouverture du troisieme onglet sur la visualisation libre");
        visualisationLibre.setVisible(true);
    	javax.swing.MenuSelectionManager.defaultManager().clearSelectedPath();
        this.setContentPane(visualisationLibre);
    	this.revalidate();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fenetre().setVisible(true);
            }
        });
    }
}
