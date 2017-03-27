package dialogWindows;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import main.Main;
import streams.GoodsWriter;

public class WindowArrive extends WindowSale{
	
	JButton aOk;
	JSpinner quant;

	public WindowArrive(Frame parent, int id, String name) {
		super(parent, id, name);
		title.setText("Arriving at stock");
		title4.setText("that arrived");
		getContentPane().remove(slider);
		getContentPane().remove(tf);
		getContentPane().remove(bOk);
		
		SpinnerModel sm = new SpinnerNumberModel(0, 0, 1000, 1);
		quant = new JSpinner(sm);
		JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) quant.getEditor();
		editor.getTextField().setEditable(false);

		quant.setBounds(125, 120, 50, 23);
		getContentPane().add(quant);
		
		aOk = new JButton("OK");
		aOk.setBounds(30, 150, 100, 23);
		getContentPane().add(aOk);
		aOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ((int)quant.getValue() < 1) {
					dispose();
				} else {
					
					Main.mainWindow.goods.get(indexOfTempGood).setQuantity(Main.mainWindow.goods.get(indexOfTempGood).getQuantity() + (int)quant.getValue());
					dispose();
					Main.mainWindow.radio1.setSelected(true);
					Main.mainWindow.saleGoodsTable.updateUI();
					Main.mainWindow.goodsTable.setModel(Main.mainWindow.goodsModel);
					Main.mainWindow.goodsTable.updateUI();
					
					GoodsWriter gw = new GoodsWriter();
					gw.saveGoodsInFile(Main.mainWindow.goods);
					
				}
			}

		});

		
	}
		
	
}
