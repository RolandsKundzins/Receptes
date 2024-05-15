package receptes.type;

public class FoodCategoryType {
	private int edienaKategorijasID;
	private String nosaukums;
	
	public FoodCategoryType() {
		
	}
	
	public FoodCategoryType(int edienaKategorijasId, String nosaukums) {
		this.edienaKategorijasID = edienaKategorijasId;
		this.nosaukums = nosaukums;
	}
	
	public int getFoodCategoryId() {
		return edienaKategorijasID;
	}
	
	public void setFoodCategoryId(int edienaKategorijasId) {
		this.edienaKategorijasID = edienaKategorijasId;
	}
	
	public String getNosaukums() {
		return nosaukums;
	}
	
	public void setNosaukums(String nosaukums) {
		this.nosaukums = nosaukums;
	}
	
	@Override
	public String toString() {
		return "FoodCategory [edienaKategorijasID= " + edienaKategorijasID + ", nosaukums= " + nosaukums + "]";
	}
	
}
