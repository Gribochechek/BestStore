package dialogWindows;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dialogWindows.WindowGroupAdd.eHandler;
import dialogWindows.WindowGroupEdit.listener;
import main.Main;
import streams.GoodsWriter;
import streams.GroupsWriter;
import streams.SubgroupsWriter;

public class WindowGroupDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JLabel warning = new JLabel();
	JLabel labelChose = new JLabel();
	JLabel title = new JLabel();
	int indexOfTempGroopInArrayList;
	JComboBox allGroups = new JComboBox();
	eHandler available_handler = new eHandler();
	public boolean result;
	
	JButton ok, cancel;

	public WindowGroupDelete(Frame parent) {
		super(parent, true);
		
		setLocation(500, 200);
		setSize(300, 260);
		setResizable(false);
		getContentPane().setLayout(null);

		title.setText("Delete Group");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(100, 8, 100, 30);
		getContentPane().add (title);
		
		labelChose.setText("Chose group:");
		labelChose.setBounds(10, 35, 100, 30);
		getContentPane().add(labelChose);
		
		String[] string = Main.mainWindow.getGroupNames();
		allGroups.setModel(new DefaultComboBoxModel(string));
		allGroups.setBounds(120, 35, 160, 30);
		allGroups.addActionListener(new listener());
		getContentPane().add(allGroups);

		warning.setText("Warning! You will delete all Goods from this group!");
		warning.setBounds(30, 80, 250, 30);
		getContentPane().add(warning);
		
		
		ok = new JButton ("OK");
		ok.setBounds(30, 190, 100, 23);
		getContentPane().add(ok);
		ok.addActionListener(available_handler);
		
		
		cancel = new JButton ("Cancel");
		cancel.setBounds(160, 190, 100, 23);
		getContentPane().add(cancel);
		cancel.addActionListener(available_handler);
		
		
	}
	
	
	class listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == allGroups) {

				indexOfTempGroopInArrayList = allGroups.getSelectedIndex();
				
			}
		}
	}
	
	public void setResult() {
		int tempGroupID = Main.mainWindow.groups.get(indexOfTempGroopInArrayList).getGroupID();
		
		for(int i=0;i<Main.mainWindow.goods.size();){
			if(Main.mainWindow.goods.get(i).getGroupID()==tempGroupID)
				Main.mainWindow.goods.remove(i);
				else i++;
		}
		
		for(int i=0;i<Main.mainWindow.subgroups.size();){
			
			if(Main.mainWindow.subgroups.get(i).getGroupID()==tempGroupID){
				Main.mainWindow.subgroups.remove(i);
				
			}
			else i++;
			
		}
		
		GoodsWriter goodsw = new GoodsWriter();
		goodsw.saveGoodsInFile(Main.mainWindow.goods);
		
		SubgroupsWriter sgw = new SubgroupsWriter();
		sgw.saveSubgroupsInFile(Main.mainWindow.subgroups);
		
		Main.mainWindow.groups.remove(indexOfTempGroopInArrayList);
		GroupsWriter gw = new GroupsWriter();
		gw.saveGroupsInFile(Main.mainWindow.groups);
		
		
		
	}
	
	public class eHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == ok){
				
				
				   
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
