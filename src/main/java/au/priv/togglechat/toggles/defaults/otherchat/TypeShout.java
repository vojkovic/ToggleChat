

package au.priv.togglechat.toggles.defaults.otherchat;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeShout extends ToggleBase {

    private final Pattern shoutPattern = Pattern
            .compile("\\[SHOUT] (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");

    @Override
    public String getName() {
        return "Shout";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.shoutPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all shout",
                "chat messages",
                "",
                "Message format",
                "&6[SHOUT] &7Player&r: Hello",
                "&6[SHOUT] &a[VIP] Player&r: Hello",
                "&6[SHOUT] &b[MVP] Player&r: Hello",
                "",
                "Good for large minigames",
                "such as Mega Skywars"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.CHAT;
    }
}
