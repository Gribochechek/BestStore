package mainWindows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import streams.GoodsReader;
import streams.GoodsWriter;
import streams.GroupsReader;
import streams.SubgroupsReader;
import tableModels.TableModelGoods;
import tableModels.TableModelSaleGoods;

public class MainWindow extends JFrame implements ChangeListener {

	private JPanel mainPanel = new JPanel();
	private JPanel title = new JPanel();
	private JPanel listOfGroups = new JPanel();
	private JPanel listOfGoods = new JPanel();
	private JTabbedPane groupsLeft = new JTabbedPane();
	private JTabbedPane goodsRight = new JTabbedPane();
	private JPanel pFirstTab = new JPanel();
	private JPanel pSecondTab = new JPanel();

	// --------------------------------------------------------

	public JButton bGoodsAdd, bGoodsRemove, bGoodsEdit;
	public JButton bGroupAdd;
	public JButton bGroupRemove;
	public JButton bGroupEdit;
	public JButton bSubgroupAdd, bSubgroupRemove, bSubgroupEdit;
	public JButton bSearch, bStatistic;
	public JButton bSaleAdd, bSaleRemove, bSaleStatistic;
	public JRadioButton radio1, radio2, radio3, radio4, radio5, radio6;

	// --------------------------------------------------------------

	public ArrayList<Goods> goods;
	public ArrayList<Group> groups;
	public ArrayList<Subgroup> subgroups;
	public ArrayList<SaleGoods> saleGoods;

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

	public MainWindow() throws IOException {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 670);
		setResizable(false);
		setLocationRelativeTo(null);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(title, BorderLayout.NORTH);
		title.setLayout(new FlowLayout());

		label.setText("Best ARM");
		label.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		title.add(label);

		listOfGroups.setLayout(new BorderLayout());
		listOfGroups.add(groupsLeft);

		listOfGoods.setLayout(new BorderLayout());
		listOfGoods.add(goodsRight);

		mainPanel.add(listOfGoods, BorderLayout.WEST);
		getContentPane().add(mainPanel);
		mainPanel.add(listOfGroups, BorderLayout.CENTER);
		getContentPane().add(mainPanel);

		goods = new ArrayList<Goods>();
		groups = new ArrayList<Group>();
		subgroups = new ArrayList<Subgroup>();
		saleGoods = new ArrayList<SaleGoods>();
		
		// Fill Arraylists 
		GoodsReader gr = new GoodsReader();
		goods = gr.getProductsList();
		GroupsReader grr = new GroupsReader();
		groups = grr.getGroupsList();
		SubgroupsReader sgr = new SubgroupsReader();
		subgroups = sgr.getSubGroupsList();
		
		
		// export in Excel example
		GoodsWriter gw = new GoodsWriter();
		gw.exportGoodsInExcel(goods, "data");

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
		setColumnWidth(saleGoodsTable, new int[] { 25, 5, 150, 10, 180 });
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

		// Buttons on Goods
		bGroupAdd = new JButton(groupAddIcon);
		bGroupAdd.setBounds(BUTTON_SPACE * 2, 435, 64, 64);
		bGroupAdd.setToolTipText("������ ����� ������");

		bGroupEdit = new JButton(groupEditIcon);
		bGroupEdit.setBounds(32 + 5 + BUTTON_SPACE * 2, 505, 64, 64);
		bGroupEdit.setToolTipText("���������� ����� ������");

		bGroupRemove = new JButton(groupRemoveIcon);
		bGroupRemove.setBounds(BUTTON_SPACE * 3 + 64, 435, 64, 64);
		bGroupRemove.setToolTipText("�������� ����� ������");

		bSubgroupAdd = new JButton(subgroupAddIcon);
		bSubgroupAdd.setBounds(64 * 2 + BUTTON_SPACE * 6, 435, 64, 64);
		bSubgroupAdd.setToolTipText("������ ������� ������");

		bSubgroupEdit = new JButton(subgroupEditIcon);
		bSubgroupEdit.setBounds(37 + 64 * 2 + BUTTON_SPACE * 6, 505, 64, 64);
		bSubgroupEdit.setToolTipText("���������� ������� ������");

		bSubgroupRemove = new JButton(subgroupRemoveIcon);
		bSubgroupRemove.setBounds(64 * 3 + BUTTON_SPACE * 7, 435, 64, 64);
		bSubgroupRemove.setToolTipText("�������� ������� ������");

		bGoodsAdd = new JButton(goodAddIcon);
		bGoodsAdd.setBounds(64 * 4 + BUTTON_SPACE * 10, 435, 64, 64);
		bGoodsAdd.setToolTipText("������ �����");

		bGoodsEdit = new JButton(goodEditIcon);
		bGoodsEdit.setBounds(37 + 64 * 4 + BUTTON_SPACE * 10, 505, 64, 64);
		bGoodsEdit.setToolTipText("���������� ����� ��� �����");

		bGoodsRemove = new JButton(goodRemoveIcon);
		bGoodsRemove.setBounds(64 * 5 + BUTTON_SPACE * 11, 435, 64, 64);
		bGoodsRemove.setToolTipText("�������� �����");

		bSearch = new JButton(searchIcon);
		bSearch.setBounds(64 * 6 + BUTTON_SPACE * 14, 435, 64, 64);
		bSearch.setToolTipText("������ �����");

		bStatistic = new JButton(statisticsIcon);
		bStatistic.setBounds(64 * 7 + BUTTON_SPACE * 15, 435, 64, 64);
		bStatistic.setToolTipText("�������� ����������");

		// Buttons on saleGoods
		bSaleAdd = new JButton(goodAddIcon);
		bSaleAdd.setBounds(BUTTON_SPACE * 2, 435, 64, 64);
		bSaleAdd.setToolTipText("������ �����");

		bSaleRemove = new JButton(goodRemoveIcon);
		bSaleRemove.setBounds(64 * 1 + BUTTON_SPACE * 3, 435, 64, 64);
		bSaleRemove.setToolTipText("�������� �����");

		bSaleStatistic = new JButton(statisticsIcon);
		bSaleStatistic.setBounds(64 * 2 + BUTTON_SPACE * 6, 435, 64, 64);
		bSaleStatistic.setToolTipText("�������� ����������");

		// Add tabs
		groupsLeft.addTab("Goods", pFirstTab);
		groupsLeft.addTab("Sale Goods", pSecondTab);

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
		JLabel jl2 = new JLabel("������� ������");
		jl2.setBounds(375, 5, 150, 21);
		jl2.setFont(new Font("Verdana", Font.BOLD, 16));
		pSecondTab.add(jl2);
		pSecondTab.add(jsp2);
		pSecondTab.add(bSaleAdd);
		pSecondTab.add(bSaleRemove);
		pSecondTab.add(bSaleStatistic);

		// RADIO BUTTONS on second tab
		JLabel jl2_1 = new JLabel("�������� ������:");
		jl2_1.setBounds(35, 45, 110, 21);
		pSecondTab.add(jl2_1);

		radio4 = new JRadioButton("��");
		radio4.setBounds(170, 45, 50, 23);
		radio4.addActionListener(rbaListener);
		pSecondTab.add(radio4);

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
		bSaleAdd.addActionListener(aListener);
		bSaleRemove.addActionListener(aListener);
		bSaleStatistic.addActionListener(aListener);
		
		groupsLeft.addChangeListener(this);

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