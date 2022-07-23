

package au.priv.togglechat.commands.impl;

import au.priv.togglechat.ToggleChatMod;
import au.priv.togglechat.commands.ModCommand;
import au.priv.togglechat.gui.core.MainGui;
import au.priv.togglechat.gui.redesign.NewMainUI;
import au.priv.togglechat.gui.selector.DesignSelectorMenu;
import au.priv.togglechat.gui.selector.UITheme;
import net.minecraft.command.ICommandSender;
import au.priv.togglechat.utils.uis.ModernGui;

import java.util.Arrays;
import java.util.List;

/**
 * The core ToggleCommand command
 */
public class ToggleCommand extends ModCommand {
    
    // Cached main menu, saves memory and options.
    private ModernGui mainMenu = null;
    
    public ToggleCommand(ToggleChatMod modIn) {
        super(modIn);
    }
    
    @Override
    public String getCommandName() {
        return "tc";
    }
    
    @Override
    public List<String> getAliases() {
        return Arrays.asList("chattoggle");
    }
    
    @Override
    public void onCommand(ICommandSender sender, String[] args) {
        UITheme theme = this.mod.getConfigLoader().getUITheme();
        
        // Display the cached menu.
        switch (theme) {
            case UNKNOWN:
                new DesignSelectorMenu().display();
                break;
            case NEW:
            case LEGACY:
                getMenu(theme).display();
        }
    }
    
    @Override
    protected boolean shouldMultithreadCommand(String[] args) {
        return true;
    }
    
    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }
    
    /**
     * Gets the cached ToggleChat menu
     *
     * @return the cached ToggleChat menu if one exists, or a new one.
     */
    private ModernGui getMenu(UITheme theme) {
        // The user has changed their preference.
        if (this.mainMenu != null && this.mainMenu.getClass() != theme.getThemeClass()) {
            this.mainMenu = null;
        }
    
        // Create a new cached menu if none exists
        if (this.mainMenu == null) {
            switch (theme) {
                case LEGACY:
                    this.mainMenu = new MainGui(1);
                    break;
                case NEW:
                    this.mainMenu = new NewMainUI();
                    break;
            }
        }
        
        // Return the cached menu
        return this.mainMenu;
    }
}
