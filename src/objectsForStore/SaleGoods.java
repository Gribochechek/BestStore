package objectsForStore;

public class SaleGoods {
	
	private static int uniqueID = 1;
	
	private int saleGoodsID;
	private String date;
	private double quantity;
	
	public SaleGoods(int saleGoodsID, String date, double quantity) {
		this.saleGoodsID = saleGoodsID;
		this.date = date;
		this.quantity = quantity;
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


	@Override
	public String toString() {
		return "SaleGoods [saleGoodsID=" + saleGoodsID + ", date=" + date + ", quantity=" + quantity + "]";
	}
	
	
}
