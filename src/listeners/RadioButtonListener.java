package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import main.Main;
import objectsForStore.Goods;
import tableModels.TableModelGoods;

public class RadioButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {

		// Show all goods
		if (Main.mainWindow.radio1.isSelected()) {
			Main.mainWindow.goodsTable.setModel(Main.mainWindow.goodsModel);
			Main.mainWindow.setColumnWidth(Main.mainWindow.goodsTable, new int[] { 10, 110, 110, 180, 20, 20, 20 });
		}

		// Show all sale goods
/*		if (Main.mainWindow.radio4.isSelected()) {
			Main.mainWindow.saleGoodsTable.setModel(Main.mainWindow.saleGoodsModel);
			Main.mainWindow.setColumnWidth(Main.mainWindow.saleGoodsTable,
					new int[] { 25, 5, 150, 10, 180, 10, 10, 10 });
		}
*/
		// Show goods of subgroup
		if (Main.mainWindow.radio3.isSelected() | e.getSource() == Main.mainWindow.comboSubgroup) {
			Main.mainWindow.radio3.setSelected(true);
			if(Main.mainWindow.subgroupstxt.exists()&&Main.mainWindow.subgroupstxt.length()>5){
			int index = Main.mainWindow.comboSubgroup.getSelectedIndex();
			int subgroupID = Main.mainWindow.subgroups.get(index).getSubgroupID();

			ArrayList<Goods> tempGoods = new ArrayList<Goods>();
			for (int i = 0; i < Main.mainWindow.goods.size(); i++) {

				if (Main.mainWindow.goods.get(i).getSubgroupID() == subgroupID)
					tempGoods.add(Main.mainWindow.goods.get(i));
			}
			TableModelGoods tempGoodsModel = new TableModelGoods(tempGoods);
			Main.mainWindow.goodsTable.setModel(tempGoodsModel);
			Main.mainWindow.setColumnWidth(Main.mainWindow.goodsTable, new int[] { 10, 110, 110, 180, 20, 20, 20 });
			}
		}

		// Show goods of group
		if (Main.mainWindow.radio2.isSelected() | e.getSource() == Main.mainWindow.comboGroup) {
			Main.mainWindow.radio2.setSelected(true);
			if(Main.mainWindow.groupstxt.exists()&&Main.mainWindow.groupstxt.length()>5){
			int index = Main.mainWindow.comboGroup.getSelectedIndex();
			int groupID = Main.mainWindow.groups.get(index).getGroupID();
		

			ArrayList<Goods> tempGoods = new ArrayList<Goods>();
			for (int i = 0; i < Main.mainWindow.goods.size(); i++) {

				if (Main.mainWindow.goods.get(i).getGroupID() == groupID)
					tempGoods.add(Main.mainWindow.goods.get(i));
			}
			TableModelGoods tempGoodsModel = new TableModelGoods(tempGoods); 
			Main.mainWindow.goodsTable.setModel(tempGoodsModel); 
			Main.mainWindow.setColumnWidth(Main.mainWindow.goodsTable, new int[] { 10, 110, 110, 180, 20, 20, 20 });
			}
		}
	}
}
