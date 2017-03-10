package tableModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import objectsForStore.SaleGoods;

public class TableModelSaleGoods implements TableModel{
	
	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private ArrayList<SaleGoods> saleGoods;

    public TableModelSaleGoods(ArrayList<SaleGoods> saleGoods) {
        this.saleGoods = saleGoods;
    }

	@Override
	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return null;
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return null;
	}

	@Override
	public int getRowCount() {
		return saleGoods.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SaleGoods saleIndex = saleGoods.get(rowIndex);
		switch(columnIndex) {
		case 0:
            return saleIndex.getSaleGoodsID();
        case 1:
            return saleIndex.getDate();
        case 2:
            return saleIndex.getQuantity();
        case 3:
            return saleIndex.getUnit();
        case 4:
            return saleIndex.getPrice();
        }
        return "";
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
	}

}
