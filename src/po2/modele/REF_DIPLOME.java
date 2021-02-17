package po2.modele;

/**
 * Enum�ration d�finissant les dipl�mes de r�f�rence
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
     * @return une cha�ne de caract�res repr�sentant le diplome de ref�rence
     */
    @Override
    public String toString() {
        return str;
    }
}
