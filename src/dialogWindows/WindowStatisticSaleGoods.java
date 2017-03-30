package dialogWindows;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.Main;
import objectsForStore.Goods;

public class WindowStatisticSaleGoods extends JDialog{
	JLabel title, lTotalValue, lTotalQuantity;
	JButton ok;
	
	public WindowStatisticSaleGoods(Frame parent) {

		super(parent, true);
		setLocation(500, 200);
		setSize(320, 200);
		setResizable(false);
		getContentPane().setLayout(null);

		title = new JLabel("Statistics");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(42, 10, 200, 30);
		getContentPane().add(title);
		int totalValue = 0;
		double totalQuant=0;
		for (int i = 0; i < Main.mainWindow.saleGoods.size(); i++) {
			double tempPrice=0;
			for (Goods g : Main.mainWindow.goods) {
				if(Main.mainWindow.saleGoods.get(i).getSaleGoodsID()==g.getID()){
				tempPrice = g.getPrice();
				break;
				}
			}
			for (Goods g : Main.mainWindow.deletedGoods) {
				if(Main.mainWindow.saleGoods.get(i).getSaleGoodsID()==g.getID()){
				tempPrice = g.getPrice();
				break;
				}
			}
			double temp = tempPrice * Main.mainWindow.saleGoods.get(i).getQuantity();
			totalQuant += Main.mainWindow.saleGoods.get(i).getQuantity();
			totalValue += temp;
		}
		
		lTotalValue = new JLabel("Total value of All Sold goods in stock: " + totalValue + " uah");
		lTotalValue.setHorizontalAlignment(SwingConstants.CENTER);
		lTotalValue.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lTotalValue.setBounds(10, 80, 280, 30);
		getContentPane().add(lTotalValue);

		lTotalQuantity = new JLabel(totalQuant + " items were sold");
		lTotalQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lTotalQuantity.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lTotalQuantity.setBounds(10, 50, 280, 30);
		getContentPane().add(lTotalQuantity);
		
		ok = new JButton("Close");
		ok.setBounds(100, 120, 100, 23);
		getContentPane().add(ok);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}

		});
		
	}
	
}
