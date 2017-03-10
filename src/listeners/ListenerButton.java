package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.WindowConstants;

import dialogWindows.WindowGroupAdd;
import main.Main;

public class ListenerButton implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Main.mainWindow.bGroupAdd){
			WindowGroupAdd dialog = new WindowGroupAdd(Main.mainWindow);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			if (dialog.result == true){
				dialog.setResult();
				Main.mainWindow.refreshComboBoxes();
			}
		}
		
	}

	
	
}
