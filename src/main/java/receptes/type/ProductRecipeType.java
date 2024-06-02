package receptes.type;

public class ProductRecipeType {
	private int recepteID;
	private int produktsID;
	
	public ProductRecipeType(int recepteID, int produktsID) {
		this.recepteID = recepteID;
		this.produktsID = produktsID;
	}

	public int getRecepteID() {
		return recepteID;
	}
	public void setRecepteID(int recepteID) {
		this.recepteID = recepteID;
	}
	
	public int getProduktsID() {
		return produktsID;
	}
	public void setProduktsID(int produktsID) {
		this.produktsID = produktsID;
	}
}
