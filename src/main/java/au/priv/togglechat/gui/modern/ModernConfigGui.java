

package au.priv.togglechat.gui.modern;

import au.priv.togglechat.utils.uis.ToggleChatModernUI;
import au.priv.togglechat.utils.uis.ModernGui;
import au.priv.togglechat.utils.ChatColor;
import au.priv.togglechat.utils.uis.components.tc.ToggleChatButtonComponent;

public class ModernConfigGui extends ToggleChatModernUI {

    private final ModernGui previous;

    private boolean modified;

    public ModernConfigGui(ModernGui previous) {
        this.previous = previous;
        this.modified = false;
    }

    @Override
    public void onGuiOpen() {
        // Toggles the style of the buttons for the mod, between one which renders the texture
        // pack style and the other which is literally just a transparent rectangle for a background.
        registerElement(new ToggleChatButtonComponent(2, this.width / 2 - 75, this.height / 2 - 10, 150, 20, "Buttons: " + getClassic(this.configLoader.isModernButton()), button -> {
            this.modified = true;
    
            this.configLoader.setModernButton(!this.configLoader.isModernButton());
            button.setText("Buttons: " + getClassic(this.configLoader.isModernButton()));
        }).setButtonData(
                // Button editing
                "Changes the button", "theme to either", "&6Modern&r or &bClassic", "", "&6Modern&r is see-through", "&bClassic&r is texture based"
        ));
        
        // Toggles the style of the text boxes in the mod, between the one which appears when you're in
        // the command block and once more one which is just a rectangle with extra steps.
        registerElement(new ToggleChatButtonComponent(3, this.width / 2 - 75, this.height / 2 + 14, 150, 20, "Textbox: " + getClassic(this.configLoader.isModernTextbox()), button -> {
            this.modified = true;
    
            this.configLoader.setModernTextbox(!this.configLoader.isModernTextbox());
            button.setText("Textbox: " + getClassic(this.configLoader.isModernTextbox()));
        }).setButtonData(
                // Textbox editing
                "Changes the textbox", "theme to either", "&6Modern&r or &bClassic"
        ));
        
        // Standard button to return to the previous page.
        registerElement(new ToggleChatButtonComponent(4, 5, this.height - 25, 90, 20, "Back", button -> this.mc.displayGuiScreen(this.previous)));
    }
    
    @Override
    public void preRender(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
    }
    
    @Override
    public void onRender(int mouseX, int mouseY, float partialTicks) {
        writeInformation(this.width / 2, this.height / 2 + 40, 12,
                "&6Modern&r is our custom theme",
                "",
                "&bClassic&r is the default game",
                "theme, usually based on texturepacks"
        );
    }
    
    @Override
    public void postRender(float partialTicks) {
        checkHover(this.height / 2 - 75);
    }

    @Override
    public void onGuiClose() {
        if (!this.modified) {
            return;
        }

        this.configLoader.saveModernUtils();
    }

    private String getClassic(boolean config) {
        return (config ? ChatColor.AQUA + "Classic" : ChatColor.GOLD + "Modern");
    }
}
