package dialogWindows;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.Main;
import objectsForStore.Goods;
import objectsForStore.SaleGoods;
import streams.GoodsWriter;
import streams.SaleGoodsWriter;

public class WindowReturn extends JDialog {

	JLabel title, title2, title3, title4;
	JButton bOk, bCancel;
	JSlider slider;
	Goods tempGood;
	SaleGoods tempSalegood;
	JLabel tf;
	private ChangeListener listener;
	int indexOfTempGood;

	public WindowReturn(Frame parent, int id, int index) {
		super(parent, true);
		setLocation(500, 200);
		setSize(300, 220);
		setResizable(false);
		getContentPane().setLayout(null);

		Goods g;
		for (int i = 0; i < Main.mainWindow.goods.size(); i++) {
			g = Main.mainWindow.goods.get(i);
			if (g.getID() == id) {
				tempGood = g;
				indexOfTempGood = i;
				break;
			}
		}
		tempSalegood = Main.mainWindow.saleGoods.get(index);

		title = new JLabel("Return Product to Stock");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(50, 10, 200, 30);
		getContentPane().add(title);

		title2 = new JLabel("Enter Quantity of ");
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title2.setBounds(50, 40, 200, 30);
		getContentPane().add(title2);

		title3 = new JLabel("returned product");
		title3.setHorizontalAlignment(SwingConstants.CENTER);
		title3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title3.setBounds(50, 60, 200, 30);
		getContentPane().add(title3);

		title4 = new JLabel("Max: "+tempSalegood.getQuantity());
		title4.setHorizontalAlignment(SwingConstants.CENTER);
		title4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title4.setBounds(50, 80, 200, 30);
		getContentPane().add(title4);

		slider = new JSlider(0, (int) tempSalegood.getQuantity(), 0);
		slider.setBounds(10, 110, 220, 30);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing((int) tempSalegood.getQuantity() / 5);
		slider.setMinorTickSpacing((int) tempSalegood.getQuantity() / 10);
		tf = new JLabel("0");
		tf.setBounds(240, 110, 30, 30);
		listener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				// Обновление поля редактирования при
				// изменении значения регулятора.
				JSlider source = (JSlider) event.getSource();
				tf.setText("" + source.getValue());
			}

		};
		slider.addChangeListener(listener);
		getContentPane().add(slider);
		getContentPane().add(tf);

		bOk = new JButton("OK");
		bOk.setBounds(30, 150, 100, 23);
		getContentPane().add(bOk);
		bOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (slider.getValue() < 1) {
					dispose();
				} else {
					Main.mainWindow.goods.get(indexOfTempGood)
							.setQuantity(Main.mainWindow.goods.get(indexOfTempGood).getQuantity() + slider.getValue());
					Main.mainWindow.saleGoods.get(index).setQuantity(Main.mainWindow.saleGoods.get(index).getQuantity()-slider.getValue());
					
					if(Main.mainWindow.saleGoods.get(index).getQuantity()==0)
						Main.mainWindow.saleGoods.remove(index);
					
					dispose();
					Main.mainWindow.saleGoodsTable.updateUI();
					Main.mainWindow.goodsTable.updateUI();

					GoodsWriter gw = new GoodsWriter();
					gw.saveGoodsInFile(Main.mainWindow.goods);

					SaleGoodsWriter sgw = new SaleGoodsWriter();
					sgw.saveSaleGoodsInFile(Main.mainWindow.saleGoods);

				}
			}

		});

		bCancel = new JButton("Cancel");
		bCancel.setBounds(160, 150, 100, 23);
		getContentPane().add(bCancel);
		bCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}

		});

	}

}
