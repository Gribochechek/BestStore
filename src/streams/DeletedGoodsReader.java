package streams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import objectsForStore.Goods;

public class DeletedGoodsReader   {
	
	public ArrayList<Goods> getDeletedGoodsList() throws IOException {
		ArrayList<Goods> goods = new ArrayList<Goods>();
			BufferedReader br = new BufferedReader(new FileReader("data\\deleted_goods.txt"));
			Goods tempGood;	
			String text;
			
			while (true) {
				 text = br.readLine();
				if (text != null) {
					int id = Integer.parseInt(text.substring(0, text.indexOf("|")));
					text = text.substring(text.indexOf("|") + 1);
					
					int subgroupID = Integer.parseInt(text.substring(0, text.indexOf("|")));
					text = text.substring(text.indexOf("|") + 1);	
					
					String pName = text.substring(0, text.indexOf("|"));
					text = text.substring(text.indexOf("|") + 1);
					
					String pDesc = text.substring(0, text.indexOf("|"));
					text = text.substring(text.indexOf("|") + 1);
					
					String pMaker = text.substring(0, text.indexOf("|"));
					text = text.substring(text.indexOf("|") + 1);
					
					String pUnit = text.substring(0, text.indexOf("|"));
					text = text.substring(text.indexOf("|") + 1);
					
					double pQuant = Double.parseDouble(text.substring(0, text.indexOf("|")));
					text = text.substring(text.indexOf("|") + 1);
					
					double pPrice = Double.parseDouble(text.substring(0, text.indexOf("|")));
					
					tempGood = new Goods(id, subgroupID, pName, pDesc, pMaker, pQuant, pPrice, pUnit);
					goods.add(tempGood);
			
				}
				else break;
			
			
			}	
			
		br.close();
		return goods;
		
	

}

}
