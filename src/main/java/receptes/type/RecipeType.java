//Type for Recipe


package receptes.type;

import java.sql.Timestamp;

public class RecipeType {
	private int recepteID;
	private String nosaukums;
	private int pagatavosanasLaiks;
	private Timestamp pievienosanasDatums;
	private String receptesApraksts;
	private int lietotajsID;
	private String lietotajvards;
	private int kategorija;
	
	
	private Boolean irDefinets = false; //Nav ideals risinajums

	public RecipeType() {
		this.irDefinets = false;	
	}
	
	
	public RecipeType(int recepteID, String nosaukums, int pagatavosanasLaiks, Timestamp timestamp, String receptesApraksts, int lietotajsID, String lietotajvards, int kategorija) {
		this.recepteID = recepteID;
		this.nosaukums = nosaukums;
		this.pagatavosanasLaiks = pagatavosanasLaiks;
		this.receptesApraksts = receptesApraksts;
		this.pievienosanasDatums = timestamp;
		this.lietotajsID = lietotajsID;
		this.lietotajvards = lietotajvards;
		this.kategorija = kategorija;
		this.irDefinets = true;
	}


	//getters and setters
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

	public int getPagatavosanasLaiks() {
		return pagatavosanasLaiks;
	}

	public void setPagatavosanasLaiks(int pagatavosanasLaiks) {
		this.pagatavosanasLaiks = pagatavosanasLaiks;
	}

	public Timestamp getPievienosanasDatums() {
		return pievienosanasDatums;
	}


	public void setPievienosanasDatums(Timestamp pievienosanasDatums) {
		this.pievienosanasDatums = pievienosanasDatums;
	}

	public String getReceptesApraksts() {
		return receptesApraksts;
	}

	public void setReceptesApraksts(String receptesApraksts) {
		this.receptesApraksts = receptesApraksts;
	}

	public int getLietotajsID() {
		return lietotajsID;
	}

	public void setLietotajsID(int lietotajsID) {
		this.lietotajsID = lietotajsID;
	}

	public String getLietotajvards() {
		return lietotajvards;
	}

	public void setLietotajvards(String lietotajvards) {
		this.lietotajvards = lietotajvards;
	}
	
	public int getKategorija() {
		return kategorija;
	}

	public void setKategorija(int kategorija) {
		this.kategorija = kategorija;
	}

	public Boolean getIrDefinets() {
		return irDefinets;
	}

	public void setIrDefinets(Boolean irDefinets) {
		this.irDefinets = irDefinets;
	}


	@Override
	public String toString() {
		return "RecipeType [recepteID=" + recepteID + ", nosaukums=" + nosaukums + ", pagatavosanasLaiks="
				+ pagatavosanasLaiks + ", pievienosanasDatums=" + pievienosanasDatums + ", receptesApraksts="
				+ receptesApraksts + ", lietotajsID=" + lietotajsID + ", lietotajvards=" + lietotajvards
				+ ", irDefinets=" + irDefinets + "]";
	}
}
