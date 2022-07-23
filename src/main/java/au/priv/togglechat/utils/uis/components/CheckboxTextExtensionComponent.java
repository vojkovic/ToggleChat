

package au.priv.togglechat.utils.uis.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import au.priv.togglechat.utils.ChatColor;
import au.priv.togglechat.utils.options.SimpleCallback;

import java.awt.Color;

/**
 * An extension of the checkbox element to render a string to the screen when the checkbox
 * is being hovered by the client. This is a bit of a hack however it's a lot better than
 * using the dummy toggle as a renderer.
 *
 * @author boomboompower
 * @since 3.1.37
 */
public class CheckboxTextExtensionComponent extends CheckboxComponent {
    
    private final int renderX;
    private final int renderY;
    private final int lineSeparation;
    
    private final String[] toRender;
    
    public CheckboxTextExtensionComponent(int xPos, int yPos, int width, int height, boolean checked, SimpleCallback<CheckboxComponent> callback, int renderX, int renderY, int lineSeparation, String... textToRender) {
        super(xPos, yPos, width, height, checked, callback);
        
        this.toRender = textToRender;
        
        this.lineSeparation = lineSeparation;
        this.renderX = renderX;
        this.renderY = renderY;
    }
    
    @Override
    public void render(int mouseX, int mouseY, float yTranslation, float partialTicks) {
        super.render(mouseX, mouseY, yTranslation, partialTicks);
        
        // Use our preexisting check!
        if (!this.isHovered()) {
            return;
        }
    
        FontRenderer renderer = Minecraft.getMinecraft().fontRendererObj;
        int renderingYPosition = (int) (this.renderY - ((this.toRender.length / 2) * this.lineSeparation) - yTranslation);
    
        for (String line : this.toRender) {
            if (line.isEmpty()) {
                renderingYPosition += this.lineSeparation;
                
                continue;
            }
            
            // Colour the text!
            line = ChatColor.translateAlternateColorCodes(line);
            
            renderer.drawString(line, this.renderX, renderingYPosition, Color.WHITE.getRGB(), false);
            
            renderingYPosition += this.lineSeparation;
        }
    }
}
