package mainWindows;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class StartWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public final static int width = (int) screenSize.getWidth();
	public final static int height = (int) screenSize.getHeight();

	int stWinSize = 300;// розмір вікна
	JLabel title, user, password; // оголошення написів
	JComboBox<String> userChoser; // оголошення випадаючого списка ролей
	JTextField userName;
	JPasswordField passField;
	JButton ok, cancel;

	MainWindow mW = new MainWindow();

	final int START_HEIGHT_POINT = 100;
	final int START_WIDTH_POINT = 10;
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
		title.setSize(100, 30);
		title.setLocation(getWidth() / 2 - title.getWidth() / 2, 11);
		add(title);

		user = new JLabel("Користувач:");
		user.setBounds(START_WIDTH_POINT * 2, START_HEIGHT_POINT, 85,
				ELEMENT_HEIGHT);
		add(user);

		userName = new JTextField();
		userName.setBounds(95, START_HEIGHT_POINT, 150, TEXTFIELD_HEIGHT);
		add(userName);

		password = new JLabel("Пароль:");
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(START_WIDTH_POINT * 2, START_HEIGHT_POINT
				+ ELEMENT_HEIGHT + SPACE, 85, ELEMENT_HEIGHT);
		add(password);

		passField = new JPasswordField();
		passField.setBounds(95, START_HEIGHT_POINT + ELEMENT_HEIGHT + SPACE,
				150, TEXTFIELD_HEIGHT);
		add(passField);

		ok = new JButton("OK");
		ok.setSize(100, 23);
		ok.setLocation(25, 200);
		add(ok);
		// ok.addActionListener(handler);

		cancel = new JButton("Вийти");
		cancel.setSize(100, 23);
		cancel.setLocation(175, 200);
		add(cancel);
		// cancel.addActionListener(handler);

	}

	boolean checkPassword() {
		char[] correctPass = { '1' };
		char[] pass = passField.getPassword();

		if (pass.length != correctPass.length)
			return false;

		else
			for (int i = 0; i < correctPass.length; i++) {
				if (pass[i] != correctPass[i])
					return false;
			}
		return true;

	}

	public class eHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == ok) {

				if (checkPassword())

					mW.setVisible(true);

				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Введено невірний пароль");
				return;
			}
			if (e.getSource() == cancel) {

				System.exit(0);
			}
		}
	}
}
