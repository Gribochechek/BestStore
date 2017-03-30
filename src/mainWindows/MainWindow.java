package mainWindows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import listeners.ListenerButton;
import listeners.RadioButtonListener;
import objectsForStore.Goods;
import objectsForStore.Group;
import objectsForStore.SaleGoods;
import objectsForStore.Subgroup;
import streams.DeletedGoodsReader;
import streams.GoodsReader;
import streams.GroupsReader;
import streams.SaleGoodsReader;
import streams.SubgroupsReader;
import tableModels.TableModelGoods;
import tableModels.TableModelSaleGoods;

public class MainWindow extends JFrame implements ChangeListener {

	private JPanel mainPanel = new JPanel();
	private JPanel title = new JPanel();
	private JPanel listOfGroups = new JPanel();
	private JPanel listOfGoods = new JPanel();
	private JTabbedPane goodsAll = new JTabbedPane();
	private JPanel pFirstTab = new JPanel();
	private JPanel pSecondTab = new JPanel();

	// --------------------------------------------------------

	public JButton bGoodsAdd, bGoodsRemove, bGoodsEdit;
	public JButton bGroupAdd;
	public JButton bGroupRemove;
	public JButton bGroupEdit;
	public JButton bSubgroupAdd, bSubgroupRemove, bSubgroupEdit;
	public JButton bSearch, bStatistic, bExport, bSale, bIncome;
	public JButton bSaleRemove, bSaleStatistic;
	public JRadioButton radio1, radio2, radio3, radio4, radio5, radio6;

	// --------------------------------------------------------------

	public ArrayList<Goods> goods;
	public ArrayList<Group> groups;
	public ArrayList<Subgroup> subgroups;
	public ArrayList<SaleGoods> saleGoods;
	public ArrayList<Goods> deletedGoods;

	// --------------------------------------------------------------

	public AbstractTableModel goodsModel;
	public TableModel saleGoodsModel;
	public JTable goodsTable;
	public JTable saleGoodsTable;

	// --------------------------------------------------------------

	private JLabel label = new JLabel();

	// --------------------------------------------------------------

	private JScrollPane jsp1;
	private JScrollPane jsp2;
	private JScrollPane jsp3;

	// --------------------------------------------------------------

	private static final int BUTTON_SPACE = 10;

	// --------------------------------------------------------------

	ListenerButton aListener = new ListenerButton();
	RadioButtonListener rbaListener = new RadioButtonListener();

	// --------------------------------------------------------------

	public JComboBox comboGroup, comboSubgroup;
	public JSpinner spinner;
	public JTextField jtf_date_1;
	public JTextField jtf_date_2;

	// --------------------------------------------------------------
	
	public File goodstxt = new File("data//goods.txt");
	public File groupstxt = new File("data//groups.txt");
	public File subgroupstxt = new File("data//subgroups.txt");
	public File sale_goodstxt = new File("data//sale_goods.txt");
	public File deleted_goodstxt = new File("data//deleted_goods.txt");
	

	public MainWindow() throws IOException {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 670);
		setResizable(false);
		setLocationRelativeTo(null);
		
		// Icons on the buttons
				Icon groupAddIcon = new ImageIcon("images\\Add_Group.png");
				Icon groupEditIcon = new ImageIcon("images\\Edit_Group.png");
				Icon groupRemoveIcon = new ImageIcon("images\\Delete_Group.png");

				Icon subgroupAddIcon = new ImageIcon("images\\Add_Subgroup.png");
				Icon subgroupEditIcon = new ImageIcon("images\\Edit_Subgroup.png");
				Icon subgroupRemoveIcon = new ImageIcon("images\\Delete_Subgroup.png");

				Icon goodAddIcon = new ImageIcon("images\\Add_Good.png");
				Icon goodEditIcon = new ImageIcon("images\\Edit_Good.png");
				Icon goodRemoveIcon = new ImageIcon("images\\Delete_Good.png");

				Icon searchIcon = new ImageIcon("images\\Search.png");
				Icon statisticsIcon = new ImageIcon("images\\Statistic.png");
				Icon saleIcon = new ImageIcon("images\\Sale.png");
				
				Icon exportIcon = new ImageIcon("images\\Export.png");
				Icon incomeIcon = new ImageIcon("images\\Income.png");
				
				
				
				Icon really = new ImageIcon("images\\Really.png");
		
		// Buttons on Goods
				bGroupAdd = new JButton(groupAddIcon);
				bGroupAdd.setBounds(BUTTON_SPACE * 2, 435, 64, 64);
				bGroupAdd.setToolTipText("Add group");

				bGroupEdit = new JButton(groupEditIcon);
				bGroupEdit.setBounds(32 + 5 + BUTTON_SPACE * 2, 505, 64, 64);
				bGroupEdit.setToolTipText("Edit group");

				bGroupRemove = new JButton(groupRemoveIcon);
				bGroupRemove.setBounds(BUTTON_SPACE * 3 + 64, 435, 64, 64);
				bGroupRemove.setToolTipText("Delete group");

				bSubgroupAdd = new JButton(subgroupAddIcon);
				bSubgroupAdd.setBounds(64 * 2 + BUTTON_SPACE * 6, 435, 64, 64);
				bSubgroupAdd.setToolTipText("Add Subgroup");

				bSubgroupEdit = new JButton(subgroupEditIcon);
				bSubgroupEdit.setBounds(37 + 64 * 2 + BUTTON_SPACE * 6, 505, 64, 64);
				bSubgroupEdit.setToolTipText("Edit Subgroup");

				bSubgroupRemove = new JButton(subgroupRemoveIcon);
				bSubgroupRemove.setBounds(64 * 3 + BUTTON_SPACE * 7, 435, 64, 64);
				bSubgroupRemove.setToolTipText("Delete Subgroup");

				bGoodsAdd = new JButton(goodAddIcon);
				bGoodsAdd.setBounds(64 * 4 + BUTTON_SPACE * 10, 435, 64, 64);
				bGoodsAdd.setToolTipText("Add product");

				bGoodsEdit = new JButton(goodEditIcon);
				bGoodsEdit.setBounds(37 + 64 * 4 + BUTTON_SPACE * 10, 505, 64, 64);
				bGoodsEdit.setToolTipText("Edit product");

				bGoodsRemove = new JButton(goodRemoveIcon);
				bGoodsRemove.setBounds(64 * 5 + BUTTON_SPACE * 11, 435, 64, 64);
				bGoodsRemove.setToolTipText("Delete product");

				bSearch = new JButton(searchIcon);
				bSearch.setBounds(64 * 6 + BUTTON_SPACE * 14, 435, 64, 64);
				bSearch.setToolTipText("Find product");

				bStatistic = new JButton(statisticsIcon);
				bStatistic.setBounds(64 * 7 + BUTTON_SPACE * 15, 435, 64, 64);
				bStatistic.setToolTipText("Statistics of Stock");
				
				bExport = new JButton(exportIcon);
				bExport.setBounds(80 + 64 * 6 + BUTTON_SPACE * 10, 505, 64, 64);
				bExport.setToolTipText("Export goods in Excel");
				
				bSale = new JButton(saleIcon);
				bSale.setBounds(64 * 7 + BUTTON_SPACE * 15+200, 435, 64, 64);
				bSale.setToolTipText("Sale product");
				
				bIncome = new JButton(incomeIcon);
				bIncome.setBounds(64 * 7 + BUTTON_SPACE * 15+130, 435, 64, 64);
				bIncome.setToolTipText("Arrived at stock");

				// Buttons on saleGoods

				bSaleRemove = new JButton(goodRemoveIcon);
				bSaleRemove.setBounds(BUTTON_SPACE * 2, 435, 64, 64);
				bSaleRemove.setToolTipText("Product Return");

				bSaleStatistic = new JButton(statisticsIcon);
				bSaleStatistic.setBounds(64 * 1 + BUTTON_SPACE * 3, 435, 64, 64);
				bSaleStatistic.setToolTipText("Statistics of SaleGoods");

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(title, BorderLayout.NORTH);
		title.setLayout(new FlowLayout());

		label.setText("Best ARM");
		label.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		title.add(label);

		listOfGroups.setLayout(new BorderLayout());
		listOfGroups.add(goodsAll);

		mainPanel.add(listOfGoods, BorderLayout.WEST);
		getContentPane().add(mainPanel);
		mainPanel.add(listOfGroups, BorderLayout.CENTER);
		getContentPane().add(mainPanel);

		goods = new ArrayList<Goods>();
		groups = new ArrayList<Group>();
		subgroups = new ArrayList<Subgroup>();
		saleGoods = new ArrayList<SaleGoods>();
		deletedGoods = new ArrayList<Goods>();
		
		// Fill Arraylists

		if (groupstxt.exists()&&groupstxt.length()>5) {
			GroupsReader grr = new GroupsReader();
			groups = grr.getGroupsList();
			
		}
		else{
			bGroupEdit.setEnabled(false);
			bGroupRemove.setEnabled(false);
			bSubgroupAdd.setEnabled(false);
			bSubgroupEdit.setEnabled(false);
			bSubgroupRemove.setEnabled(false);
			bGoodsAdd.setEnabled(false);
			bGoodsEdit.setEnabled(false);
			bGoodsRemove.setEnabled(false);
			bSearch.setEnabled(false);
			bStatistic.setEnabled(false);
			bSale.setEnabled(false);
			bExport.setEnabled(false);
			bIncome.setEnabled(false);
		}
		if (subgroupstxt.exists()&&subgroupstxt.length()>5) {
			SubgroupsReader sgr = new SubgroupsReader();
			subgroups = sgr.getSubGroupsList();
		}
		else{
			bSubgroupEdit.setEnabled(false);
			bSubgroupRemove.setEnabled(false);
			bGoodsAdd.setEnabled(false);
			bGoodsEdit.setEnabled(false);
			bGoodsRemove.setEnabled(false);
			bSearch.setEnabled(false);
			bStatistic.setEnabled(false);
			bSale.setEnabled(false);
			bExport.setEnabled(false);
			bIncome.setEnabled(false);
		}
		if (goodstxt.exists()&&goodstxt.length()>5) {
			GoodsReader gr = new GoodsReader();
			goods = gr.getProductsList();
		}
		else{
			bGoodsEdit.setEnabled(false);
			bGoodsRemove.setEnabled(false);
			bSearch.setEnabled(false);
			bStatistic.setEnabled(false);
			bSale.setEnabled(false);
			bExport.setEnabled(false);
			bIncome.setEnabled(false);
		}
		if (sale_goodstxt.exists()) {
		SaleGoodsReader slgr = new SaleGoodsReader();
		saleGoods = slgr.getProductsList();
		}
		if(deleted_goodstxt.exists()){
			DeletedGoodsReader dgr = new DeletedGoodsReader();
			deletedGoods = dgr.getDeletedGoodsList();
		}
		
	

		// Table Goods
		goodsModel = new TableModelGoods(goods);
		goodsTable = new JTable(goodsModel);
		goodsTable.getTableHeader().setReorderingAllowed(false);
		setColumnWidth(goodsTable, new int[] { 5, 170, 60, 180, 15, 15, 20 });
		jsp1 = new JScrollPane(goodsTable);
		jsp1.setBounds(20, 80, 850, 351);
		

		// Table SaleGoods
		saleGoodsModel = new TableModelSaleGoods(saleGoods);
		saleGoodsTable = new JTable(saleGoodsModel);
		saleGoodsTable.getTableHeader().setReorderingAllowed(false);
		setColumnWidth(saleGoodsTable, new int[] { 50, 100, 50, 30, 30, 30 });
		jsp2 = new JScrollPane(saleGoodsTable);
		jsp2.setBounds(20, 80, 850, 351);

		goodsTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable table = (JTable) me.getSource();
				Point point = me.getPoint();
				int row = table.rowAtPoint(point);
				if (me.getClickCount() == 2) {
					bGoodsEdit.doClick();
				}
			}
		});
		
		

		

		

		// Add tabs
		goodsAll.addTab("Goods", pFirstTab);
		goodsAll.addTab("Sale Goods", pSecondTab);

		// Add on first Tab
		pFirstTab.setLayout(null);
		JLabel label1 = new JLabel("Goods");
		label1.setBounds(375, 5, 150, 21);
		label1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pFirstTab.add(label1);
		pFirstTab.add(jsp1);
		pFirstTab.add(bGroupAdd);
		pFirstTab.add(bGroupEdit);
		pFirstTab.add(bGroupRemove);
		pFirstTab.add(bSubgroupAdd);
		pFirstTab.add(bSubgroupEdit);
		pFirstTab.add(bSubgroupRemove);
		pFirstTab.add(bGoodsAdd);
		pFirstTab.add(bGoodsEdit);
		pFirstTab.add(bGoodsRemove);
		pFirstTab.add(bSearch);
		pFirstTab.add(bStatistic);
		pFirstTab.add(bSale);
		pFirstTab.add(bExport);
		pFirstTab.add(bIncome);
		
		
		
		// RADIO BUTTONS on first tab
		JLabel label2 = new JLabel("Show Goods:");
		label2.setBounds(30, 45, 100, 23);
		pFirstTab.add(label2);

		radio1 = new JRadioButton("All Goods");
		radio1.setBounds(120, 45, 90, 23);
		radio1.addActionListener(rbaListener);
		pFirstTab.add(radio1);

		radio2 = new JRadioButton("Only Groups: ");
		radio2.setBounds(210, 45, 100, 23);
		radio2.addActionListener(rbaListener);
		pFirstTab.add(radio2);

		comboGroup = new JComboBox();
		String[] string = getGroupNames();
		comboGroup.setModel(new DefaultComboBoxModel(string));
		comboGroup.setBounds(340, 45, 180, 25);
		comboGroup.addActionListener(rbaListener);
		pFirstTab.add(comboGroup);

		radio3 = new JRadioButton("Only Subgroups: ");
		radio3.setBounds(540, 45, 150, 23);
		radio3.addActionListener(rbaListener);
		pFirstTab.add(radio3);

		comboSubgroup = new JComboBox();
		string = getSubgroupNames();
		comboSubgroup.setModel(new DefaultComboBoxModel(string));
		comboSubgroup.setBounds(700, 45, 180, 25);
		comboSubgroup.addActionListener(rbaListener);
		pFirstTab.add(comboSubgroup);

		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(radio1);
		bg1.add(radio2);
		bg1.add(radio3);

		// Second Tab
		pSecondTab.setLayout(null);
		JLabel jl2 = new JLabel("Sold products");
		jl2.setBounds(375, 5, 150, 21);
		jl2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pSecondTab.add(jl2);
		pSecondTab.add(jsp2);  // тут почему-то нулл поинтер эксепшн
		pSecondTab.add(bSaleRemove);
		pSecondTab.add(bSaleStatistic);

		// ActionListeners
		bGroupAdd.addActionListener(aListener);
		bGroupEdit.addActionListener(aListener);
		bGroupRemove.addActionListener(aListener);
		bSubgroupAdd.addActionListener(aListener);
		bSubgroupEdit.addActionListener(aListener);
		bSubgroupRemove.addActionListener(aListener);
		bGoodsAdd.addActionListener(aListener);
		bGoodsEdit.addActionListener(aListener);
		bGoodsRemove.addActionListener(aListener);
		bSearch.addActionListener(aListener);
		bStatistic.addActionListener(aListener);
		bSaleRemove.addActionListener(aListener);
		bSaleStatistic.addActionListener(aListener);
		bSale.addActionListener(aListener);
		bExport.addActionListener(aListener);
		bIncome.addActionListener(aListener);
		
		goodsAll.addChangeListener(this);
		
		//Adding Window Listener
		addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent event) {}
            
			public void windowClosed(WindowEvent event) {}
            
			public void windowClosing(WindowEvent event) {
				Object[] options = { "Yes", "No!" };
                int n = JOptionPane.showOptionDialog(event.getWindow(), "Close programm?",
                                "Confirmation", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, really, options,
                                options[0]);
                if (n == 0) {
                    event.getWindow().setVisible(false);
                    System.exit(0);
                }
            }
 
            public void windowDeactivated(WindowEvent event) {}
 
            public void windowDeiconified(WindowEvent event) {}
 
            public void windowIconified(WindowEvent event) {}
 
            public void windowOpened(WindowEvent event) {}
 
        });
 }



	public void setColumnWidth(JTable goodsTable2, int[] is) {
		for (int i = 0; i < is.length; i++)
			goodsTable2.getColumnModel().getColumn(i).setPreferredWidth(is[i]);
	}

	public void refreshComboBoxes() {
		String[] string = getGroupNames();
		comboGroup.setModel(new DefaultComboBoxModel(string));

		string = getSubgroupNames();
		comboSubgroup.setModel(new DefaultComboBoxModel(string));
	}

	public String[] getGroupNames() {
		String[] string = new String[groups.size()];
		for (int i = 0; i < groups.size(); i++) {
			Group group = groups.get(i);
			string[i] = group.getGroupName();
		}
		return string;
	}

	public String[] getGoodsNames() {
		String[] string = new String[goods.size()];
		for (int i = 0; i < goods.size(); i++) {
			Goods g = goods.get(i);
			string[i] = g.getName();
		}
		return string;
	}

	public String[] getSubgroupNames() {
		String[] string = new String[subgroups.size()];
		for (int i = 0; i < subgroups.size(); i++) {
			Subgroup sgr = subgroups.get(i);
			string[i] = sgr.getSubgroupName();
		}
		return string;
	}

	public int getGroupIDByName(String selectedItem) {
		for (Group gr : groups) {
			if (gr.getGroupName() == selectedItem)
				return gr.getGroupID();
		}
		return -1;
	}

	public ArrayList<Subgroup> getSubgroupsByGroupID(int groupID) {
		ArrayList<Subgroup> arrayList = new ArrayList<Subgroup>();
		for (Subgroup sgr : subgroups) {
			if (sgr.getGroupID() == groupID) {
				arrayList.add(sgr);
			}
		}
		return arrayList;
	}

	public ArrayList<Goods> getGoodsOfGroup(int groupID) {
		ArrayList<Goods> tempGoods = new ArrayList<Goods>();
		for (Goods g : goods) {
			if (g.getGroupID() == groupID)
				tempGoods.add(g);
		}
		return tempGoods;
	}

	public String[] getSubgroupNamesByGroupID(int groupID) {
		ArrayList<Subgroup> arrayList = getSubgroupsByGroupID(groupID);

		String[] array = new String[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
			array[i] = arrayList.get(i).getSubgroupName();
		}
		return array;
	}

	public int getSubgroupIDByName(String selectedItem) {
		for (Subgroup sgr : subgroups) {
			if (sgr.getSubgroupName() == selectedItem)
				return sgr.getSubgroupID();
		}
		return -1;
	}
	


	public void stateChanged(ChangeEvent arg0) {}

}
