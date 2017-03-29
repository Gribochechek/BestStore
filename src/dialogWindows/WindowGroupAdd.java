package dialogWindows;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import objectsForStore.Group;
import objectsForStore.Subgroup;
import streams.GroupsWriter;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.Main;

public class WindowGroupAdd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	public boolean result;
	
	JButton ok, cancel;
	JPanel buttonPane;
	JTextField jt_name, jt_description;
	JLabel title, labelGroupName, labelGroupDescription;
	eHandler available_handler = new eHandler();
	Group group;
	GroupsWriter gw = new GroupsWriter();

	public WindowGroupAdd(Frame parent) {
				
		super(parent, true);
		setLocation(500, 200);
		setSize(300, 260);
		setResizable(false);
		getContentPane().setLayout(null);
		
		title = new JLabel("Add new Group");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(100, 8, 100, 30);
		getContentPane().add (title);
		
		labelGroupName = new JLabel("Group Name*:");
		labelGroupName.setBounds(10, 70, 100, 30);
		getContentPane().add(labelGroupName);
		jt_name = new JTextField();
		jt_name.setBorder(BorderFactory.createLineBorder(Color.GRAY)); //встановлюємо сірий ободок
	
		jt_name.setBounds(120, 70, 160, 25);
		getContentPane().add(jt_name);
		
		labelGroupDescription = new JLabel("Description:");
		labelGroupDescription.setBounds(10, 120, 100, 30);
		getContentPane().add(labelGroupDescription);
		jt_description = new JTextField();
		jt_description.setBorder(BorderFactory.createLineBorder(Color.GRAY)); //встановлюємо сірий ободок
	
		jt_description.setBounds(120, 120, 160, 25);
		getContentPane().add(jt_description);
		
		
		ok = new JButton ("OK");
		ok.setBounds(30, 190, 100, 23);
		getContentPane().add(ok);
		ok.addActionListener(available_handler);
		Main.mainWindow.goodsTable.updateUI();
		
		cancel = new JButton ("Cancel");
		cancel.setBounds(160, 190, 100, 23);
		getContentPane().add(cancel);
		cancel.addActionListener(available_handler);
		
	}

	public void setResult() {
		//перевіряємо наявність у базі аналогічного найменування
				String temp_str = jt_name.getText().toLowerCase();
				for (int i = 0; i < Main.mainWindow.groups.size(); i++){
					Group group = Main.mainWindow.groups.get(i);
					String str2 = group.getGroupName().toLowerCase();
					if (temp_str.equals(str2)){
						JOptionPane.showMessageDialog(null, "Such entry already exists!");
						
						return;
					}
				}
					for (Subgroup sgr : Main.mainWindow.subgroups) {
						if (temp_str.equals(sgr.getSubgroupName().toLowerCase())){
							JOptionPane.showMessageDialog(null, "Such entry already exists!");
							
							return;
						}
					}
				
				
			
				//если групп еще нет - приссваиваем первой ID = 1, если есть - ID последнего в списке +1
				if(Main.mainWindow.groups.size()>0){
				group = new Group(Main.mainWindow.groups.get(Main.mainWindow.groups.size()-1).getGroupID()+1, jt_name.getText(), jt_description.getText());
				}
				else{
					group = new Group(1, jt_name.getText(), jt_description.getText());
				}
				Main.mainWindow.groups.add(group);
				
				
				gw.saveGroupsInFile(Main.mainWindow.groups);
				
				Main.mainWindow.bGroupEdit.setEnabled(true);
				Main.mainWindow.bGroupRemove.setEnabled(true);
				Main.mainWindow.bSubgroupAdd.setEnabled(true);
		
		
	
	}
	
	public class eHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == ok){
				if (jt_name.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Name format");
					return;
				}
				
				else
					result = true;
					dispose(); 
			}
			
			if (e.getSource() == cancel){
				result = false;
				dispose(); 
			}
			
		}
	}

}
