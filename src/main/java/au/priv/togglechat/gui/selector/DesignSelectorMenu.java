

package au.priv.togglechat.gui.selector;

import au.priv.togglechat.ToggleChatMod;
import au.priv.togglechat.config.ConfigLoader;
import au.priv.togglechat.gui.core.MainGui;
import au.priv.togglechat.gui.redesign.NewMainUI;
import au.priv.togglechat.gui.selector.elements.LegacyMenuBoxedElement;
import au.priv.togglechat.gui.selector.elements.NewMenuBoxedElement;
import au.priv.togglechat.utils.ChatColor;
import au.priv.togglechat.utils.uis.ModernGui;

import java.awt.Color;

/**
 * A menu to display the different designs of the mod, to condense the logic in this class, rendering is done
 * in the respective {@link au.priv.togglechat.gui.selector.elements.BoxedElement} implementations.
 *
 * This menu is designed to give users a choice between migrating to the new menu, or sticking with the old EOL one.
 *
 * @author boomboompower
 * @since 3.1.37
 */
public class DesignSelectorMenu extends ModernGui {
    
    // Stores the instance of the config loader.
    private final ConfigLoader configLoader;
    
    // True if the config should be saved.
    private boolean themeChanged;
    
    public DesignSelectorMenu() {
        // Just cache this for later.
        this.configLoader = ToggleChatMod.getInstance().getConfigLoader();
    }
    
    @Override
    public void onGuiOpen() {
        // Register the superior boxed element
        // Takes the user to the new, fresh, squeaky clean menu
        registerElement(new NewMenuBoxedElement(this.width / 2 - 160, this.height / 2 - 20, 140, 80, (v) -> {
            updateTheme(UITheme.NEW);
            
            new NewMainUI().display();
        }));
    
        // Register the inferior boxed element
        // Takes the user to the dirty, old, legacy menu
        registerElement(new LegacyMenuBoxedElement(this.width / 2 + 20, this.height / 2 - 20, 140, 80, (v) -> {
            updateTheme(UITheme.LEGACY);
            
            new MainGui(1).display();
        }));
    }
    
    @Override
    public void preRender(int mouseX, int mouseY, float partialTicks) {
        // Always gotta remember to draw that sick gradient right
        drawDefaultBackground();
    }
    
    @Override
    public void onRender(int mouseX, int mouseY, float partialTicks) {
        // Draws the welcome to the screen
        drawCenteredString(this.fontRendererObj, "Welcome to ToggleChat", this.width / 2, 15, Color.WHITE.getRGB());
        drawCenteredString(this.fontRendererObj, "Please select the design you'd like to use", this.width / 2, 29, Color.WHITE.getRGB());
        
        // Red or blue pill?
        drawCenteredString(this.fontRendererObj, ChatColor.BOLD + "Pick your Poison", this.width / 2, this.height / 2 - 55, Color.WHITE.getRGB());
        drawCenteredString(this.fontRendererObj, ChatColor.BOLD + "or", this.width / 2, this.height / 2 + 15, Color.WHITE.getRGB());
    }
    
    /**
     * Triggers a theme update, and changes the config setting if
     * the selected theme differs from the currently stored value.
     * Doing this will also trigger the save to call when the gui close
     * is executed later down the track.
     *
     * @param theme the theme to set this to.
     */
    private void updateTheme(UITheme theme) {
        // Collect the old preference
        UITheme oldTheme = this.configLoader.getUITheme();
        
        // Preference hasn't changed
        if (oldTheme == theme) {
            return;
        }
        
        // Mark the config as dirty
        this.themeChanged = true;
        
        // Set the new theme
        this.configLoader.setUITheme(theme);
    }
    
    @Override
    public void onGuiClose() {
        // If no changes were made do nothing
        if (!this.themeChanged) {
            return;
        }
        
        // Save the config
        this.configLoader.saveModernUtils();
    }
}
