package br.gov.inpi.epec.util;

import java.util.ResourceBundle;

public final class I18N {
	
	/** I18N. */
    private static final ResourceBundle BUNDLE =  ResourceBundle.getBundle("i18n");

    public static String getString(String key) {
    	return BUNDLE.getString(key);
    }
	
}
