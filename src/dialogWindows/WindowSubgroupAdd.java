package dialogWindows;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import main.Main;
import objectsForStore.Group;
import objectsForStore.Subgroup;
import streams.SubgroupsWriter;

public class WindowSubgroupAdd extends WindowGroupAdd{
	
	JLabel subgroupDescription = new JLabel();
	JComboBox allGroups = new JComboBox();
	int groupID;
	Subgroup subgroup;
	SubgroupsWriter sgw = new SubgroupsWriter();

	public WindowSubgroupAdd(Frame parent) {
		super(parent);
		title.setText("Add new Subgroup");
		labelGroupName.setText("Subgroup Name:");
		subgroupDescription.setText("Chose group:");
		subgroupDescription.setBounds(10, 35, 100, 30);
		getContentPane().add(subgroupDescription);
		getContentPane().remove(jt_description);
		getContentPane().remove(labelGroupDescription);
		
		
		String[] string = Main.mainWindow.getGroupNames();
		allGroups.setModel(new DefaultComboBoxModel(string));
		allGroups.setBounds(120, 35, 160, 30);
		allGroups.addActionListener(new listener());
		getContentPane().add(allGroups);

	}

	class listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == allGroups) {
				groupID =  Main.mainWindow.groups.get(allGroups.getSelectedIndex()).getGroupID();
				
			}
		}
	}
		
	
	public void setResult() {
		

		String temp_str = jt_name.getText().trim();
		for (int i = 0; i < Main.mainWindow.subgroups.size(); i++){
			Subgroup group = Main.mainWindow.subgroups.get(i);
			String str2 = group.getSubgroupName();
			if (temp_str.toLowerCase().equals(str2.toLowerCase())){
				JOptionPane.showMessageDialog(null, "Such entry already exists!");
				
				return;
			}
		}
		for (Group sgr : Main.mainWindow.groups) {
			if (temp_str.equals(sgr.getGroupName().toLowerCase())){
				JOptionPane.showMessageDialog(null, "Such entry already exists!");
				
				return;
			}
		}
		
	
		groupID =  Main.mainWindow.groups.get(allGroups.getSelectedIndex()).getGroupID();
		if(Main.mainWindow.subgroups.size()>0){
			subgroup = new Subgroup(groupID, Main.mainWindow.subgroups.get(Main.mainWindow.subgroups.size()-1).getSubgroupID()+1, jt_name.getText().trim());
		}
		else{
			subgroup = new Subgroup(groupID, 1, jt_name.getText().trim());
		}
		Main.mainWindow.subgroups.add(subgroup);
		
		sgw.saveSubgroupsInFile(Main.mainWindow.subgroups);
		Main.mainWindow.bSubgroupEdit.setEnabled(true);
		Main.mainWindow.bSubgroupRemove.setEnabled(true);
		Main.mainWindow.bGoodsAdd.setEnabled(true);
		
	}

}
