package objectsForStore;

import java.io.Serializable;

public class Group implements Serializable{
	
	private static int uniqueID = 1;
	
	private static int groupID;
	private String groupName;
	private String groupDesc;

	public Group(int groupID, String groupName) {
		this.groupID = groupID;
		this.groupName = groupName;
		if (uniqueID < groupID) {
			uniqueID = groupID;
		}
		uniqueID++;
	}
	
	public Group(int groupID, String groupName, String groupDesc) {
		this.groupID = groupID;
		this.groupName = groupName;
		this.groupDesc = groupDesc;
	}

	public static int getUniqueID() {
		return uniqueID;
	}

	public static void setUniqueID(int uniqueID) {
		Group.uniqueID = uniqueID;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	
	public String toString() {
		return groupID + ", " + groupName + ", " + groupDesc + "." + "/n";
	}
}