package main;

import java.io.IOException;

import javax.swing.JFrame;

import dialogWindows.WindowGoodsAdd;
import dialogWindows.WindowGoodsDelete;
import dialogWindows.WindowGroupAdd;
import dialogWindows.WindowGroupDelete;
import dialogWindows.WindowSubgroupAdd;
import mainWindows.MainWindow;
import mainWindows.StartWindow;

public class Main {
	
	public static MainWindow mainWindow;
	public static StartWindow startWindow;
	
	public static void main(String[] args) throws IOException {
		
		//StartWindow stWin = new StartWindow("Best Store Login");

		//stWin.setVisible(true);
		
		//WindowGroupAdd addGroup = new WindowGroupAdd(null);
		//addGroup.setVisible(true);
		
		mainWindow = new MainWindow();
		mainWindow.setVisible(true);

			
	}

}
