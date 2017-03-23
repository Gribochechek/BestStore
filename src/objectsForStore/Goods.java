package objectsForStore;

import main.Main;

//У кожного товару є наступні властивості - назва, опис, виробник, кількість на складі, ціна за одиницю.
public class Goods {
	
	private static int uniqueID = 1;
	
	private int goodsID;
	private int subgroupID;
	private String name;
	private String desc;
	private String maker;
	private String unit;		//Одиниці вимірювання товарів (кг., шт., од., і тд.)
	private double quantity;
	private double price;
	
	public Goods(int ID, int subgroupID, String name, String desc, String maker, double quantity, double price, String unit) {
		this.goodsID = ID;
		this.subgroupID = subgroupID;
		this.name = name;
		this.desc = desc;
		this.maker = maker;
		this.quantity = quantity;
		this.price = price;
		this.unit = unit;
		uniqueID++;
	}
	
	public Goods(int ID, int subgroupID, String name, double price, double quantity, String unit) {
		this.goodsID = ID;
		this.subgroupID = subgroupID;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.unit = unit;
		uniqueID++;
	}
	
	public Goods(int ID, int subgroupID, String name, double quantity, String unit) {
		this.goodsID = ID;
		if (uniqueID < ID) {
			uniqueID = ID;
		}
		uniqueID++;
		this.subgroupID = subgroupID;
		this.name = name;
		this.quantity = quantity;
		this.unit = unit;
	}

	public static int getUniqueID() {
		return uniqueID;
	}

	public static void setUniqueID(int uniqueID) {
		Goods.uniqueID = uniqueID;
	}

	public int getID() {
		return goodsID;
	}

	public void setID(int iD) {
		this.goodsID = iD;
	}

	public int getSubgroupID() {
		return subgroupID;
	}

	public void setSubgroupID(int subgroupID) {
		this.subgroupID = subgroupID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Goods [goodsID=" + goodsID + ", subgroupID=" + subgroupID + ", name=" + name + ", desc=" + desc
				+ ", maker=" + maker + ", unit=" + unit + ", quantity=" + quantity + ", price=" + price + "]";
	}

	public int getGroupID() {
		for (int i = 0; i < Main.mainWindow.subgroups.size(); i++){
			if (Main.mainWindow.subgroups.get(i).getSubgroupID() == subgroupID) 
				return Main.mainWindow.subgroups.get(i).getGroupID();
		}
		return -1;
	}
}
