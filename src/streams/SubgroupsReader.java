package streams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import objectsForStore.Subgroup;

public class SubgroupsReader {
	
	public ArrayList<Subgroup> getSubGroupsList() throws IOException {
		ArrayList<Subgroup> subgroups = new ArrayList<Subgroup>();
			BufferedReader br = new BufferedReader(new FileReader("data\\subgroups.txt"));
			Subgroup tempSubgroup;	
			String text;
			
			while (true) {
				 text = br.readLine();
				if (text != null) {
					int groupID = Integer.parseInt(text.substring(0, text.indexOf("|")));
					text = text.substring(text.indexOf("|") + 1);
					
					int subgroupID = Integer.parseInt(text.substring(0, text.indexOf("|")));
					text = text.substring(text.indexOf("|") + 1);
					
					String name = text.substring(0, text.indexOf("|"));
					text = text.substring(text.indexOf("|") + 1);
					
					
					tempSubgroup = new Subgroup(groupID, subgroupID, name);
					subgroups.add(tempSubgroup);
			
				}
				else break;
			
			
			}	
			
		br.close();
		return subgroups;
		
	

}

}
