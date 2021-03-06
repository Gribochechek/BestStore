package streams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import objectsForStore.Goods;

public class DeletedGoodsWriter {
	public void saveDeletedGoodsInFile(ArrayList<Goods> goods){
		try {
			FileWriter writer = new FileWriter("data\\deleted_goods.txt");
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			String s;
			for (int i = 0; i < goods.size(); i++) {
				Goods tempGood = goods.get(i);
				s = tempGood.getID()+"|"+tempGood.getSubgroupID()+"|"+tempGood.getName()+"|"+
						tempGood.getDesc()+"|"+tempGood.getMaker()+"|"+tempGood.getUnit()
						+"|"+tempGood.getQuantity()+"|"+tempGood.getPrice()+"|\n";	
				bufferWriter.write(s);
			}
			bufferWriter.close();
			writer.close();
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}
}
