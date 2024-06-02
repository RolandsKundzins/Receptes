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
	private int kategorijaID;
	private String kategorijasNosaukums;
	private int skatSkaits;
	
	
	public RecipeType(int recepteID, String nosaukums, int pagatavosanasLaiks, Timestamp timestamp, String receptesApraksts, int lietotajsID, String lietotajvards, int kategorijaID, String kategorijasNosaukums, int skatSkaits) {
		this.recepteID = recepteID;
		this.nosaukums = nosaukums;
		this.pagatavosanasLaiks = pagatavosanasLaiks;
		this.receptesApraksts = receptesApraksts;
		this.pievienosanasDatums = timestamp;
		this.lietotajsID = lietotajsID;
		this.lietotajvards = lietotajvards;
		this.kategorijaID = kategorijaID;
		this.kategorijasNosaukums = kategorijasNosaukums;
		this.skatSkaits = skatSkaits;
	}
	
	public RecipeType(String nosaukums, int pagatavosanasLaiks, String receptesApraksts, int lietotajsID, int kategorijaID) {
		this.nosaukums = nosaukums;
		this.pagatavosanasLaiks = pagatavosanasLaiks;
		this.receptesApraksts = receptesApraksts;
		this.lietotajsID = lietotajsID;
		this.kategorijaID = kategorijaID;
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
	
	public int getKategorijaID() {
		return kategorijaID;
	}
	public void setKategorijaID(int kategorijaID) {
		this.kategorijaID = kategorijaID;
	}

	public String getKategorijasNosaukums() {
		return kategorijasNosaukums;
	}
	public void setKategorijasNosaukums(String kategorijasNosaukums) {
		this.kategorijasNosaukums = kategorijasNosaukums;
	}


	public int getSkatSkaits() {
		return skatSkaits;
	}
	public void setSkatSkaits(int skatSkaits) {
		this.skatSkaits = skatSkaits;
	}
}
