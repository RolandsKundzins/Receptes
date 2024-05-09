//Type for Recipe


package receptes.type;

public class RecipeType {
	private int recepteID;
	private String nosaukums;
	private int pagatavosanasLaiks;
//	private LocalDateTime createdAt;
	private String receptesApraksts;
	//TODO pievienot parejos laukus - vini jau ir datubazes pievienoti
	
	public RecipeType() {
		
	}
	
	
	public RecipeType(int recepteId, String nosaukums, int pagatavosanasLaiks, String receptesApraksts) {
		this.recepteID = recepteId;
		this.nosaukums = nosaukums;
		this.pagatavosanasLaiks = pagatavosanasLaiks;
		this.receptesApraksts = receptesApraksts;
	}


	//getters and setters
	public int getRecepteId() {
		return recepteID;
	}

	public void setRecepteId(int recepteId) {
		this.recepteID = recepteId;
	}

	public String getNosaukums() {
		return nosaukums;
	}

	public void setNosaukums(String nosaukums) {
		this.nosaukums = nosaukums;
	}

	public int getPagatavosanasLaiks() {
		return pagatavosanasLaiks;
	}

	public void setPagatavosanasLaiks(int pagatavosanasLaiks) {
		this.pagatavosanasLaiks = pagatavosanasLaiks;
	}

	public String getReceptesApraksts() {
		return receptesApraksts;
	}

	public void setReceptesApraksts(String receptesApraksts) {
		this.receptesApraksts = receptesApraksts;
	}
}
