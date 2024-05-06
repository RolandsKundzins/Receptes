//Type for Recipe


package com.example.receptes;

public class Recipe {
	private int recepteId;
	private String nosaukums;
	private int pagatavosanasLaiks;
//	private LocalDateTime createdAt;
	private String receptesApraksts;
	//TODO pievienot parejos laukus - vini jau ir datubazes pievienoti
	
	public Recipe() {
		
	}
	
	
	public Recipe(int recepteId, String nosaukums, int pagatavosanasLaiks, String receptesApraksts) {
		this.recepteId = recepteId;
		this.nosaukums = nosaukums;
		this.pagatavosanasLaiks = pagatavosanasLaiks;
		this.receptesApraksts = receptesApraksts;
	}


	//getters and setters
	public int getRecepteId() {
		return recepteId;
	}

	public void setRecepteId(int recepteId) {
		this.recepteId = recepteId;
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
