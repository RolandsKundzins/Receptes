package receptes.type;

public class ProductType {
	private int produktsID;
	private String nosaukums;

	
	public ProductType() {
	}
	
	
	public ProductType(int produktsID, String nosaukums) {
		this.produktsID = produktsID;
		this.nosaukums = nosaukums;
	}


	//getters and setters
	public int getProduktsID() {
		return produktsID;
	}

	public void setProduktsID(int produktsID) {
		this.produktsID = produktsID;
	}

	public String getNosaukums() {
		return nosaukums;
	}

	public void setNosaukums(String nosaukums) {
		this.nosaukums = nosaukums;
	}

	
	@Override
	public String toString() {
		return "ProductType [produktsID=" + produktsID + ", nosaukums=" + nosaukums + "]";
	}
}
