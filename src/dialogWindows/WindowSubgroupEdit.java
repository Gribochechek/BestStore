package dialogWindows;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import main.Main;
import objectsForStore.Group;
import objectsForStore.Subgroup;
import streams.SubgroupsWriter;

public class WindowSubgroupEdit extends WindowGroupEdit {

	Subgroup tempSubgroup;
	int tempID = 0;

	public WindowSubgroupEdit(Frame parent) {
		super(parent);
		title.setText("Edit subgroup");
		getContentPane().remove(jt_description);
		getContentPane().remove(labelGroupDescription);

		String[] string = Main.mainWindow.getSubgroupNames();
		allGroups.setModel(new DefaultComboBoxModel(string));
		allGroups.setBounds(120, 35, 160, 30);
		allGroups.removeActionListener(l);
		allGroups.addActionListener(new listener2());

		jt_name.setText(Main.mainWindow.subgroups.get(0).getSubgroupName());
		oldText = jt_name.getText().toLowerCase();

	}

	class listener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == allGroups) {
				jt_name.setText(Main.mainWindow.subgroups.get(allGroups.getSelectedIndex()).getSubgroupName());
				tempID = Main.mainWindow.subgroups.get(allGroups.getSelectedIndex()).getSubgroupID();
				oldText = jt_name.getText().toLowerCase();
				indexOfTempGroopInArrayList = allGroups.getSelectedIndex();
			}
		}
	}

	public void setResult() {
		String temp_str = jt_name.getText().toLowerCase().trim();

		if (!oldText.equals(temp_str)) {
			for (Group gr : Main.mainWindow.groups) {
				if(gr.getGroupName().toLowerCase().equals(temp_str)){
					JOptionPane.showMessageDialog(null, "Such entry already exists!");
					return;
				}		
			}
			for (Subgroup gr : Main.mainWindow.subgroups) {
				if(gr.getSubgroupName().toLowerCase().equals(temp_str)){
					JOptionPane.showMessageDialog(null, "Such entry already exists!");
					return;
				}		
			}
		}

		tempSubgroup = new Subgroup(Main.mainWindow.subgroups.get(indexOfTempGroopInArrayList).getGroupID(),
				Main.mainWindow.subgroups.get(indexOfTempGroopInArrayList).getSubgroupID(), jt_name.getText().trim());
		Main.mainWindow.subgroups.remove(indexOfTempGroopInArrayList);
		Main.mainWindow.subgroups.add(indexOfTempGroopInArrayList, tempSubgroup);

		SubgroupsWriter sgw = new SubgroupsWriter();
		sgw.saveSubgroupsInFile(Main.mainWindow.subgroups);

	}

}
