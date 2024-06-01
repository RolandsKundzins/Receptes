package receptes.type;

import java.sql.Timestamp;

public class RecipeViewType {
	private int LietotajsRecepteSkatitsID;
	private String lietotajvardsSkatitajs; //tabulā pagaidām saucās kā "lietotajvārds"
	private int recepteID;
	private Timestamp skatLaiks; //šo var neaizpildīt, ja paredzēts, ka tiks izmantota db noklusējuma vērtība
	
	
	//Konstruktors, kuru izmanto insertam, jo datumu un id nosaka datubāze
	public RecipeViewType(String lietotajvardsSkatitajs, int recepteID) {
		this.lietotajvardsSkatitajs = lietotajvardsSkatitajs;
		this.recepteID = recepteID;
	}
	
	public RecipeViewType(int LietotajsRecepteSkatitsID, String lietotajvardsSkatitajs, int recepteID, Timestamp skatLaiks) {
		this.LietotajsRecepteSkatitsID = LietotajsRecepteSkatitsID;
		this.lietotajvardsSkatitajs = lietotajvardsSkatitajs;
		this.recepteID = recepteID;
		this.skatLaiks = skatLaiks;
	}


	//GETTERS AND SETTERS
	public String getLietotajvardsSkatitajs() {
		return lietotajvardsSkatitajs;
	}
	public void setLietotajvardsSkatitajs(String lietotajvardsSkatitajs) {
		this.lietotajvardsSkatitajs = lietotajvardsSkatitajs;
	}
	

	public int getLietotajsRecepteSkatitsID() {
		return LietotajsRecepteSkatitsID;
	}
	public void setLietotajsRecepteSkatitsID(int lietotajsRecepteSkatitsID) {
		LietotajsRecepteSkatitsID = lietotajsRecepteSkatitsID;
	}

	public int getRecepteID() {
		return recepteID;
	}
	public void setRecepteID(int recepteID) {
		this.recepteID = recepteID;
	}


	public Timestamp getSkatLaiks() {
		return skatLaiks;
	}
	public void setSkatLaiks(Timestamp skatLaiks) {
		this.skatLaiks = skatLaiks;
	}
}
