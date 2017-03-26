package streams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import objectsForStore.SaleGoods;

public class SaleGoodsReader {

	public ArrayList<SaleGoods> getProductsList() throws IOException {
		ArrayList<SaleGoods> goods = new ArrayList<SaleGoods>();
			BufferedReader br = new BufferedReader(new FileReader("data\\sale_goods.txt"));
			SaleGoods tempGood;	
			String text;
			
			while (true) {
				 text = br.readLine();
				if (text != null) {
					int id = Integer.parseInt(text.substring(0, text.indexOf("|")));
					text = text.substring(text.indexOf("|") + 1);
														
					String pDate = text.substring(0, text.indexOf("|"));
					text = text.substring(text.indexOf("|") + 1);
					
					double pQuant = Double.parseDouble(text.substring(0, text.indexOf("|")));
					
					
					tempGood = new SaleGoods(id, pDate, pQuant);
					goods.add(tempGood);
			
				}
				else break;
			
			
			}	
			
		br.close();
		return goods;
		
	

}
	
}
