package objectsForStore;

public class Subgroup {
	
	private static int uniqueID = 1;
	
	private int subgroupID;
	private String subgroupName;

	public Subgroup(int subgroupID, String subgroupName) {
		this.subgroupID = subgroupID;
		this.subgroupName = subgroupName;
		if (uniqueID < subgroupID) {
			uniqueID = subgroupID;
		}
		uniqueID++;
	}

	public static int getUniqueID() {
		return uniqueID;
	}

	public static void setUniqueID(int uniqueID) {
		Subgroup.uniqueID = uniqueID;
	}

	public int getSubgroupID() {
		return subgroupID;
	}

	public void setSubgroupID(int subgroupID) {
		this.subgroupID = subgroupID;
	}

	public String getSubgroupName() {
		return subgroupName;
	}

	public void setSubgroupName(String subgroupName) {
		this.subgroupName = subgroupName;
	}

	@Override
	public String toString() {
		return "Subgroup [subgroupID=" + subgroupID + ", subgroupName=" + subgroupName + "]";
	}

}
