package io.github.railroad.lang;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Handles localization and lang assets.
 *
 * @author TheOnlyTails
 */
public interface LangManager {
    /**
     * A ResourceBundle for the english lang assets.
     */
    ResourceBundle ENGLISH = ResourceBundle.getBundle("assets.lang.en", Locale.ENGLISH);
    /**
     * A ResourceBundle for the italian lang assets.
     */
    ResourceBundle ITALIAN = ResourceBundle.getBundle("assets.lang.it", new Locale("it"));
    /**
     * A ResourceBundle for the spanish lang assets.
     */
    ResourceBundle SPANISH = ResourceBundle.getBundle("assets.lang.es", new Locale("es"));
    /**
     * A ResourceBundle for the chinese lang assets.
     */
    ResourceBundle CHINESE = ResourceBundle.getBundle("assets.lang.zh", new Locale("zh"));

    /**
     * Retrieves the lang asset of the key based on the ResourceBundle selected.
     *
     * @param key    the localization key.
     * @param bundle the ResourceBundle which contains the lang assets.
     * @return the lang asset of the key.
     * @author TheOnlyTails
     */
    static String getLocalization(String key, ResourceBundle bundle) {
        try {
            /*
             TODO: once we have a working settings menu, remove the bundle parameter and use the bundle set in the settings
             */
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            System.err.printf("Localization key %s was not found.%n", key);
            return key;
        }
    }
}
