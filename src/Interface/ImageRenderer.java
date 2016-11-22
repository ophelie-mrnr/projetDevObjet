package Interface;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class ImageRenderer extends DefaultTableCellRenderer {
	  JLabel lbl = new JLabel();

	  // la fonction regarde le contenu de la cellule image
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {
	    lbl.setIcon((ImageIcon) value); // le jlabel prend l'image
	    return lbl;
	  }
	}