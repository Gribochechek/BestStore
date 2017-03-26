package tableModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import main.Main;
import objectsForStore.Goods;
import objectsForStore.SaleGoods;

public class TableModelSaleGoods extends AbstractTableModel{
	
	private ArrayList<SaleGoods> saleGoods;
	
	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
	
	public TableModelSaleGoods(ArrayList<SaleGoods> saleGoods) {
    	this.saleGoods = saleGoods;
    }
	
	public void addTableModelListener(TableModelListener listener) {
    	listeners.add(listener);
    }
	
	public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return saleGoods.size();
	}
	
	
	public String getColumnName(int columnIndex) {
	    switch (columnIndex) {
	    	case 0:
	    		return "Date";  
	        case 1:
	            return "Product Name";
	        case 2:
	            return "Manufacturer";
	        case 3:
	            return "Quantity";
	        case 4:
	            return "Unit";
	        case 5:
	            return "Price";
	        }
	    return "";
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SaleGoods goodIndex = saleGoods.get(rowIndex);
		Goods tempGood = null;
		for (Goods g : Main.mainWindow.goods) {
			if(g.getID()==goodIndex.getSaleGoodsID())
				tempGood =g;
		}
        switch (columnIndex) {
        case 0:
            return goodIndex.getDate();
        case 1:
            return tempGood.getName();
        case 2:
            return tempGood.getMaker();
        case 3:
            return goodIndex.getQuantity();
        case 4:
            return tempGood.getUnit();
        case 5:
            return tempGood.getPrice();
        }
        return "";
	}

}
