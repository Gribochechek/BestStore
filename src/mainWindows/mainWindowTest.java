package mainWindows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTree;

import objectsForStore.Goods;
import objectsForStore.Group;
import objectsForStore.SaleGoods;
import objectsForStore.Subgroup;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class mainWindowTest {

	private JFrame frame;
	private JTable table;
	
	//------------------------------------------------
	
	private static final int SPACE = 10;
	
	//------------------------------------------------
	
	public ArrayList<Group> groups;
	public ArrayList<Subgroup> subgroups;
	public ArrayList<Goods> goods;
	public ArrayList<SaleGoods> soldGoods;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindowTest window = new mainWindowTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindowTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTree tree = new JTree();
		frame.getContentPane().add(tree, BorderLayout.WEST);
		
		table = new JTable();
		frame.getContentPane().add(table, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setSize(500, 400);
		
		groups = new ArrayList<Group>();
     	subgroups = new ArrayList<Subgroup>();
     	goods = new ArrayList<Goods>();
     	soldGoods = new ArrayList<SaleGoods>();
		
		JButton btnNewButton = new JButton("Add Group");
		btnNewButton.setToolTipText("Додати групу товарів");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete Group");
		btnNewButton_1.setToolTipText("Видалити групу товарів");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Edit Group");
		btnNewButton_2.setToolTipText("Редагувати групу товарів");
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Add SubG");
		btnNewButton_3.setToolTipText("Додати підгрупу товарів");
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Delete SubG");
		btnNewButton_4.setToolTipText("Видалити підгрупу товарів");
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Edit SubG");
		btnNewButton_5.setToolTipText("Редагувати підгрупу товарів");
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Add Good");
		btnNewButton_6.setToolTipText("Додати товар");
		panel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Delete Good");
		btnNewButton_7.setToolTipText("Видалити товар");
		panel.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Edit Good");
		btnNewButton_8.setToolTipText("Редагувати товар");
		panel.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Search");
		btnNewButton_9.setToolTipText("Шукати товар");
		panel.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("Statistic");
		btnNewButton_10.setToolTipText("Показати статистику");
		panel.add(btnNewButton_10);
	}
}
