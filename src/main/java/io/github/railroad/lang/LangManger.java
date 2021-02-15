package io.github.railroad.lang;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author TheOnlyTails
 */
public interface LangManger {
    ResourceBundle ENGLISH = ResourceBundle.getBundle("assets.lang.en", Locale.ENGLISH);
    ResourceBundle ITALIAN = ResourceBundle.getBundle("assets.lang.it", new Locale("it"));
    ResourceBundle SPANISH = ResourceBundle.getBundle("assets.lang.es", new Locale("es"));
    ResourceBundle CHINESE = ResourceBundle.getBundle("assets.lang.zh", new Locale("zh"));

    static String getLocalization(String key, ResourceBundle bundle) {
        try {
            /*
             TODO: once we have a working settings menu, remove the bundle parameter and use the bundle set in the settings
             */
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            System.err.printf("Localization key %s was not found.\n", key);
            return key;
        }
    }
}
