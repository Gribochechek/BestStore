package dialogWindows;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class WindowSale extends JDialog {

	JLabel title, title2, title3, title4;
	JButton bOk, bCancel;
	JSlider slider;
	Goods tempGood;
	SaleGoods salegood;
	JLabel tf;
	private ChangeListener listener;
	int indexOfTempGood;

	public WindowSale(Frame parent, int id, String name) {
		super(parent, true);
		setLocation(500, 200);
		setSize(300, 220);
		setResizable(false);
		getContentPane().setLayout(null);

		Goods g;
		for(int i=0;i<Main.mainWindow.goods.size();i++){
			g = Main.mainWindow.goods.get(i);
			if (g.getID() == id){
				tempGood = g;
				indexOfTempGood = i;
				break;
			}
		}

		title = new JLabel("Sale");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(50, 10, 200, 30);
		getContentPane().add(title);

		title2 = new JLabel("Enter Quantity of ");
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title2.setBounds(50, 40, 200, 30);
		getContentPane().add(title2);

		title3 = new JLabel(name);
		title3.setHorizontalAlignment(SwingConstants.CENTER);
		title3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title3.setBounds(50, 60, 200, 30);
		getContentPane().add(title3);

		title4 = new JLabel("for sale. Max: " + (int) tempGood.getQuantity());
		title4.setHorizontalAlignment(SwingConstants.CENTER);
		title4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title4.setBounds(50, 80, 200, 30);
		getContentPane().add(title4);

		slider = new JSlider(0, (int) tempGood.getQuantity(), 0);
		slider.setBounds(10, 110, 220, 30);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing((int) tempGood.getQuantity() / 5);
		slider.setMinorTickSpacing((int) tempGood.getQuantity() / 10);
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
					Date date = new Date();
					SimpleDateFormat dateFormat = null;
					dateFormat = new SimpleDateFormat("ddMMMyyyy HH:mm:ss");
					String sData = dateFormat.format(date);
					salegood = new SaleGoods(tempGood.getID(), sData, slider.getValue());
					Main.mainWindow.goods.get(indexOfTempGood).setQuantity(Main.mainWindow.goods.get(indexOfTempGood).getQuantity() - slider.getValue());
					Main.mainWindow.saleGoods.add(salegood);
					dispose();
					Main.mainWindow.radio1.setSelected(true);
					Main.mainWindow.saleGoodsTable.updateUI();
					Main.mainWindow.goodsTable.setModel(Main.mainWindow.goodsModel);
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
