package receptes.type;

public class ProductType {
	private int produktsID;
	private String nosaukums;

	
	public ProductType() {
		
	}
	
	
	public ProductType(int produktsId, String nosaukums) {
		this.produktsID = produktsId;
		this.nosaukums = nosaukums;
	}


	//getters and setters
	public int getProduktsId() {
		return produktsID;
	}

	public void setProduktsId(int produktsId) {
		this.produktsID = produktsId;
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
