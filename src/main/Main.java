package main;

import java.io.IOException;

import mainWindows.MainWindow;
import mainWindows.StartWindow;

public class Main {
	
	public static MainWindow mainWindow;
	public static StartWindow startWindow;
	
	public static boolean isLogCorrect = false;

	public static void main(String[] args) throws IOException {
		
		StartWindow stWin = new StartWindow("Best Store Login");
		stWin.setVisible(true); 
		
		//mainWindow = new MainWindow();
		//mainWindow.setVisible(true);
		

			
	}

}
