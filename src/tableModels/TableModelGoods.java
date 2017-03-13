package tableModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import objectsForStore.Goods;

public class TableModelGoods extends AbstractTableModel{
	
	private ArrayList<Goods> goods;
	
	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
	
	public TableModelGoods(ArrayList<Goods> goods) {
    	this.goods = goods;
    }
	
	public void addTableModelListener(TableModelListener listener) {
    	listeners.add(listener);
    }
	
	public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return goods.size();
	}
	
	
	public String getColumnName(int columnIndex) {
	    switch (columnIndex) {
	        case 0:
	            return "ID";
	        case 1:
	            return "Product Name";
	        case 2:
	            return "Manufacturer";
	        case 3:
	            return "Description";
	        case 4:
	            return "Quantity";
	        case 5:
	            return "Unit";
	        case 6:
	            return "Price";
	        }
	    return "";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Goods goodIndex = goods.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return goodIndex.getID();
        case 1:
            return goodIndex.getName();
        case 2:
            return goodIndex.getMaker();
        case 3:
            return goodIndex.getDesc();
        case 4:
            return goodIndex.getQuantity();
        case 5:
            return goodIndex.getUnit();
        case 6:
            return goodIndex.getPrice();
        }
        return "";
	}
	
	

}
