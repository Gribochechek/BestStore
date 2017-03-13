package dialogWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//import com.jgoodies.forms.factories.DefaultComponentFactory;

import main.Main;
import objectsForStore.Group;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.border.LineBorder;

public class WindowGroupAdd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	public boolean result;
	
	JButton okButton, cancelButton;
	JPanel buttonPane;
	JTextArea groupName;
	JLabel titleGroup, labelGroupName;
	Listener okCancel = new Listener(); 
	

	public WindowGroupAdd(Frame parent) {
		
		super(parent, true);		
		
		titleGroup = new JLabel("Додати нову групу товарів");
		titleGroup.setHorizontalAlignment(SwingConstants.CENTER); //розташовуємо напис по центру
		titleGroup.setFont(new Font("Verdana", Font.BOLD, 12));
		titleGroup.setBounds(42, 11, 226, 28);
		getContentPane().add (titleGroup);
		
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new LineBorder(Color.GRAY));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			labelGroupName = new JLabel("Type group name:");
			labelGroupName.setFont(new Font("Tahoma", Font.PLAIN, 21));
			contentPanel.add(labelGroupName);
		}
		{
			groupName = new JTextArea();
			groupName.setRows(10);
			groupName.setColumns(20);
			groupName.setBounds(126, 63, 142, 49);
			groupName.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			contentPanel.add(groupName);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setToolTipText("Confirm");
				buttonPane.add(okButton);
				okButton.addActionListener(okCancel);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setToolTipText("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(okCancel);
			}
		}
	}
	
	class Listener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			
			if (ae.getSource() == okButton){
				result = true;
				dispose();
			}
			
			if (ae.getSource() == cancelButton){
				result = false;
				dispose();
			}
		}
	}

	public void setResult() {
		//Тут має щось бути
	}

}
