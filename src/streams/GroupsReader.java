package streams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import objectsForStore.Group;

public class GroupsReader {
	public ArrayList<Group> getGroupsList() throws IOException {
		ArrayList<Group> groups = new ArrayList<Group>();
			BufferedReader br = new BufferedReader(new FileReader("data\\groups.txt"));
			Group tempGroup;	
			String text;
			
			while (true) {
				 text = br.readLine();
				if (text != null) {
					int id = Integer.parseInt(text.substring(0, text.indexOf("|")));
					text = text.substring(text.indexOf("|") + 1);
					
					String pName = text.substring(0, text.indexOf("|"));
					text = text.substring(text.indexOf("|") + 1);
					
					String pDesc = text.substring(0, text.indexOf("|"));
					text = text.substring(text.indexOf("|") + 1);
					
					
					tempGroup = new Group(id, pName, pDesc);
					groups.add(tempGroup);
			
				}
				else break;
			
			
			}	
			
		br.close();
		return groups;
		
	

}
}
