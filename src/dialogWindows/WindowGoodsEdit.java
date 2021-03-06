package dialogWindows;

import java.awt.Frame;

import javax.swing.JLabel;

import main.Main;
import objectsForStore.Goods;
import streams.GoodsWriter;

public class WindowGoodsEdit extends WindowGoodsAdd {
	Goods tempGood;
	int indexOfTempGoodInArrayList;
	JLabel tempQuant;

	public WindowGoodsEdit(Frame parent, int id) {
		super(parent);
		for (int i = 0; i < Main.mainWindow.goods.size(); i++) {
			if (id == Main.mainWindow.goods.get(i).getID()) {
				tempGood = Main.mainWindow.goods.get(i);
				indexOfTempGoodInArrayList = i;
			}
		}
		title.setText("Edition of Product");
		comboGroup.setSelectedIndex(tempGood.getGroupID() - 1);
		int index = 0;
		int tempGroupID = 0;
		for (int i = 0; i < Main.mainWindow.subgroups.size(); i++) {
			tempGroupID = Main.mainWindow.subgroups.get(i).getGroupID();
			if (tempGroupID == tempGood.getGroupID()) {
				index++;
			}

			if (Main.mainWindow.subgroups.get(i).getSubgroupID() == tempGood.getSubgroupID()) {
				index--;
				break;
			}

		}
		jt_quantity.setText(""+tempGood.getQuantity());
		getContentPane().remove(jt_quantity);
		tempQuant = new JLabel(""+tempGood.getQuantity());
		tempQuant.setBounds(124, 63+14*10+15*6-3, 146, 25);
		getContentPane().add(tempQuant);

		comboSubgroup.setSelectedIndex(index);
		jt_name.setText(tempGood.getName());
		jt_description.setText(tempGood.getDesc());
		jt_producer.setText(tempGood.getMaker());
		jt_price.setText("" + tempGood.getPrice());
		
		jt_measureType.setText(tempGood.getUnit());

	}

	void setResult() {

		// якщо аналогічного найменування не знайдено - готуємо об'єкт - новий
		// товар
		int subgroupID = Main.mainWindow.getSubgroupIDByName((String) comboSubgroup.getSelectedItem());

		// перевіряємо, чи не введено у числі випадково кому замість крапки
		
		String price = setCommas(jt_price.getText());
		if (price.equals(""))
			price = "0";
		
			product = new Goods(tempGood.getID(), subgroupID, jt_name.getText().trim(), jt_description.getText(), jt_producer.getText().trim(),
					tempGood.getQuantity(), Double.parseDouble(price), jt_measureType.getText().trim());
		
		
		Main.mainWindow.goods.remove(indexOfTempGoodInArrayList);
		Main.mainWindow.goods.add(indexOfTempGoodInArrayList, product);
		GoodsWriter gw = new GoodsWriter();
		gw.saveGoodsInFile(Main.mainWindow.goods);

		Main.mainWindow.goodsTable.updateUI();
	}

}
