package zpi.mobiletoring;

/**
 * 
 * ConfHolder to singleton, przechowujacy konfiguracje- adresy strumieni z poszczegolnych kamer.
 * @author Mobiletoring Team
 *
 */
public final class ConfHolder {
	
	/**
	 * Przechowuje nazwe zewnetrznych preferencji, gdzie przechowywana jest konfiguracja po zamknieciu aplikacji.
	 */
	public static final String PREFERENCES_NAME="LastVideoPreferences";
	
    private final static ConfHolder ourInstance = new ConfHolder();
    private String camera1="";
    private String camera2="";
    
    /**
     * Zwraca instancje ConfHoldera.
     * @return ConfHolder
     */
    public static ConfHolder getInstance() {
        return ourInstance;
    }
 
    //¿eby unikn¹æ automatycznego tworzenia domyœlnego, publicznego, bezargumentowego konstruktora
    private ConfHolder() {
    }

    /**
     * Zwraca adres URL strumienia z pierwszej kamery.
     * @return camera1- adres URL strumienia z pierwszej kamery.
     */
	public String getCamera1() {
		return camera1;
	}
	
	
	/**
	 * Pozwala na ustawienie adresu URL strumienia z pierwszej kamery.
	 * @param camera1- adres URL strumienia z pierwszej kamery. 
	 */
	public void setCamera1(String camera1) {
		this.camera1 = camera1;
	}

    /**
     * Zwraca adres URL strumienia z pierwszej kamery.
     * @return camera2- adres URL strumienia z drugiej kamery.
     */
	public String getCamera2() {
		return camera2;
	}

	/**
	 * Pozwala na ustawienie adresu URL strumienia z drugiej kamery.
	 * @param camera2- adres URL strumienia z drugiej kamery. 
	 */
	public void setCamera2(String camera2) {
		this.camera2 = camera2;
	}
}
