

package au.priv.togglechat.toggles.defaults.guilds;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeGuild extends ToggleBase {

    private final Pattern guildPattern = Pattern
            .compile("(Guild|G) > (?<rank>\\[.+] )?(?<player>\\S{1,16})( \\[.+])?: (?<message>.*)");

        @Override
    public String getName() {
        return "Guild";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.guildPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all guild",
                "chat messages",
                "",
                "&2Guild > &7Player&r: Hi",
                "",
                "This is a feature",
                "which should be",
                "offered, but isn't",
                "",
                "This toggle works",
                "regardless of the",
                "rank a player has"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.GUILDS;
    }
}
