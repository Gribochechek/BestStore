package mainWindows;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class StartWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public final static int width = (int) screenSize.getWidth();
	public final static int height = (int) screenSize.getHeight();

	int stWinSize = 400;//розмыр вікна
	JLabel title, user, password; // оголошення написів заголовок. користувач. пароль
	JComboBox<String> userChoser; // оголошення випадаючого списку ролі користувача
	
	
	
	
	final int START_HEIGHT_POINT = 100;
	final int START_WIDTH_POINT = 50;
	final int ELEMENT_HEIGHT = 20;
	final int TEXTFIELD_HEIGHT = 25;
	final int SPACE = 15;

	public StartWindow(String s) {
		super(s);

		setSize(stWinSize, stWinSize);//
		setLocation(width / 2 - stWinSize / 2, height / 2 - stWinSize / 2);
		setResizable(false);
		getContentPane().setLayout(null);

		title = new JLabel("Авторизація");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 15));
		title.setSize(150, 30);
		title.setLocation(getWidth()/2 - title.getWidth()/2, 11);
		add (title);
		
		user = new JLabel("Користувач:");
		user.setBounds(START_WIDTH_POINT*2, START_HEIGHT_POINT, 85, ELEMENT_HEIGHT);
		getContentPane().add(user);
		
		userChoser = new JComboBox<String>();
		String string[] = {"Адміністратор", "Гість"};
		userChoser.setModel(new DefaultComboBoxModel<String>(string));
		userChoser.setBounds(195, START_HEIGHT_POINT, 146, TEXTFIELD_HEIGHT);
		//userCombo.addActionListener(handler); //обробник списку
		add(userChoser);
		

	}

	public static void main(String[] args) {

		StartWindow stWin = new StartWindow("Best Store Login");

		stWin.setVisible(true);

	}

}
