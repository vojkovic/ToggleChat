

package au.priv.togglechat.toggles.defaults.guilds;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeGuildLeave extends ToggleBase {

    private final Pattern leavePattern = Pattern.compile("Guild > (?<player>\\S{1,16})(\\s+)(left\\.)");

    @Override
    public String getName() {
        return "Guild Leave";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.leavePattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all leave",
                "notification messages",
                "or anything matching",
                "this format",
                "",
                "&ePlayer left.",
                "&2Guild > &bPlayer &eleft.",
                "",
                "This is good for",
                "people in a large",
                "guild"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.GUILDS;
    }
}