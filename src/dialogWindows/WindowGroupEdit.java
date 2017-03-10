package dialogWindows;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

public class WindowGroupEdit extends WindowGroupAdd {
	
	JComboBox allGroups = new JComboBox();
	JLabel newGroupName = new JLabel();

	public WindowGroupEdit(Frame parent) {
		super(parent);
		setSize(300, 310);
		
		titleGroup.setText("Edit group");
		
		groupName.setText("Chose group to edit:");
		groupName.setSize(190, 14);
		groupName.setLocation(getWidth()/2 - groupName.getWidth()/2, 63);
		
		String[] string = Main.mainWindow.getGroupNames();
		allGroups.setModel(new DefaultComboBoxModel(string));
		allGroups.setSize(170, 24);
		allGroups.setLocation(getWidth()/2 - allGroups.getWidth()/2, 88);
		allGroups.addActionListener(new listener());
		getContentPane().add(allGroups);
		
		newGroupName.setText("Enter new name:");
		newGroupName.setSize(110, 14);
		newGroupName.setLocation(getWidth()/2 - newGroupName.getWidth()/2, 138);
		getContentPane().add(newGroupName);
		
		groupName.setText((String) allGroups.getSelectedItem());
		groupName.setLocation(getWidth()/2 - groupName.getWidth()/2, 163);
		
		okButton.setLocation(30, 240);
		cancelButton.setLocation(160, 240);
	}
	
	class listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == allGroups){
				groupName.setText((String) allGroups.getSelectedItem());
			}
		}
	}
	
	public void setResult() {
		//Тут має щось бути
	}

}
