package objectsForStore;

public class SaleGoods {
	
	private static int uniqueID = 1;
	
	private int saleGoodsID;
	private String date;
	private String unit;
	private double quantity;
	private double price;
	
	public SaleGoods(int saleGoodsID, String date, double quantity, double price) {
		this.saleGoodsID = saleGoodsID;
		this.date = date;
		this.quantity = quantity;
		this.price = price;
		if (uniqueID < saleGoodsID)  {
			uniqueID = saleGoodsID;
		}
		uniqueID++;
	}

	public static int getUniqueID() {
		return uniqueID;
	}

	public static void setUniqueID(int uniqueID) {
		SaleGoods.uniqueID = uniqueID;
	}

	public int getSaleGoodsID() {
		return saleGoodsID;
	}

	public void setSaleGoodsID(int saleGoodsID) {
		this.saleGoodsID = saleGoodsID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "SaleGoods [saleGoodsID=" + saleGoodsID + ", date=" + date + ", quantity=" + quantity + ", price="
				+ price + "]";
	}
	
	
}
