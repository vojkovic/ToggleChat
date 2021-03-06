

package au.priv.togglechat.utils.uis.faces;

import au.priv.togglechat.utils.uis.components.ButtonComponent;

/**
 * Provides the base methods for a Gui, these methods are designed to be applicable to multiple Minecraft versions.
 * <br>
 * It should be noted that all render methods are ran in sandboxed environments meaning if an error occurs, the game will not crash.
 *
 * @author boomboompower
 * @version 1.0
 * @since 3.0.0
 */
public interface UISkeleton {
    
    /**
     * Called when the GUI is first opened. Equivalent to initGui
     */
    public void onGuiOpen();
    
    /**
     * Called when the GUI is closed.
     */
    public default void onGuiClose() {
    }
    
    /**
     * Called just before the render method is called. Should not be used for rendering. Use for logic methods.
     * <br />
     * This method will not cause a crash on an error.
     *
     * @param mouseX the x position of the mouse
     * @param mouseY the y position of the mouse
     */
    public default void preRender(int mouseX, int mouseY, float partialTicks) {
    }
    
    /**
     * Called during the render method. Use this for rendering.
     * <br />
     * This method will not cause a crash on an error
     *
     * @param mouseX       the x position of the mouse
     * @param mouseY       the y position of the mouse
     * @param partialTicks from timer - how much time has elapsed since the last tick, in ticks, for
     *                     use by display rendering routines (range: 0.0 - * 1.0). This field is frozen if the display
     *                     is paused to eliminate jitter.
     */
    public void onRender(int mouseX, int mouseY, float partialTicks);
    
    /**
     * Called after the render method. Use this for any post-processing / post-render logic.
     * <br />
     * This method will not cause a crash on an error
     */
    public default void postRender(float partialTicks) {
    }
    
    /**
     * Called when a {@link ButtonComponent} is left clicked.
     *
     * @param button the button that was left clicked.
     */
    public default void buttonPressed(ButtonComponent button) {
    }
    
    /**
     * Called when a {@link ButtonComponent} is right clicked.
     *
     * @param button the button that was right clicked.
     */
    public default void rightClicked(ButtonComponent button) {
    }
    
    /**
     * Called when the user scrolls up
     */
    public default void onScrollUp() {
    }
    
    /**
     * Called when the user scrolls down
     */
    public default void onScrollDown() {
    }
    
    /**
     * Called when a character is typed. Mimics the GuiScreen keyTyped functionality.
     *
     * @param keyCode      the keyCode of the character. See {@link org.lwjgl.input.Keyboard} to get codes.
     * @param keyCharacter the character representation of the key. Example: if {@link
     *                     org.lwjgl.input.Keyboard#KEY_A} was pressed then 'a' would be the character that is
     *                     returned.
     *
     * @return true if this event has been handled.
     */
    public default boolean onKeyTyped(int keyCode, char keyCharacter) {
        return false;
    }
}
