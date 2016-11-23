/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import DAO.GO;
import GestionLog.MyFormatter;

public class fenetre extends javax.swing.JFrame {

	private static final Logger LOGGER = Logger.getLogger("myLogger");
	
    // Variables declaration
    private JMenu gestionProduitsMenu;
    private JMenu visualisationVenteMenu;
    private JMenu visualisationLibreMenu;
    private JMenuBar barreMenu;
    private JPanel gestion;
    private JPanel visualisationVentes;

	private JPanel visualisationLibre;

	
    // End of variables declaration
	
    /**
     * Creates new form fenetre
     */
    public fenetre() {
    	new GO();
        
        initComponents();
    }

    private void initComponents() {
    	this.setTitle("Interface de gestion de base de donnees");
    	
    	Handler fh;
    	
     	try 
        {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");// Nom du package contenant le Look And Feel.
            SwingUtilities.updateComponentTreeUI(this); 
        }

    catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } // Exception
    catch (InstantiationException e)
        {
            e.printStackTrace();
        } // Exception
    catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } // Exception
    catch (UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        } // Exception

    
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
    	
        barreMenu = new JMenuBar();
        gestionProduitsMenu = new JMenu();
        visualisationVenteMenu = new JMenu();
        visualisationLibreMenu = new JMenu();
       

        LOGGER.log(Level.INFO, "Ouverture de la page principale");
        
        gestion = new GestionsProduits();
    	visualisationVentes = new VisualisationDesVentes();
    	visualisationLibre = new VisualisationLibre();

    	this.add(gestion);
    	this.add(visualisationVentes);
    	this.add(visualisationLibre);

        setJMenuBar(barreMenu);
           
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gestionProduitsMenu.setText("Gestion des produits");
        gestionProduitsMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gestionProduitsMenuMouseClicked(evt);
            }
        });
        barreMenu.add(gestionProduitsMenu);

        visualisationVenteMenu.setText("Visualisations des ventes");
        visualisationVenteMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visualisationVenteMenuMouseClicked(evt);
            }
        });
        barreMenu.add(visualisationVenteMenu);

        visualisationLibreMenu.setText("Visualisations libres");
        visualisationLibreMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visualisationLibreMenuMouseClicked(evt);
            }
        });
        barreMenu.add(visualisationLibreMenu);


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
        this.setLocationRelativeTo(null);
    }

    private void gestionProduitsMenuMouseClicked(java.awt.event.MouseEvent evt) {
    	LOGGER.log(Level.INFO, "Ouverture de la page gestion des produits");
    	gestion.setVisible(true);
    	javax.swing.MenuSelectionManager.defaultManager().clearSelectedPath();
    	this.setContentPane(gestion);
    	this.revalidate();
    }
    
    private void visualisationVenteMenuMouseClicked(java.awt.event.MouseEvent evt) {
    	LOGGER.log(Level.INFO, "Ouverture de la page visualisation des ventes");
        visualisationVentes.setVisible(true);
    	javax.swing.MenuSelectionManager.defaultManager().clearSelectedPath();
        this.setContentPane(visualisationVentes);
    	this.revalidate();
    }
    
    private void visualisationLibreMenuMouseClicked(java.awt.event.MouseEvent evt) {
    	LOGGER.log(Level.INFO, "Ouverture de la page visualisation libre");
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
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
        	LOGGER.log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
        	LOGGER.log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        	LOGGER.log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fenetre().setVisible(true);
            }
        });
    }
}
