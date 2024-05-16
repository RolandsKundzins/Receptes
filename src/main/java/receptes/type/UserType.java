//Vairak par spring security: https://www.youtube.com/watch?v=QwQuro7ekvc

package receptes.type;


public class UserType {
	private int lietotajsID;
	private String epasts;
	private String parole;
	private String lietotajvards;
	//private String apraksts; //TODO nez kur sitas tiks lietots?
	//private attels - ir datubazee
	//private izveidesDatums - ir datubazee
	private Boolean irAktivs; // - ir datubazee, bet bez administratora nav jegas
	

	public UserType() {
	}
	
	public UserType(int lietotajsID, String epasts, String parole, String lietotajvards, Boolean irAktivs) {
		this.lietotajsID = lietotajsID;
		this.epasts = epasts;
		this.parole = parole;
		this.lietotajvards = lietotajvards;
		this.irAktivs = irAktivs;
	}
	
	
	//GETTERS AND SETTERS
	public int getLietotajsID() {
		return lietotajsID;
	}
	public void setLietotajsID(int lietotajsID) {
		this.lietotajsID = lietotajsID;
	}

	public String getEpasts() {
		return epasts;
	}
	public void setEpasts(String epasts) {
		this.epasts = epasts;
	}

	public String getParole() {
		return parole;
	}
	public void setParole(String parole) {
		this.parole = parole;
	}

	public String getLietotajvards() {
		return lietotajvards;
	}
	public void setLietotajvards(String lietotajvards) {
		this.lietotajvards = lietotajvards;
	}

	public Boolean getIrAktivs() {
		return irAktivs;
	}
	public void setIrAktivs(Boolean irAktivs) {
		this.irAktivs = irAktivs;
	}

	@Override
	public String toString() {
		return "UserType [epasts=" + epasts + ", parole=" + parole + ", lietotajvards=" + lietotajvards + ", irAktivs="
				+ irAktivs + "]";
	}
}
