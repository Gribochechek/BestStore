package streams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import objectsForStore.Subgroup;

public class SubgroupsWriter {
	
	public void saveSubgroupsInFile(ArrayList<Subgroup> subgroups) {
		try {
			FileWriter writer = new FileWriter("data\\subgroups.txt");
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			String s;
			for (int i = 0; i < subgroups.size(); i++) {
				Subgroup tempSubgroup = subgroups.get(i);
				s = tempSubgroup.getGroupID() + "|" + tempSubgroup.getSubgroupID() + "|" + tempSubgroup.getSubgroupName() + "|\n";
				bufferWriter.write(s);

			}
			bufferWriter.close();
			writer.close();
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}

}
