package dialogWindows;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Main;
import objectsForStore.Group;
import objectsForStore.Subgroup;
import streams.GroupsWriter;

public class WindowGroupEdit extends WindowGroupAdd {

	JComboBox allGroups = new JComboBox();
	JLabel groupDescription = new JLabel();
	String oldText;
	int indexOfTempGroopInArrayList=0;
	Group tempGroup;
	listener l = new listener();

	public WindowGroupEdit(Frame parent) {
		super(parent);

		title.setText("Edit group");
		jt_name.setText(Main.mainWindow.groups.get(0).getGroupName());
		jt_description.setText(Main.mainWindow.groups.get(0).getGroupDesc());

		groupDescription.setText("Chose group:");
		groupDescription.setBounds(10, 35, 100, 30);
		getContentPane().add(groupDescription);

		String[] string = Main.mainWindow.getGroupNames();
		allGroups.setModel(new DefaultComboBoxModel(string));
		allGroups.setBounds(120, 35, 160, 30);
		allGroups.addActionListener(l);
		getContentPane().add(allGroups);
		oldText = jt_name.getText().toLowerCase();

	}

	class listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == allGroups) {
				jt_name.setText(Main.mainWindow.groups.get(allGroups.getSelectedIndex()).getGroupName());
				oldText = jt_name.getText().toLowerCase();
				indexOfTempGroopInArrayList = allGroups.getSelectedIndex();
				jt_description.setText(Main.mainWindow.groups.get(allGroups.getSelectedIndex()).getGroupDesc());
			}
		}
	}

	public void setResult() {
		// перевіряємо наявність у базі аналогічного найменування
		String temp_str = jt_name.getText();
		if (!oldText.equals(temp_str)) {
			for (Group gr : Main.mainWindow.groups) {
				if(gr.getGroupName().toLowerCase().equals(temp_str)){
					JOptionPane.showMessageDialog(null, "Запис із таким найменуванням вже існує!");
					return;
				}		
			}
			for (Subgroup gr : Main.mainWindow.subgroups) {
				if(gr.getSubgroupName().toLowerCase().equals(temp_str)){
					JOptionPane.showMessageDialog(null, "Запис із таким найменуванням вже існує!");
					return;
				}		
			}
		}
		
		
		tempGroup = new Group(Main.mainWindow.groups.get(indexOfTempGroopInArrayList).getGroupID(), jt_name.getText() , jt_description.getText());
		Main.mainWindow.groups.remove(indexOfTempGroopInArrayList);
		Main.mainWindow.groups.add(indexOfTempGroopInArrayList, tempGroup);
		
		gw.saveGroupsInFile(Main.mainWindow.groups);
		
		

		
	}

}
