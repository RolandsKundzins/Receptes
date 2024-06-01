package receptes.type;

import java.time.LocalDate;

public class StatisticsByDateType {
	private LocalDate datums;
	private int skatijumuSkaits;
	private int patikSkaits;
	
	public StatisticsByDateType() {
	}
	
	
	public StatisticsByDateType(LocalDate datums, int skatijumuSkaits, int patikSkaits) {
		this.datums = datums;
		this.skatijumuSkaits = skatijumuSkaits;
		this.patikSkaits = patikSkaits;
	}

	
	//GETTERS AND SETTERS
	public LocalDate getDatums() {
		return datums;
	}

	public void setDatums(LocalDate datums) {
		this.datums = datums;
	}

	public int getSkatijumuSkaits() {
		return skatijumuSkaits;
	}

	public void setSkatijumuSkaits(int skatijumuSkaits) {
		this.skatijumuSkaits = skatijumuSkaits;
	}

	public int getPatikSkaits() {
		return patikSkaits;
	}

	public void setPatikSkaits(int patikSkaits) {
		this.patikSkaits = patikSkaits;
	}
}
