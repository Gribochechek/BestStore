package dialogWindows;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.Main;

public class WindowStatisticGoods extends JDialog {

	JLabel title, lTotalValue, lGroupStat, lGroupStatChoose, lGroupStatResult, lSubGroupStat,lSubgroupStatChoose,lSubgroupStatResult;
	JComboBox comboGroup, comboSubgroup;
	int totalGroupValue = 0, totalSubgroupValue=0;
	JButton ok;
	String[] groupsList = Main.mainWindow.getGroupNames();
	String[] subgroupsList = Main.mainWindow.getSubgroupNames();

	public WindowStatisticGoods(Frame parent) {
		super(parent, true);
		setLocation(500, 200);
		setSize(320, 300);
		setResizable(false);
		getContentPane().setLayout(null);

		title = new JLabel("Statistics");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(42, 10, 200, 30);
		getContentPane().add(title);
		int totalValue = 0;
		for (int i = 0; i < Main.mainWindow.goods.size(); i++) {
			double temp = Main.mainWindow.goods.get(i).getPrice() * Main.mainWindow.goods.get(i).getQuantity();
			totalValue += temp;
		}

		lTotalValue = new JLabel("Total value of All goods in stock: " + totalValue + " uah");
		lTotalValue.setHorizontalAlignment(SwingConstants.CENTER);
		lTotalValue.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lTotalValue.setBounds(10, 30, 280, 30);
		getContentPane().add(lTotalValue);

		lGroupStat = new JLabel("Group Statistics");
		lGroupStat.setHorizontalAlignment(SwingConstants.CENTER);
		lGroupStat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lGroupStat.setBounds(42, 60, 200, 30);
		getContentPane().add(lGroupStat);

		lGroupStatChoose = new JLabel("Choose group:");
		lGroupStatChoose.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lGroupStatChoose.setBounds(10, 90, 100, 30);
		getContentPane().add(lGroupStatChoose);

		comboGroup = new JComboBox();
		comboGroup.setModel(new DefaultComboBoxModel(groupsList));
		comboGroup.setBounds(120, 90, 180, 25);
		getContentPane().add(comboGroup);
		comboGroup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int tempGroupID = comboGroup.getSelectedIndex();
				for(int i=0;i<Main.mainWindow.goods.size();i++){
					if(Main.mainWindow.goods.get(i).getGroupID()==Main.mainWindow.groups.get(tempGroupID).getGroupID()){
						totalGroupValue +=Main.mainWindow.goods.get(i).getPrice()*Main.mainWindow.goods.get(i).getQuantity();
					}
				}
				lGroupStatResult.setText("Total value of All goods in this group: " + totalGroupValue + " uah");
				totalGroupValue=0;
			}

		});
		
		int tempGroupID = Main.mainWindow.groups.get(0).getGroupID();
		for(int i=0;i<Main.mainWindow.goods.size();i++){
			if(Main.mainWindow.goods.get(i).getGroupID()==tempGroupID){
				totalGroupValue +=Main.mainWindow.goods.get(i).getPrice()*Main.mainWindow.goods.get(i).getQuantity();
			}
		}
		
		lGroupStatResult = new JLabel("Total value of All goods in this group: " + totalGroupValue + " uah");
		totalGroupValue = 0;
		lGroupStatResult.setHorizontalAlignment(SwingConstants.CENTER);
		lGroupStatResult.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lGroupStatResult.setBounds(10, 120, 280, 30);
		getContentPane().add(lGroupStatResult);

		lSubGroupStat = new JLabel("Subgroup Statistics");
		lSubGroupStat.setHorizontalAlignment(SwingConstants.CENTER);
		lSubGroupStat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lSubGroupStat.setBounds(42, 150, 200, 30);
		getContentPane().add(lSubGroupStat);

		lSubgroupStatChoose = new JLabel("Choose subgroup:");
		lSubgroupStatChoose.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lSubgroupStatChoose.setBounds(10, 180, 280, 30);
		getContentPane().add(lSubgroupStatChoose);

		comboSubgroup = new JComboBox();
		comboSubgroup.setModel(new DefaultComboBoxModel(subgroupsList));
		comboSubgroup.setBounds(120, 180, 180, 25);
		getContentPane().add(comboSubgroup);
		comboSubgroup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				int tempSubroupID = comboSubgroup.getSelectedIndex();
				for(int i=0;i<Main.mainWindow.goods.size();i++){
					if(Main.mainWindow.goods.get(i).getSubgroupID()==Main.mainWindow.subgroups.get(tempSubroupID).getSubgroupID()){
						totalSubgroupValue +=Main.mainWindow.goods.get(i).getPrice()*Main.mainWindow.goods.get(i).getQuantity();
					}
				}
				lSubgroupStatResult.setText("Total value of All goods in this Subgroup: " + totalSubgroupValue + " uah");
				totalSubgroupValue=0;
				
				
			}

		});
		
		int tempSubgroupID = Main.mainWindow.subgroups.get(0).getSubgroupID();
		for(int i=0;i<Main.mainWindow.goods.size();i++){
			if(Main.mainWindow.goods.get(i).getSubgroupID()==tempSubgroupID){
				totalSubgroupValue +=Main.mainWindow.goods.get(i).getPrice()*Main.mainWindow.goods.get(i).getQuantity();
			}
		}

		lSubgroupStatResult = new JLabel("Total value of All goods in this subgroup: " + totalSubgroupValue + " uah");
		totalSubgroupValue = 0;
		lSubgroupStatResult.setHorizontalAlignment(SwingConstants.CENTER);
		lSubgroupStatResult.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lSubgroupStatResult.setBounds(10, 210, 280, 30);
		getContentPane().add(lSubgroupStatResult);
		
		
		ok = new JButton("Close");
		ok.setBounds(100, 240, 100, 23);
		getContentPane().add(ok);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}

		});

	}

}
