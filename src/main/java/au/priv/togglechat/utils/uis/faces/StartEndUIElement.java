

package au.priv.togglechat.utils.uis.faces;

/**
 * An element which has a predefined rectangular area.
 * <p>
 * Generally used in tandem with {@link InteractiveUIElement}
 */
public interface StartEndUIElement {
    
    /**
     * Returns the x-plane position of the coordinate in the top left of the rectangle
     *
     * @return the x value of the element
     */
    public int getX();
    
    /**
     * Returns the y-plane position of the coordinate in the top left of the rectangle
     *
     * @return the y value of the element
     */
    public int getY();
    
    /**
     * Returns the distance from the initial x-plane position to the final x-plane position
     *
     * @return the width of the element
     */
    public int getWidth();
    
    /**
     * Returns the distance from the initial y-plane position to the final y-plane position
     *
     * @return the height of the element
     */
    public int getHeight();
}
