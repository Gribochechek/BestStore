package dialogWindows;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import main.Main;
import streams.GoodsWriter;
import streams.SubgroupsWriter;

public class WindowSubgroupDelete extends WindowGroupDelete{

	public WindowSubgroupDelete(Frame parent) {
		super(parent);
		title.setText("Delete Subgroup");
		labelChose.setText("Chose subgroup:");
		
		
		String[] string = Main.mainWindow.getSubgroupNames();
		allGroups.setModel(new DefaultComboBoxModel(string));
		allGroups.setBounds(120, 35, 160, 30);
		allGroups.addActionListener(new listener2());
		getContentPane().add(allGroups);
		
	}
	
	public void setResult() {
		
int tempSubgroupID = Main.mainWindow.subgroups.get(indexOfTempGroopInArrayList).getSubgroupID();
		
		for(int i=0;i<Main.mainWindow.goods.size();){
			if(Main.mainWindow.goods.get(i).getSubgroupID()==tempSubgroupID)
				Main.mainWindow.goods.remove(i);
				else i++;
		}
		Main.mainWindow.subgroups.remove(indexOfTempGroopInArrayList);
		
		GoodsWriter goodsw = new GoodsWriter();
		goodsw.saveGoodsInFile(Main.mainWindow.goods);
		
		SubgroupsWriter sgw = new SubgroupsWriter();
		sgw.saveSubgroupsInFile(Main.mainWindow.subgroups);
		
		if(Main.mainWindow.subgroups.size()==0){
		Main.mainWindow.bSubgroupEdit.setEnabled(false);
		Main.mainWindow.bSubgroupRemove.setEnabled(false);
		Main.mainWindow.bGoodsAdd.setEnabled(false);
		Main.mainWindow.bGoodsEdit.setEnabled(false);
		Main.mainWindow.bGoodsRemove.setEnabled(false);
		Main.mainWindow.bSearch.setEnabled(false);
		Main.mainWindow.bStatistic.setEnabled(false);
		Main.mainWindow.bSale.setEnabled(false);
		Main.mainWindow.bExport.setEnabled(false);
		Main.mainWindow.bIncome.setEnabled(false);
		}
		WindowGoodsDelete.disableGoodsButtons();
	}

	class listener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == allGroups) {
				
				indexOfTempGroopInArrayList = allGroups.getSelectedIndex();
				
			}
		}
	}
	
}
