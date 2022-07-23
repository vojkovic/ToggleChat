

package au.priv.togglechat.toggles.defaults.otherchat;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

public class TypeColored extends ToggleBase {

    @Override
    public String getName() {
        return "Colored team";
    }

    @Override
    public boolean shouldToggle(String message) {
        return message.startsWith("[BLUE] ") ||
                message.startsWith("[YELLOW] ") ||
                message.startsWith("[GREEN] ") ||
                message.startsWith("[RED] ") ||
                message.startsWith("[WHITE] ") ||
                message.startsWith("[PURPLE] ");
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Disables colored chat",
                "messages in multiple games",
                "",
                "Toggles things like these",
                "&e[YELLOW]",
                "&d[PURPLE]",
                "&a[GREEN]",
                "&c[RED]",
                "",
                "This is good for games",
                "such as paintball"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.CHAT;
    }
}
