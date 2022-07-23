

package au.priv.togglechat.utils.uis.faces;

import au.priv.togglechat.utils.uis.components.HeaderComponent;

public interface HeaderChildComponent extends ModernUIElement {
    
    /**
     * Renders the element from a header position. By default this will just call the {@link
     * #render(int, int, float, float)} method, however some {@link ModernUIElement}'s will react differently
     * to this change.
     *
     * @param xPos               the x position of the element
     * @param yPos               the y position of the element
     * @param yTranslation       the translation in the y axis
     * @param mouseX             the raw x location of the mouse
     * @param mouseY             the raw y location of the mouse
     * @param recommendedYOffset the recommended offset this {@link ModernUIElement} should follow (how
     *                           far down it should be shifted).
     */
    public default void renderFromHeader(int xPos, int yPos, float yTranslation, float partialTicks, int mouseX, int mouseY, int recommendedYOffset) {
        render(mouseX, mouseY, yTranslation, partialTicks);
    }
    
    /**
     * Tells this element to register itself as part of this header. Some elements will react
     * differently to this change
     *
     * @param parent the header which the element should be set under.
     */
    public void setAsPartOfHeader(HeaderComponent parent);
    
    /**
     * Should this element be rendered relative to its header (if its part of one)?
     *
     * @return true if the element should be moved based on header position.
     */
    public default boolean renderRelativeToHeader() {
        return true;
    }
}
