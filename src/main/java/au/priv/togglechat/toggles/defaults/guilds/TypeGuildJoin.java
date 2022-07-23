

package au.priv.togglechat.toggles.defaults.guilds;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeGuildJoin extends ToggleBase {

    private final Pattern joinPattern = Pattern.compile("Guild > (?<player>\\S{1,16})(\\s+)(joined\\.)");

    @Override
    public String getName() {
        return "Guild Join";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.joinPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all join",
                "notification messages",
                "or anything matching",
                "this format",
                "",
                "&ePlayer joined.",
                "&2Guild > &bPlayer &ejoined.",
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