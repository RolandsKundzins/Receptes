package receptes.type;

import java.sql.Timestamp;

public class StatisticsType {
	private int statistikaID;
	private String lietotajvardsSkatitajs; //tabulā pagaidām saucās kā "lietotajvārds"
	private int recepteID;
	private Timestamp skatLaiks; //šo var neaizpildīt, ja paredzēts, ka tiks izmantota db noklusējuma vērtība
	
	
	public StatisticsType(int statistikaID, String lietotajvardsSkatitajs, int recepteID, Timestamp skatLaiks) {
		this.statistikaID = statistikaID;
		this.lietotajvardsSkatitajs = lietotajvardsSkatitajs;
		this.recepteID = recepteID;
		this.skatLaiks = skatLaiks;
	}


	//GETTERS AND SETTERS
	public int getStatistikaID() {
		return statistikaID;
	}
	public void setStatistikaID(int statistikaID) {
		this.statistikaID = statistikaID;
	}


	public String getSkatitajsLietotajvards() {
		return lietotajvardsSkatitajs;
	}
	public void setSkatitajsLietotajvards(String lietotajvards) {
		this.lietotajvardsSkatitajs = lietotajvards;
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
