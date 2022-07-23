

package au.priv.togglechat.toggles.defaults.otherchat;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeTeam extends ToggleBase {

    private final Pattern teamPattern = Pattern
            .compile("\\[TEAM] (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");

        @Override
    public String getName() {
        return "Team";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.teamPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all",
                "incoming team messages",
                "",
                "Message format:",
                "&9[TEAM] &7Player&r: Hi",
                "&9[TEAM] &a[VIP] Player&r: Hi",
                "&9[TEAM] &b[MVP] Player&r: Hi",
                "",
                "Useful for large",
                "team games"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.CHAT;
    }
}