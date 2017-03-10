package main;

import dialogWindows.WindowGroupAdd;
import mainWindows.MainWindow;
import mainWindows.StartWindow;

public class Main {
	
	public static MainWindow mainWindow;
	public static StartWindow startWindow;
	
	public static void main(String[] args) {
		
		//StartWindow stWin = new StartWindow("Best Store Login");

		//stWin.setVisible(true);
		
		//WindowGroupAdd addGroup = new WindowGroupAdd(null);
		//addGroup.setVisible(true);
		
		mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}

}
