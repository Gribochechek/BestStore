package streams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import objectsForStore.SaleGoods;

public class SaleGoodsWriter {
	
	public void saveSaleGoodsInFile(ArrayList<SaleGoods> goods){
		try {
			FileWriter writer = new FileWriter("data\\sale_goods.txt");
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			String s;
			for (int i = 0; i < goods.size(); i++) {
				SaleGoods tempGood = goods.get(i);
				s = tempGood.getSaleGoodsID()+"|"+tempGood.getDate()+"|"+tempGood.getQuantity()+"|\n";	
				bufferWriter.write(s);
			}
			bufferWriter.close();
			writer.close();
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}
	
	// method exports collection of products in Excel file with users directory
	public void exportSaleGoodsInExcel(ArrayList<SaleGoods> goods, String dir){
		try {
			FileWriter writer = new FileWriter(dir+"\\sale_goods.csv");
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			String top = "id;date;quantity;\n";
			bufferWriter.write(top);
			String s;
			for (int i = 0; i < goods.size(); i++) {
				SaleGoods tempGood = goods.get(i);
				s = tempGood.getSaleGoodsID()+"|"+tempGood.getDate()+"|"+tempGood.getQuantity()+"|\n";	
				bufferWriter.write(s);
			}
			bufferWriter.close();
			writer.close();
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}
	
}
