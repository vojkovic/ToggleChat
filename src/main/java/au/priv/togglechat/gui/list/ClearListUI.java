

package au.priv.togglechat.gui.list;

import au.priv.togglechat.utils.uis.ToggleChatModernUI;
import au.priv.togglechat.utils.uis.components.ButtonComponent;
import au.priv.togglechat.utils.uis.ModernGui;
import net.minecraft.client.Minecraft;

public class ClearListUI extends ToggleChatModernUI {

    private ModernGui previousScreen;
    private Minecraft mc;

    public ClearListUI(ModernGui previous) {
        this.previousScreen = previous;

        this.mc = Minecraft.getMinecraft();
    }

    @Override
    public void onGuiOpen() {
        registerElement(new ButtonComponent(0, this.width / 2 - 200, this.height / 2 + 30, 150, 20, "Cancel"));
        registerElement(new ButtonComponent(1, this.width / 2 + 50, this.height / 2 + 30, 150, 20, "Confirm"));
    }
    
    @Override
    public void preRender(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
    }
    
    @Override
    public void onRender(int mouseX, int mouseY, float partialTicks) {
        writeInformation(this.width / 2, this.height / 2 - 60, 15,
                String.format("Are you sure you wish to clear &6%s %s&r from your whitelist?", this.mod.getConfigLoader().getWhitelist().size(), (this.mod.getConfigLoader().getWhitelist().size() == 1 ? "entry" : "entries")),
                "This action cannot be undone, use at your own risk!"
        );
    }

    @Override
    public void buttonPressed(ButtonComponent button) {
        switch (button.getId()) {
            case 0:
                this.mc.displayGuiScreen(this.previousScreen);
                break;
            case 1:
                this.mod.getConfigLoader().getWhitelist().clear();
                sendChatMessage("Cleared the whitelist!");
                this.mc.displayGuiScreen(null);
                break;
        }
    }

    @Override
    public void onGuiClose() {
        this.mod.getConfigLoader().saveModernUtils();
    }
}
