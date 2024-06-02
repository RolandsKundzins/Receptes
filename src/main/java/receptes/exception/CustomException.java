package receptes.exception;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	//Šo lietot iekš CATCH bloka! Piemēram, datubāze nav pieejama
    public CustomException(String message, Throwable e) {
        super(message, e);
    }
	
    //Šo lietot, ja exception nav pieejams - kļūda tiek izmesta ne esoša exception dēļ, bet jauna loģikas kļūda.
	public CustomException(String message) {
        super(message);
    }
}
