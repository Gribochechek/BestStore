package dialogWindows;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.Main;

public class WindowSearch extends JDialog {
	JLabel title, title2;
	JButton bSearch, bCancel;
	JTextField jt_search;

	public WindowSearch(Frame parent) {
		super(parent, true);
		setLocation(500, 200);
		setSize(300, 200);
		setResizable(false);
		getContentPane().setLayout(null);

		title = new JLabel("Search");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(42, 10, 200, 30);
		getContentPane().add(title);

		title2 = new JLabel("Enter keyword: ");

		title2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title2.setBounds(10, 70, 200, 30);
		getContentPane().add(title2);

		jt_search = new JTextField(20);
		jt_search.setBounds(100, 70, 150, 25);
		getContentPane().add(jt_search);

		bSearch = new JButton("Search");
		bSearch.setBounds(30, 130, 100, 23);
		getContentPane().add(bSearch);
		bSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String keyword = jt_search.getText();
				while (!keyword.isEmpty() && keyword.charAt(0) == ' ')
					keyword = keyword.substring(1);

				if (keyword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Textfield is Empty");
				} else {
					if (keyword.length() < 3) {
						JOptionPane.showMessageDialog(null, "Enter at least 3 symbols");
					} else {

						dispose();
						WindowSearchResult sr = new WindowSearchResult(Main.mainWindow, keyword);
						sr.setVisible(true);
					}
				}

			}

		});

		bCancel = new JButton("Cancel");
		bCancel.setBounds(160, 130, 100, 23);
		getContentPane().add(bCancel);
		bCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}

		});

	}
}
