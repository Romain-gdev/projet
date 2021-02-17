package po2.modele;

/**
 * Enumération definissant les séries du bac prises en compte
 */
public enum SERIE {
    S,
    L,
    ES,
    STI2D,
    STMG;
	
	public static SERIE getSerieFromString(String str) {
    	for (int i=0; i<SERIE.values().length; i++) {
    		if (SERIE.values()[i].toString().equals(str))
    			return SERIE.values()[i];
    	}
    	return null;
    }
}
