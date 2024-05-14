package receptes.type;

import java.sql.Timestamp;

public class StatisticsType {
	private int statistikaID;
	private String lietotajvards;
	private int recepteID;
	private Timestamp skatLaiks;
	
	
	public StatisticsType(int statistikaID, String lietotajvards, int recepteID, Timestamp skatLaiks) {
		this.statistikaID = statistikaID;
		this.lietotajvards = lietotajvards;
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


	public String getLietotajvards() {
		return lietotajvards;
	}
	public void setLietotajvards(String lietotajvards) {
		this.lietotajvards = lietotajvards;
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
