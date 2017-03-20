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
import dialogWindows.WindowSearch;
import dialogWindows.WindowStatisticGoods;
import main.Main;

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
		

	}

}
