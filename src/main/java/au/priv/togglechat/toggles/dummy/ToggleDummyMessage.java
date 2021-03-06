

package au.priv.togglechat.toggles.dummy;

import au.priv.togglechat.toggles.ToggleBase;

/**
 * A dummy ToggleBase instance used to display messages on the ModernGui screens.
 * <p>
 * DO NOT REGISTER THIS IN THE {@link ToggleBase} CLASS!
 */
public class ToggleDummyMessage extends ToggleBase {

    private String[] message;

    public ToggleDummyMessage(String... message) {
        this.message = message;
    }

    @Override
    public String getName() {
        return "Dummy";
    }

    @Override
    public boolean shouldToggle(String message) {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void setEnabled(boolean enabled) {
    }

    @Override
    public String[] getDescription() {
        return this.message;
    }

    public void appendLine(String line) {
        // Treat null chars as an empty line.
        if (line == null) {
            line = "";
        }
        
        String[] addedArray = new String[this.message.length + 1];
    
        System.arraycopy(this.message, 0, addedArray, 0, this.message.length);
        
        addedArray[this.message.length] = line;
    
        this.message = addedArray;
    }

    public void clearLines() {
        this.message = new String[0];
    }
}
