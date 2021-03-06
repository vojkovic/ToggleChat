

package au.priv.togglechat.gui.redesign;

import net.minecraft.client.renderer.GlStateManager;

import au.priv.togglechat.ToggleChatMod;
import au.priv.togglechat.gui.core.MainGui;
import au.priv.togglechat.gui.list.ViewListUI;
import au.priv.togglechat.gui.modern.ModernConfigGui;
import au.priv.togglechat.gui.selector.DesignSelectorMenu;
import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;
import au.priv.togglechat.utils.ChatColor;
import au.priv.togglechat.utils.uis.ModernGui;
import au.priv.togglechat.utils.uis.components.ButtonComponent;
import au.priv.togglechat.utils.uis.components.CheckboxTextExtensionComponent;
import au.priv.togglechat.utils.uis.components.LabelComponent;
import au.priv.togglechat.utils.uis.components.ScrollComponent;

import java.awt.Color;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A layout for the ToggleChat main menu, displays all currently displayed toggles with descriptions.
 *
 * This version is a complete rewrite of its predecessor, and aim's to incorporate grouping properly.
 * The core difference between this version and it's predecessor, is this version makes use of vertical
 * scrolling whilst the previous version made use of pagination instead. Both have their uses which is
 * why the original version is (as of now) still available to use, view and modify in the legacy
 * {@link MainGui} menu.
 *
 * @author boomboompower
 * @since 3.1.37
 */
public class NewMainUI extends ModernGui {
    
    // The backdrop colour is the colour we use behind the left panel
    private final int backdropColour = new Color(0, 0, 0, 100).getRGB();
    // The line colour is the colour of the vertical line which is rendered 1 third of the viewport from the left.
    private final int lineColour = new Color(200, 200, 200, 200).getRGB();
    
    // Stores the cached backdrop width (so we don't have to do as many calcs per frame).
    private int backDropWidth = 0;
    // Stores the final Y position of the elements drawn on the left of the screen
    private int finalYPos;
    
    // If this is true then the config will be overwritten when the UI closes.
    private boolean changesMade = false;
    
    // We want to store the active scrolling component
    // so we can retrieve it's scrolling values :)
    private ScrollComponent scrollComponent = null;
    
    @Override
    public void onGuiOpen() {
        // We want to use half the font height to correctly position
        // most of the label's we'll draw onto the screen.
        int halfFontHeight = this.fontRendererObj.FONT_HEIGHT / 2;
        
        // The backdrop width is 1 third of the current view window.
        this.backDropWidth = (this.width / 3);
        
        // The rendering X position for all the information text will occur 20 pixels
        // after the backdrop ends. This is sufficient space on essentially every resolution.
        int renderTextX = this.backDropWidth + 20;
        
        // Start of at y position 10 (which is where we will render the title).
        int yPos = 10;
    
        // Collect all the currently registered toggles for the mod.
        Collection<ToggleBase> toggles = ToggleChatMod.getInstance().getToggleHandler().getToggles().values();
        
        // Iterate through all the different category types for toggles.
        for (Categories category : Categories.values()) {
            // Collect all toggles which have this category. Whilst stream can occasionally be a bit slower than
            // I'd like, this is ___ok___  *sigh*, but for a large scale application I'd probably cache this somewhere.
            Collection<ToggleBase> categoryToggles = toggles.stream().filter(base -> base.getCategory() == category).collect(Collectors.toList());
    
            // Skip the category if there are no toggles in it (such as for favourites, custom toggles, etc).
            if (categoryToggles.size() == 0) {
                continue;
            }
            
            // Register a label containing the category name in bold.
            registerElement(new LabelComponent(10, yPos + halfFontHeight, ChatColor.YELLOW.toString() + ChatColor.BOLD + category.getDisplayName()));
    
            // Move down from the category name
            yPos += 15;
    
            // Iterate through each toggle which is part of this category and add all
            // its corresponding elements to the window.
            //
            // Note: CheckboxTextExtensionComponent (wow what a name), extends functionality of
            // The regular CheckboxComponent by adding drawable lines. I was thinking of
            // Putting the checkbox and label into one class for hovering, however I didn't want
            // to mix their code together, which is why I opted to just register two individual
            // nodes. The downside to this approach is the user currently has to hover over the checkbox
            // to actually see the toggles description.
            for (ToggleBase toggle : categoryToggles) {
                // Adds the label for the toggle based on the name
                // TODO create a dedicated method in the toggle class containing
                //      to separate the internal id for the toggle and the actual display name.
                registerElement(new LabelComponent(20, yPos + halfFontHeight, toggle.getName()));
                
                // Adds the button for toggling stuff
                registerElement(new CheckboxTextExtensionComponent(this.backDropWidth - 20, yPos, 10, 10, toggle.isEnabled(), box -> {
                    // Indicate that the config
                    // will need to be saved.
                    this.changesMade = true;
                    
                    // Switch the toggle state.
                    toggle.setEnabled(!toggle.isEnabled());
                    
                    // Copy the state from the toggle.
                    box.setChecked(toggle.isEnabled());
                }, renderTextX, this.height / 2, 15, toggle.getDescription()));
        
                // Move down the window
                yPos += 15;
            }
            
            // Move further down the window.
            // This separates the categories from each other.
            yPos += 15;
        }
        
        this.finalYPos = yPos;
    
        // Displays the theme editor menu.
        registerElement(new ButtonComponent(2, this.width - 105, this.height - 75, 75, 20, "Theme Editor", btn -> {
            new ModernConfigGui(this).display();
        }));
        
        // Displays the allow list menu
        registerElement(new ButtonComponent(1, this.width - 105, this.height - 50, 75, 20, "Allow List", btn -> {
            new ViewListUI(this).display();
        }));
        
        // Displays the design selector menu
        registerElement(new ButtonComponent(0, this.width - 105, this.height - 25, 75, 20, "Select Theme", btn -> {
            new DesignSelectorMenu().display();
        }));
        
        // Adds the scrollbar which modifies the y translation on the page.
        registerElement(this.scrollComponent = new ScrollComponent(this.width - 20, 5, 12, this.height - 10, component -> {
            this.yTranslation = -(this.finalYPos - this.height) * this.scrollComponent.getCurrentScroll();
        }));
    }
    
    @Override
    public void preRender(int mouseX, int mouseY, float partialTicks) {
        // Draw the default background as always.
        drawDefaultBackground();
        
        // Draw the left strip background and vertical line.
        ModernGui.drawRect(0, 0, this.backDropWidth, this.height, this.backdropColour);
        ModernGui.drawVerticalLine_(this.backDropWidth, 0, this.height, this.lineColour);
    }
    
    @Override
    public void onRender(int mouseX, int mouseY, float partialTicks) {
        // Pushes a new matrix to the stack, making a new gl matrix stack
        GlStateManager.pushMatrix();
        // Upscales the matrix
        GlStateManager.scale(1.5, 1.5, 1.5);
        
        // Calculates the width of the text
        float width = this.fontRendererObj.getStringWidth("ToggleChat");
        
        // Draws the string to the screen
        drawString(this.fontRendererObj, "ToggleChat", ((this.width - 30) / 1.5f) - width, 5, Color.WHITE.getRGB());
        
        // Pops the matrix - Essentially clearing the gl state
        GlStateManager.popMatrix();
        
        // Make another matrix stack
        GlStateManager.pushMatrix();
        // Downscale this matrix
        GlStateManager.scale(0.85, 0.85, 0.85);
    
        // Calculate the width of this string
        width = this.fontRendererObj.getStringWidth("by boomboompower");
    
        // Draw the string to the screen
        drawString(this.fontRendererObj, "by boomboompower", ((this.width - 30) / 0.85f) - width, 25, Color.WHITE.getRGB());
    
        // Pop the matrix once more, clearing the stack again.
        GlStateManager.popMatrix();
    }
    
    @Override
    public void onScrollUp() {
        // When we scroll up we want to tell the scroll
        // component that the mouse has moved up.
        if (this.scrollComponent != null) {
            this.scrollComponent.onScroll(1);
        }
    }
    
    @Override
    public void onScrollDown() {
        // Similar to above, when we scroll down we
        // want to send this to the scroll component so
        // it can dictate how much the scrollbar should
        // be moved, which will be sent back here and interpreted
        // as a yTranslation on this menu.
        if (this.scrollComponent != null) {
            this.scrollComponent.onScroll(-1);
        }
    }
    
    @Override
    public void onGuiClose() {
        // If no changes have been made just exit gracefully.
        if (!this.changesMade) {
            return;
        }
        
        // TODO code some logic which actually tracks changed toggles? so if someone toggles something
        //      twice (e.g disabling then enabling the same thing) we don't need to save. This is not
        //      really crucial since most user's won't notice saving anyway right?.
        //      It would be a nice QOL feature to have though, even for those unaware of its presence.
    
        // Save all the toggles
        ToggleChatMod.getInstance().getConfigLoader().saveToggles();
    }
}