

package au.priv.togglechat.utils.uis.components;

import au.priv.togglechat.locale.Language;
import au.priv.togglechat.utils.options.SimpleCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * A type of ModernButton which supports translations.
 * <br>
 * <br>
 * An implementation of the {@link ButtonComponent} designed to adhere to the specifications in the Translation system.
 */
public class LocaleButtonComponent extends ButtonComponent {
    
    private final String title;
    private final String key;
    
    private String defaultValue;
    
    /**
     * A basic constructor for a LocaleButton. With no callback (for legacy reasons)
     *
     * @param buttonId the id of the button (deprecated). Use callbacks.
     * @param x the x position of the button
     * @param y the y position of the button
     * @param widthIn the width of the button
     * @param heightIn the height of the button
     * @param title the title of the button (to remember what the translation is for)
     * @param key the translation key for the button.
     */
    public LocaleButtonComponent(int buttonId, int x, int y, int widthIn, int heightIn, String title, String key) {
        super(buttonId, x, y, widthIn, heightIn, Language.format(key));
        
        this.title = title;
        this.key = key;
    }
    
    /**
     * A basic constructor for a LocaleButton
     *
     * @param buttonId the id of the button (deprecated). Use callbacks.
     * @param x the x position of the button
     * @param y the y position of the button
     * @param widthIn the width of the button
     * @param heightIn the height of the button
     * @param title the title of the button (to remember what the translation is for)
     * @param key the translation key for the button.
     * @param clicked the event which is called when the button is clicked (supersedes buttonId)
     */
    public LocaleButtonComponent(int buttonId, int x, int y, int widthIn, int heightIn, String title, String key, SimpleCallback<LocaleButtonComponent> clicked) {
        super(buttonId, x, y, widthIn, heightIn, Language.format(key), clicked);
        
        this.title = title;
        this.key = key;
    }
    
    
    /**
     * A basic constructor for a LocaleButton
     *
     * @param buttonId the id of the button (deprecated). Use callbacks.
     * @param x the x position of the button
     * @param y the y position of the button
     * @param widthIn the width of the button
     * @param heightIn the height of the button
     * @param title the title of the button (to remember what the translation is for)
     * @param key the translation key for the button.
     * @param value the value of the button (formatted)
     * @param clicked the event which is called when the button is clicked (supersedes buttonId)
     */
    public LocaleButtonComponent(int buttonId, int x, int y, int widthIn, int heightIn, String title, String key, String value, SimpleCallback<LocaleButtonComponent> clicked) {
        super(buttonId, x, y, widthIn, heightIn, Language.format(key, value), clicked);
        
        this.title = title;
        this.key = key;
    }
    
    /**
     * Locates and attempts to use the lore based on the key
     *
     * @param key the key for the lore.
     * @param defaultVal the default value of the lore, appended to the end of the lore.
     *
     * @return this instance of the button.
     */
    public LocaleButtonComponent interpretLoreKey(String key, String defaultVal) {
        List<String> lines = new ArrayList<>(Language.getMultiLine(key));
        
        lines.add(" ");
        lines.add(Language.format("skinchanger.phrase.default", defaultVal));
        
        // Set the lore to our translation
        setMessageLines(lines);
        
        return this;
    }
    
    /**
     * Updates the title of the button with a value
     *
     * @param valueIn the value of the button
     */
    public void updateTitleWithValue(String valueIn) {
        String translatedString = Language.format(this.key, valueIn);
        
        // Fallback for untranslated keys.
        if (translatedString.equalsIgnoreCase(this.key)) {
            translatedString = this.title + "*: " + valueIn;
        }
        
        setText(translatedString);
    }
    
    public LocaleButtonComponent setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        
        return this;
    }
    
    /**
     * Returns the default value of the button
     *
     * @return a nullable string for the default value
     */
    public String getDefaultValue() {
        return this.defaultValue;
    }
    
    /**
     * Returns the translation key for the button
     *
     * @return a non-null translation key for the button
     */
    public String getKey() {
        return this.key;
    }
}
