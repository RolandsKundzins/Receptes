package receptes.type;

import java.time.LocalDate;

public class StatisticsByDateType {
	private LocalDate datums;
	private int skatijumuSkaits; //tabulā pagaidām saucās kā "lietotajvārds"
	
	
	public StatisticsByDateType(LocalDate datums, int skatijumuSkaits) {
		this.datums = datums;
		this.skatijumuSkaits = skatijumuSkaits;
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

}
