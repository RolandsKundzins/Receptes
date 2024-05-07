//Vairak par spring security: https://www.youtube.com/watch?v=QwQuro7ekvc

package receptes.type;


public class UserType {
//	private int lietotajsId;
	private String epasts;
	private String parole;
	private String lietotajvards;
	//private String apraksts; //TODO nez kur sitas tiks lietots?
	//private attels - ir datubazee
	//private izveidesDatums - ir datubazee
	private Boolean irAktivs; // - ir datubazee, bet bez administratora nav jegas
	
	private Boolean isDefined = false; //Nav ideals risinajums

	public UserType() {
		isDefined = false;
	}
	
	public UserType(String epasts, String parole, String lietotajvards, Boolean irAktivs) {
//		super();
		this.epasts = epasts;
		this.parole = parole;
		this.lietotajvards = lietotajvards;
		this.irAktivs = irAktivs;
		isDefined = true;
	}
	
	
	public Boolean getIsDefined() {
		return isDefined;
	}
	
	
	public String getEpasts() {
		return epasts;
	}

	public void setEpasts(String epasts) {
		this.epasts = epasts;
	}

	public String getPassword() {
		return parole;
	}

	public String getUsername() {
		return lietotajvards;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return irAktivs;
	}

	@Override
	public String toString() {
		return "UserType [epasts=" + epasts + ", parole=" + parole + ", lietotajvards=" + lietotajvards + ", irAktivs="
				+ irAktivs + "]";
	}
}
