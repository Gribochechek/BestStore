package streams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import objectsForStore.Group;

public class GroupsWriter {

	public void saveGroupsInFile(ArrayList<Group> groups) {
		try {
			FileWriter writer = new FileWriter("data\\groups.txt");
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			String s;
			for (int i = 0; i < groups.size(); i++) {
				Group tempGroup = groups.get(i);
				s = tempGroup.getGroupID() + "|" + tempGroup.getGroupName() + "|" + tempGroup.getGroupDesc() + "|\n";
				bufferWriter.write(s);

			}
			bufferWriter.close();
			writer.close();
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}

}
