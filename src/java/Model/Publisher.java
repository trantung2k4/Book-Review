package Model;

public class Publisher {

	private int pId;
	private String pName;
	
	public Publisher(int pId, String pName) {
		
		this.pId = pId;
		this.pName = pName;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String toString() {
		return "Publisher [pId=" + pId + ", pName=" + pName + "]";
	}
	
}
