package po2.modele;

/**
 * Enumération définissant les diplômes de référence
 */
public enum REF_DIPLOME {

    BTS_SIO_SLAM("BTS SIO SLAM"),
    BTS_SN_IR("BTS SN IR"),
    DUT_INFO("DUT informatique"),
    LICENCE_INFO("Licence informatique");

    private final String str;

    REF_DIPLOME(String str) {
        this.str = str;
    }
    
    public static REF_DIPLOME getRefFromString(String str) {
    	if (str.equals("Licence"))
    		return LICENCE_INFO;
    	else if (str.equals("DUT"))
    		return DUT_INFO;
    	for (int i=0; i<REF_DIPLOME.values().length; i++) {
    		if (REF_DIPLOME.values()[i].toString().equals(str))
    			return REF_DIPLOME.values()[i];
    	}
    	return null;
    }

    /**
     * @return une chaîne de caractères représentant le diplome de reférence
     */
    @Override
    public String toString() {
        return str;
    }
}
