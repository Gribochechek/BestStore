package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import dialogWindows.WindowGoodsAdd;
import dialogWindows.WindowGoodsDelete;
import dialogWindows.WindowGoodsEdit;
import dialogWindows.WindowGroupAdd;
import dialogWindows.WindowGroupDelete;
import dialogWindows.WindowGroupEdit;
import dialogWindows.WindowReturn;
import dialogWindows.WindowSale;
import dialogWindows.WindowSearch;
import dialogWindows.WindowStatisticGoods;
import dialogWindows.WindowStatisticSaleGoods;
import dialogWindows.WindowSubgroupAdd;
import dialogWindows.WindowSubgroupDelete;
import dialogWindows.WindowSubgroupEdit;
import main.Main;
import objectsForStore.Goods;
import objectsForStore.SaleGoods;

public class ListenerButton implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Main.mainWindow.bGroupAdd) {
			WindowGroupAdd dialog = new WindowGroupAdd(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (dialog.result == true) {
				dialog.setResult();
				Main.mainWindow.refreshComboBoxes();
			}
		}
		
		if (e.getSource() == Main.mainWindow.bGroupEdit) {
			WindowGroupEdit dialog = new WindowGroupEdit(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (dialog.result == true) {
				dialog.setResult();
				Main.mainWindow.refreshComboBoxes();
			}
		}
		
		if (e.getSource() == Main.mainWindow.bGroupRemove) {
			WindowGroupDelete dialog = new WindowGroupDelete(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (dialog.result == true) {
				
				dialog.setResult();
				Main.mainWindow.refreshComboBoxes();
				Main.mainWindow.radio1.setSelected(true);
				Main.mainWindow.goodsTable.setModel(Main.mainWindow.goodsModel);
				Main.mainWindow.goodsTable.updateUI();
				
			}
		}
		
		if (e.getSource() == Main.mainWindow.bSubgroupAdd) {
			WindowSubgroupAdd dialog = new WindowSubgroupAdd(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (dialog.result == true) {
				dialog.setResult();
				Main.mainWindow.refreshComboBoxes();
				Main.mainWindow.goodsTable.updateUI();
			}
		}
		
		if (e.getSource() == Main.mainWindow.bSubgroupEdit) {
			WindowSubgroupEdit dialog = new WindowSubgroupEdit(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (dialog.result == true) {
				dialog.setResult();
				Main.mainWindow.refreshComboBoxes();
				Main.mainWindow.goodsTable.updateUI();
			}
		}
		
		if (e.getSource() == Main.mainWindow.bSubgroupRemove) {
			WindowSubgroupDelete dialog = new WindowSubgroupDelete(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (dialog.result == true) {
				dialog.setResult();
				Main.mainWindow.refreshComboBoxes();
				Main.mainWindow.radio1.setSelected(true);
				Main.mainWindow.goodsTable.setModel(Main.mainWindow.goodsModel);
				Main.mainWindow.goodsTable.updateUI();
			}
		}
		

		if (e.getSource() == Main.mainWindow.bGoodsAdd) {
			WindowGoodsAdd dialog = new WindowGoodsAdd(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		}

		if (e.getSource() == Main.mainWindow.bGoodsEdit) {
			if (Main.mainWindow.goodsTable.getSelectedRow() >= 0) {
				int idOfDeleteProduct = (int) Main.mainWindow.goodsTable
						.getValueAt(Main.mainWindow.goodsTable.getSelectedRow(), 0);
				
				WindowGoodsEdit dialog = new WindowGoodsEdit(Main.mainWindow, idOfDeleteProduct); 
				dialog.setVisible(true);
				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
			else{
				JOptionPane.showMessageDialog(Main.mainWindow, "No items selected");
			}

		}

		if (e.getSource() == Main.mainWindow.bGoodsRemove) {
			if (Main.mainWindow.goodsTable.getSelectedRow() >= 0) {
				int idOfDeleteProduct = (int) Main.mainWindow.goodsTable
						.getValueAt(Main.mainWindow.goodsTable.getSelectedRow(), 0);
				String nameOfDeleteProduct = (String) Main.mainWindow.goodsTable
						.getValueAt(Main.mainWindow.goodsTable.getSelectedRow(), 1);
				WindowGoodsDelete dialog = new WindowGoodsDelete(Main.mainWindow, idOfDeleteProduct,
						nameOfDeleteProduct); 
				dialog.setVisible(true);
				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
			else{
				JOptionPane.showMessageDialog(Main.mainWindow, "No items selected");
			}
		}
		
		if (e.getSource() == Main.mainWindow.bSearch) {
			WindowSearch dialog = new WindowSearch(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		}
		
		if (e.getSource() == Main.mainWindow.bStatistic) {
			WindowStatisticGoods dialog = new WindowStatisticGoods(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		}
		
		if (e.getSource() == Main.mainWindow.bSale) {
			if (Main.mainWindow.goodsTable.getSelectedRow() >= 0) {
				int idOfSaleProduct = (int) Main.mainWindow.goodsTable
						.getValueAt(Main.mainWindow.goodsTable.getSelectedRow(), 0);
				String nameOfSaleProduct = (String) Main.mainWindow.goodsTable
						.getValueAt(Main.mainWindow.goodsTable.getSelectedRow(), 1);
				WindowSale dialog = new WindowSale(Main.mainWindow, idOfSaleProduct,
						nameOfSaleProduct); 
				dialog.setVisible(true);
				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
			else{
				JOptionPane.showMessageDialog(Main.mainWindow, "No items selected");
			}

		}
		
		
	
		
		if (e.getSource() == Main.mainWindow.bSaleRemove) {
			if (Main.mainWindow.saleGoodsTable.getSelectedRow() >= 0) {
				int idOfReturnProduct=0;
				int index = Main.mainWindow.saleGoodsTable.getSelectedRow();
				String nameOfReturnProduct = (String) Main.mainWindow.saleGoodsTable
						.getValueAt(Main.mainWindow.saleGoodsTable.getSelectedRow(), 1);
				for (Goods g : Main.mainWindow.goods) {
					if(nameOfReturnProduct.equals(g.getName()))
						idOfReturnProduct = g.getID();
				}
				
				WindowReturn dialog = new WindowReturn(Main.mainWindow, idOfReturnProduct, index); 
				dialog.setVisible(true);
				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
			else{
				JOptionPane.showMessageDialog(Main.mainWindow, "No items selected");
			}
		}
		
		if (e.getSource() == Main.mainWindow.bSaleStatistic) {
			WindowStatisticSaleGoods dialog = new WindowStatisticSaleGoods(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		}

	}

}
