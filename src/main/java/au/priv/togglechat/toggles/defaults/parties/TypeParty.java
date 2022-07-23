

package au.priv.togglechat.toggles.defaults.parties;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeParty extends ToggleBase {

    private final Pattern partyPattern = Pattern
            .compile("(P|Party) > (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");

    @Override
    public String getName() {
        return "Party";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.partyPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all party",
                "chat messages",
                "",
                "Toggle format",
                "&9Party > &7Player&r: Hello",
                "",
                "Fairly useful when",
                "You're in a large party"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.PARTIES;
    }
}
