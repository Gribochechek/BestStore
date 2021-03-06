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
import streams.DeletedGoodsWriter;
import streams.GoodsWriter;



public class WindowGoodsDelete extends JDialog{
	
	JButton ok, cancel;
	JLabel title, title2, title3;
	
	//в конструктор передается фрейм, ид, по которому удалится объект из коллекции и имя для вывода в диалоговом окне
	public WindowGoodsDelete(Frame parent, int id, String nameOfGoodForDelete){
		super(parent, true);
		setLocation(500, 200);
		setSize(300, 200);
		setResizable(false);
		getContentPane().setLayout(null);
		
		title = new JLabel(nameOfGoodForDelete);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(42, 10, 226, 30);
		getContentPane().add (title);
		
		title2 = new JLabel("will be deleted!!!");
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title2.setBounds(42, 40, 226, 30);
		getContentPane().add (title2);
		
		title3 = new JLabel("Are you sure?");
		title3.setHorizontalAlignment(SwingConstants.CENTER);
		title3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title3.setBounds(42, 70, 226, 30);
		getContentPane().add (title3);
		
		
		ok = new JButton ("YES");
		ok.setBounds(30, 120, 100, 23);
		getContentPane().add(ok);
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int tempID;
				for(int i =0;i<Main.mainWindow.goods.size();i++){
					tempID = Main.mainWindow.goods.get(i).getID();
					if(tempID==id){
						Main.mainWindow.deletedGoods.add(Main.mainWindow.goods.get(i));
						Main.mainWindow.goods.remove(Main.mainWindow.goods.get(i));					
					}
				}
				
				GoodsWriter gw = new GoodsWriter();
				gw.saveGoodsInFile(Main.mainWindow.goods);
				DeletedGoodsWriter dgw = new DeletedGoodsWriter();
				dgw.saveDeletedGoodsInFile(Main.mainWindow.deletedGoods);
				
				dispose();
				Main.mainWindow.radio1.setSelected(true);
				Main.mainWindow.goodsTable.setModel(Main.mainWindow.goodsModel);
				Main.mainWindow.goodsTable.updateUI();
				
				disableGoodsButtons();
				
				
			}

			
			
			
		});
		
		cancel = new JButton ("NO");
		cancel.setBounds(160, 120, 100, 23);
		getContentPane().add(cancel);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
			
		});
		
	}
	
	 static void disableGoodsButtons() {
		if(Main.mainWindow.goods.size()==0){
			Main.mainWindow.bGoodsEdit.setEnabled(false);
			Main.mainWindow.bGoodsRemove.setEnabled(false);
			Main.mainWindow.bSearch.setEnabled(false);
			Main.mainWindow.bStatistic.setEnabled(false);
			Main.mainWindow.bSale.setEnabled(false);
			Main.mainWindow.bExport.setEnabled(false);
			Main.mainWindow.bIncome.setEnabled(false);
			}
		
	}

}
