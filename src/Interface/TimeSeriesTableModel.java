package Interface;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.jfree.data.time.TimeSeries;


public class TimeSeriesTableModel extends AbstractTableModel {
	    private final List<TimeSeries> timeSerieList = new ArrayList<TimeSeries>();
	 
	    private final String[] entetes = {"Title", "Event number"};
	 
	    public TimeSeriesTableModel() {
	        super();
	 
	    }
	 
	    @Override
	    public int getRowCount() {
	        return timeSerieList.size();
	    }
	 
	    @Override
	    public int getColumnCount() {
	        return entetes.length;
	    }
	 
	    @Override
	    public String getColumnName(int columnIndex) {
	        return entetes[columnIndex];
	    }
	 
	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        switch(columnIndex){
	            case 0:
	                return timeSerieList.get(rowIndex).getKey().toString();
	            case 1:
	                return timeSerieList.get(rowIndex).getItemCount();
	            default:
	                return null; 
	        }
	    }
	 
	    public void addTimeSerie(TimeSeries timeSeries) {
	        timeSerieList.add(timeSeries);
	 
	        fireTableRowsInserted(timeSerieList.size() -1, timeSerieList.size() -1);
	    }
	 
	    public void removeTimeSerie(int rowIndex) {
	        timeSerieList.remove(rowIndex);
	 
	        fireTableRowsDeleted(rowIndex, rowIndex);
	    }
	

}
