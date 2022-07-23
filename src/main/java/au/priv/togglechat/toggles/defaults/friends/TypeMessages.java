

package au.priv.togglechat.toggles.defaults.friends;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

public class TypeMessages extends ToggleBase {

    @Override
    public String getName() {
        return "Messages";
    }

    @Override
    public boolean shouldToggle(String message) {
        return message.startsWith("To ") || message.startsWith("From ");
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all incoming",
                "private messages",
                "or any being sent",
                "",
                "These are the formats",
                "&dFrom &7Player&r: Hello",
                "&dTo &7Player&r: Hello"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.FRIENDS;
    }
}
