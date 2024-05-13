package receptes.type;

public class ProductType {
	private int recepteID;
	private String nosaukums;
	private Boolean irDefinets = false; //Nav ideals risinajums

	public ProductType() {
		irDefinets = false;
	}
	
	public ProductType(int recepteID, String nosaukums) {
		this.recepteID = recepteID;
		this.nosaukums = nosaukums;
		this.irDefinets = true;
	}


	public int getRecepteID() {
		return recepteID;
	}
	public void setRecepteID(int recepteID) {
		this.recepteID = recepteID;
	}

	public String getNosaukums() {
		return nosaukums;
	}
	public void setNosaukums(String nosaukums) {
		this.nosaukums = nosaukums;
	}

	public Boolean getIrDefinets() {
		return irDefinets;
	}
	public void setIrDefinets(Boolean irDefinets) {
		this.irDefinets = irDefinets;
	}

	@Override
	public String toString() {
		return "ProductType [recepteID=" + recepteID + ", nosaukums=" + nosaukums + ", irDefinets=" + irDefinets + "]";
	}
}
