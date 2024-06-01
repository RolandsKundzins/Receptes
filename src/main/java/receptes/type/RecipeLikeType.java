package receptes.type;

public class RecipeLikeType {
	private boolean lietotajamPatikRecepte;
	private int patikSkaits;
	
	
	public RecipeLikeType(boolean lietotajamPatikRecepte, int patikSkaits) {
		this.lietotajamPatikRecepte = lietotajamPatikRecepte;
		this.patikSkaits = patikSkaits;
	}


	//GETTERS AND SETTERS
	public boolean isLietotajamPatikRecepte() {
		return lietotajamPatikRecepte;
	}

	public void setLietotajamPatikRecepte(boolean lietotajamPatikRecepte) {
		this.lietotajamPatikRecepte = lietotajamPatikRecepte;
	}

	public int getPatikSkaits() {
		return patikSkaits;
	}

	public void setPatikSkaits(int patikSkaits) {
		this.patikSkaits = patikSkaits;
	}
}
