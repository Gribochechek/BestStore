package dialogWindows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.Main;
import objectsForStore.Goods;
import streams.GoodsWriter;

public class WindowGoodsAdd extends JDialog{
	
	JButton ok, cancel;
	JLabel title, group, subgroup, name, description, producer, price, quantity, measureType;
	JComboBox comboGroup, comboSubgroup;
	JTextField jt_producer, jt_price, jt_quantity, jt_measureType;
	JTextArea jt_name, jt_description;
	eHandler available_handler = new eHandler();
	Goods product;
	
	
	public WindowGoodsAdd(Frame parent) {
		super(parent, true);
		setLocation(500, 200);
		setSize(300, 450);
		setResizable(false);
		getContentPane().setLayout(null);
		
		title = new JLabel("Add new Good #" + (Main.mainWindow.goods.size()+1));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(42, 11, 226, 30);
		getContentPane().add (title);
		
		group = new JLabel("Group*:");
		group.setBounds(25, 63, 85, 14);
		getContentPane().add(group);
		comboGroup = new JComboBox();
		String[] string = Main.mainWindow.getGroupNames();
		comboGroup.setModel(new DefaultComboBoxModel(string));
		comboGroup.setBounds(124, 63-3, 146, 25);
		comboGroup.addActionListener(available_handler);
		getContentPane().add(comboGroup);
		
		subgroup = new JLabel("Subgroup*:");
		subgroup.setBounds(25, 63+14+15, 
				85, 14);
		getContentPane().add(subgroup);
		comboSubgroup = new JComboBox();
		string = Main.mainWindow.getSubgroupNamesByGroupID(Main.mainWindow.groups.get(comboGroup.getSelectedIndex()).getGroupID());
		comboSubgroup.setModel(new DefaultComboBoxModel(string));
		comboSubgroup.setBounds(124, 63+14+15-3, 146, 25);
		getContentPane().add(comboSubgroup);
		
		name = new JLabel("Name*:");
		name.setBounds(25, 63+14*2+15*2, 
				100, 14);
		getContentPane().add(name);
		jt_name = new JTextArea();
		jt_name.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		jt_name.setWrapStyleWord(true);
		jt_name.setRows(3);
		jt_name.setLineWrap(true);
		jt_name.setBounds(126, 63+14*2+15*2, 142, 49);
		getContentPane().add(jt_name);
		
		description = new JLabel("Description:");
		description.setBounds(25, 63+14*5+15*3, 
				85, 14);
		getContentPane().add(description);
		jt_description = new JTextArea();
		jt_description.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		jt_description.setWrapStyleWord(true);
		jt_description.setRows(3);
		jt_description.setLineWrap(true);
		jt_description.setBounds(126, 63+14*5+15*3, 142, 49);
		getContentPane().add(jt_description);

		
		producer = new JLabel("Manufacturer:");
		producer.setBounds(25, 63+14*8+15*4, 
				85, 14);
		getContentPane().add(producer);
		jt_producer = new JTextField(20);
		jt_producer.setBounds(124, 63+14*8+15*4-3, 
				146, 25);
		getContentPane().add(jt_producer);
		
		price = new JLabel("Price, uah");
		price.setBounds(25, 63+14*9+15*5,
				60, 14);
		getContentPane().add(price);
		jt_price = new JTextField(20);
		jt_price.setBounds(124, 63+14*9+15*5-3, 
				146, 25);
		getContentPane().add(jt_price);
		
		quantity = new JLabel("Quantity*:");
		quantity.setBounds(25, 63+14*10+15*6, 
				90, 14);
		getContentPane().add(quantity);
		jt_quantity = new JTextField();
		jt_quantity.setBounds(124, 63+14*10+15*6-3, 
				146, 25);
		getContentPane().add(jt_quantity);
		
		measureType = new JLabel("Measurement*:");
		measureType.setBounds(25, 63+14*11+15*7, 
				100, 14);
		getContentPane().add(measureType);
		jt_measureType = new JTextField();
		jt_measureType.setBounds(124, 63+14*11+15*7-3, 
				146, 25);
		getContentPane().add(jt_measureType);
		
		ok = new JButton ("OK");
		ok.setBounds(30, 367, 100, 23);
		getContentPane().add(ok);
		ok.addActionListener(available_handler);
		Main.mainWindow.goodsTable.updateUI();
		
		cancel = new JButton ("Cancel");
		cancel.setBounds(160, 367, 100, 23);
		getContentPane().add(cancel);
		cancel.addActionListener(available_handler);
		
	}
    
	public class eHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == ok){
				if (comboSubgroup.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Subgroup is empty");
					return;
				}
				if (jt_name.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Name format");
					return;
				}
				if (jt_quantity.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Quantity format");
					return;
				}
				if (jt_measureType.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Measurement format");
					return;
				}
				if (jt_price.getText().equals("")||!isDouble(jt_price.getText())) {
					JOptionPane.showMessageDialog(null, "Invalid Price format");
					return;
				}
				if (!isDouble(jt_quantity.getText())) {
					JOptionPane.showMessageDialog(null, "Invalid Quantity format");
					return;
				}
				
				else
					setResult();
					dispose();
			}
			
			if (e.getSource() == cancel){
				dispose();
			}
			
			if (e.getSource() == comboGroup){
				String[] string = Main.mainWindow.getSubgroupNamesByGroupID(comboGroup.getSelectedIndex() + 1);
				comboSubgroup.setModel(new DefaultComboBoxModel(string));
			}
		}
	}
	
	//Change commas into dots (for doubles)
	String setCommas(String str){
		if (str.contains(","))
			str = str.replace (",", ".");
		return str;
	}
	
	void setResult(){
		//Check unique name
		String temp_str = jt_name.getText();
		for (int i = 0; i < Main.mainWindow.goods.size(); i++){
			Goods goods = Main.mainWindow.goods.get(i);
			String str2 = goods.getName();
			if (temp_str.equals(str2)){
				JOptionPane.showMessageDialog(null, "Such entry already exists!");
				Main.mainWindow.goodsTable.setRowSelectionInterval(i, i); //highlight the row with the same name
				return;
			}
		}
		
		//If the same name is not found - preparing the new good
		int subgroupID = Main.mainWindow.getSubgroupIDByName((String) comboSubgroup.getSelectedItem());
		
		//check is there are dots instead of commas
		String quantity = setCommas(jt_quantity.getText());
		String price = setCommas(jt_price.getText());
		if (price.equals("")) price = "0";
		
		//First Tab (Goods)
		if(Main.mainWindow.goods.size()>0){
		product = new Goods(Main.mainWindow.goods.get(Main.mainWindow.goods.size()-1).getID()+1, subgroupID, jt_name.getText(), jt_description.getText(), jt_producer.getText(), 
				Double.parseDouble(quantity), Double.parseDouble(price), jt_measureType.getText());
		}
		else{
			product = new Goods(1, subgroupID, jt_name.getText(), jt_description.getText(), jt_producer.getText(), 
					Double.parseDouble(quantity), Double.parseDouble(price), jt_measureType.getText());
		}
		Main.mainWindow.goods.add(product);
		GoodsWriter gw = new GoodsWriter();
		gw.saveGoodsInFile(Main.mainWindow.goods);
		
		Main.mainWindow.bGoodsEdit.setEnabled(true);
		Main.mainWindow.bGoodsRemove.setEnabled(true);
		Main.mainWindow.bSearch.setEnabled(true);
		Main.mainWindow.bStatistic.setEnabled(true);
		Main.mainWindow.bSale.setEnabled(true);
		Main.mainWindow.bExport.setEnabled(true);
		Main.mainWindow.bIncome.setEnabled(true);
		
		Main.mainWindow.goodsTable.updateUI();
		
		
	}
	
	
	// method checks is string double?
	public static boolean isDouble(String s) {
		char c = s.charAt(0);
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if ((c >= '0' && c <= '9') || c == '.') {
				continue;
			} else
				return false;
		}
		return true;
	}

}
