

package au.priv.togglechat.toggles.defaults.otherchat;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

/**
 * #BringBackSpecChat
 */
public class TypeSpectator extends ToggleBase {

    private final Pattern spectatorPattern = Pattern
            .compile("\\[SPECTATOR] (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");

    @Override
    public String getName() {
        return "Spectator";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.spectatorPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all spectator",
                "chat messages",
                "",
                "Message format",
                "&7[SPECTATOR] &7Player&r: Hi",
                "&7[SPECTATOR] &a[VIP] Player&r: Hi",
                "&7[SPECTATOR] &b[MVP] Player&r: Hi",
                "",
                "Useful to ignore",
                "post-game chat",
                "messages"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.CHAT;
    }
}
