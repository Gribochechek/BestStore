package dialogWindows;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import main.Main;
import objectsForStore.Goods;

public class WindowSearchResult extends JDialog {

	JLabel title;
	JScrollPane jsp;
	JPanel panel;

	JButton ok, cancel;
	ArrayList<Goods> searchResultList = new ArrayList<Goods>();
	ButtonGroup jradioList;
	JRadioButton jrb[];

	public WindowSearchResult(Frame parent, String keyword) {
		super(parent, true);
		for (int i = 0; i < Main.mainWindow.goods.size(); i++) {
			if (Main.mainWindow.goods.get(i).getName().toLowerCase().contains(keyword)
					|| Main.mainWindow.goods.get(i).getMaker().toLowerCase().contains(keyword)
					|| Main.mainWindow.goods.get(i).getDesc().toLowerCase().contains(keyword))
				searchResultList.add(Main.mainWindow.goods.get(i));
		}
		setLocation(500, 200);
		setSize(300, 200 + 25 * searchResultList.size());
		setResizable(false);
		getContentPane().setLayout(null);

		title = new JLabel("Searching results: ");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 12));
		title.setBounds(42, 10, 226, 30);
		getContentPane().add(title);
		if (searchResultList.isEmpty()) {
			JLabel title = new JLabel("No results");
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setFont(new Font("Times New Roman", Font.BOLD, 20));
			title.setBounds(42, 50, 226, 30);
			getContentPane().add(title);

			cancel = new JButton("Close");
			cancel.setBounds(100, 120 + 25 * searchResultList.size(), 100, 23);
			getContentPane().add(cancel);
			cancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();

				}

			});

		} else {
			jradioList = new ButtonGroup();
			jrb = new JRadioButton[searchResultList.size()];
			
			for (int i = 0; i <= searchResultList.size() - 1; i++) {
				JRadioButton button = new JRadioButton(searchResultList.get(i).getName());
				button.setBounds(20, 45 + i * 25, 250, 23);
				jradioList.add(button);
				jrb[i] = button;
				getContentPane().add(button);
			}

			ok = new JButton("Show");
			ok.setBounds(30, 120 + 25 * searchResultList.size(), 100, 23);
			getContentPane().add(ok);
			ok.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int tempID = 0, rawIndex = 0;
					for (int i = 0; i < searchResultList.size(); i++) {
						if (jrb[i].isSelected()) {
							tempID = searchResultList.get(i).getID();
							break;
						}
					}
					for (int i = 0; i < Main.mainWindow.goods.size(); i++) {
						if (Main.mainWindow.goods.get(i).getID() == tempID) {
							rawIndex = Main.mainWindow.goods.indexOf(Main.mainWindow.goods.get(i));
						}
					}
					Main.mainWindow.radio1.setSelected(true);
					Main.mainWindow.goodsTable.setModel(Main.mainWindow.goodsModel);
					Main.mainWindow.goodsTable.setRowSelectionInterval(rawIndex, rawIndex);
					dispose();
				}

			});

			cancel = new JButton("Cancel");
			cancel.setBounds(160, 120 + 25 * searchResultList.size(), 100, 23);
			getContentPane().add(cancel);
			cancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();

				}

			});

		}
	}

}
