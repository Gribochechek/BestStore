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
import mainWindows.MainWindow;
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
	
	final int START_HEIGHT_POINT = 63;
	final int START_WIDTH_POINT = 25;
	final int ELEMENT_HEIGHT = 14;
	final int TEXTFIELD_HEIGHT = 25;
	final int SPACE = 15;
	
	public WindowGoodsAdd(Frame parent) {
		super(parent, true);
		setLocation(500, 200);
		setSize(300, 450);
		setResizable(false);
		getContentPane().setLayout(null);
		
		title = new JLabel("Add new Good №" + (Main.mainWindow.goods.size()+1));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(42, 11, 226, 30);
		getContentPane().add (title);
		
		group = new JLabel("Група*:");
		group.setBounds(START_WIDTH_POINT, START_HEIGHT_POINT, 85, ELEMENT_HEIGHT);
		getContentPane().add(group);
		comboGroup = new JComboBox();
		String[] string = Main.mainWindow.getGroupNames();
		comboGroup.setModel(new DefaultComboBoxModel(string));
		comboGroup.setBounds(124, START_HEIGHT_POINT-3, 146, TEXTFIELD_HEIGHT);
		comboGroup.addActionListener(available_handler);
		getContentPane().add(comboGroup);
		
		subgroup = new JLabel("Підгрупа*:");
		subgroup.setBounds(START_WIDTH_POINT, START_HEIGHT_POINT+ELEMENT_HEIGHT+SPACE, 
				85, ELEMENT_HEIGHT);
		getContentPane().add(subgroup);
		comboSubgroup = new JComboBox();
		string = Main.mainWindow.getSubgroupNamesByGroupID(comboGroup.getSelectedIndex()+1);
		comboSubgroup.setModel(new DefaultComboBoxModel(string));
		comboSubgroup.setBounds(124, START_HEIGHT_POINT+ELEMENT_HEIGHT+SPACE-3, 146, TEXTFIELD_HEIGHT);
		getContentPane().add(comboSubgroup);
		
		name = new JLabel("Найменування*:");
		name.setBounds(START_WIDTH_POINT, START_HEIGHT_POINT+ELEMENT_HEIGHT*2+SPACE*2, 
				100, ELEMENT_HEIGHT);
		getContentPane().add(name);
		jt_name = new JTextArea();
		jt_name.setBorder(BorderFactory.createLineBorder(Color.GRAY)); //встановлюємо сірий ободок
		jt_name.setWrapStyleWord(true);
		jt_name.setRows(3);
		jt_name.setLineWrap(true); //переносить текст на наступний рядок
		jt_name.setBounds(126, START_HEIGHT_POINT+ELEMENT_HEIGHT*2+SPACE*2, 142, 49);
		getContentPane().add(jt_name);
		
		description = new JLabel("Опис");
		description.setBounds(START_WIDTH_POINT, START_HEIGHT_POINT+ELEMENT_HEIGHT*5+SPACE*3, 
				85, 14);
		getContentPane().add(description);
		jt_description = new JTextArea();
		jt_description.setBorder(BorderFactory.createLineBorder(Color.GRAY)); //встановлюємо сірий ободок
		jt_description.setWrapStyleWord(true);
		jt_description.setRows(3);
		jt_description.setLineWrap(true); //переносить текст на наступний рядок
		jt_description.setBounds(126, START_HEIGHT_POINT+ELEMENT_HEIGHT*5+SPACE*3, 142, 49);
		getContentPane().add(jt_description);
		
		producer = new JLabel("Виробник:");
		producer.setBounds(START_WIDTH_POINT, START_HEIGHT_POINT+ELEMENT_HEIGHT*8+SPACE*4, 
				85, 14);
		getContentPane().add(producer);
		jt_producer = new JTextField(20);
		jt_producer.setBounds(124, START_HEIGHT_POINT+ELEMENT_HEIGHT*8+SPACE*4-3, 
				146, TEXTFIELD_HEIGHT);
		getContentPane().add(jt_producer);
		
		price = new JLabel("Ціна, грн");
		price.setBounds(START_WIDTH_POINT, START_HEIGHT_POINT+ELEMENT_HEIGHT*9+SPACE*5,
				60, 14);
		getContentPane().add(price);
		jt_price = new JTextField(20);
		jt_price.setBounds(124, START_HEIGHT_POINT+ELEMENT_HEIGHT*9+SPACE*5-3, 
				146, TEXTFIELD_HEIGHT);
		getContentPane().add(jt_price);
		
		quantity = new JLabel("Кількість*:");
		quantity.setBounds(START_WIDTH_POINT, START_HEIGHT_POINT+ELEMENT_HEIGHT*10+SPACE*6, 
				90, 14);
		getContentPane().add(quantity);
		jt_quantity = new JTextField();
		jt_quantity.setBounds(124, START_HEIGHT_POINT+ELEMENT_HEIGHT*10+SPACE*6-3, 
				146, TEXTFIELD_HEIGHT);
		getContentPane().add(jt_quantity);
		
		measureType = new JLabel("Одиниця виміру*:");
		measureType.setBounds(START_WIDTH_POINT, START_HEIGHT_POINT+ELEMENT_HEIGHT*11+SPACE*7, 
				100, 14);
		getContentPane().add(measureType);
		jt_measureType = new JTextField();
		jt_measureType.setBounds(124, START_HEIGHT_POINT+ELEMENT_HEIGHT*11+SPACE*7-3, 
				146, TEXTFIELD_HEIGHT);
		getContentPane().add(jt_measureType);
		
		ok = new JButton ("OK");
		ok.setBounds(30, 367, 100, 23);
		getContentPane().add(ok);
		ok.addActionListener(available_handler);
		Main.mainWindow.goodsTable.updateUI();
		
		cancel = new JButton ("Скасувати");
		cancel.setBounds(160, 367, 100, 23);
		getContentPane().add(cancel);
		cancel.addActionListener(available_handler);
		
	}
    
	public class eHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == ok){
				if (jt_name.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Введіть дані в поле \"Найменування\"");
					return;
				}
				if (jt_quantity.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Введіть дані в поле \"Кількість\"");
					return;
				}
				if (jt_measureType.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Введіть дані в поле \"Одиниця виміру\"");
					return;
				}
				if (!isDouble(jt_price.getText())) {
					JOptionPane.showMessageDialog(null, "Invalid price format");
					return;
				}
				if (!isDouble(jt_quantity.getText())) {
					JOptionPane.showMessageDialog(null, "Invalid quantity format");
					return;
				}
				
				else
					setResult();
					dispose(); // прибрати вікно
			}
			
			if (e.getSource() == cancel){
				dispose(); // прибрати вікно
			}
			
			if (e.getSource() == comboGroup){
				String[] string = Main.mainWindow.getSubgroupNamesByGroupID(comboGroup.getSelectedIndex() + 1);
				comboSubgroup.setModel(new DefaultComboBoxModel(string));
			}
		}
	}
	
	//метод замінює введені коми на точки (потрібно для чисел double)
	String setCommas(String str){
		if (str.contains(","))
			str = str.replace (",", ".");
		return str;
	}
	
	void setResult(){
		//перевіряємо наявність у базі аналогічного найменування
		String temp_str = jt_name.getText();
		for (int i = 0; i < Main.mainWindow.goods.size(); i++){
			Goods goods = Main.mainWindow.goods.get(i);
			String str2 = goods.getName();
			if (temp_str.equals(str2)){
				JOptionPane.showMessageDialog(null, "Запис із таким найменуванням вже існує!");
				Main.mainWindow.goodsTable.setRowSelectionInterval(i, i); //виділяємо рядок з аналогічним найменуванням
				return;
			}
		}
		
		//якщо аналогічного найменування не знайдено - готуємо об'єкт - новий товар
		int subgroupID = Main.mainWindow.getSubgroupIDByName((String) comboSubgroup.getSelectedItem());
		
		//перевіряємо, чи не введено у числі випадково кому замість крапки
		String quantity = setCommas(jt_quantity.getText());
		String price = setCommas(jt_price.getText());
		if (price.equals("")) price = "0";
		//если товаров еще нет - приссваиваем первому ID = 1, если есть - ID последнего в списке +1
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
