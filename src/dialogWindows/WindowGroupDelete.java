package dialogWindows;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import main.Main;

public class WindowGroupDelete extends WindowGroupAdd{

	JComboBox comboBox = new JComboBox<>();
	JLabel warning = new JLabel();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public WindowGroupDelete(Frame parent) {
		super(parent);
		setSize(300, 350);
		titleGroup.setText("Group Delete");
		
		labelGroupName.setText("Chose your group to delete:");
		labelGroupName.setSize(170, 14);
		labelGroupName.setLocation(getWidth()/2 - groupName.getWidth()/2, 63);
		
		remove(groupName);
		
		String[] string = Main.mainWindow.getGroupNames();
		comboBox.setModel(new DefaultComboBoxModel(string));
		comboBox.setSize(170, 15 + 10);
		comboBox.setLocation(getWidth()/2 - comboBox.getWidth()/2, 65 + 25);
		getContentPane().add(comboBox);
		
		warning.setText("Warning! You will delete all Goods from this group!");
		warning.setBounds(30, 185, 250, 30);
		getContentPane().add(warning);
		
		okButton.setLocation(30, 240);
		cancelButton.setLocation(160, 240);
	}
}
