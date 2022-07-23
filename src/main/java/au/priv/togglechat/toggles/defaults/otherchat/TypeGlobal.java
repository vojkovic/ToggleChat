

package au.priv.togglechat.toggles.defaults.otherchat;

import au.priv.togglechat.ToggleChatMod;
import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeGlobal extends ToggleBase {

    private final Pattern chatPattern = Pattern
            .compile("(?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");

    // Store the found toggle.
    private ToggleBase specialToggle;
    
    @Override
    public String getName() {
        return "Global";
    }

    @Override
    public String getDisplayName() {
        return "Global Chat: %s";
    }

    @Override
    public boolean shouldToggle(String message) {
        // Store the toggle
        if (this.specialToggle == null) {
            this.specialToggle = ToggleChatMod.getInstance().getToggleHandler().getToggle("special");
        }
    
        // A system to prevent accidentally toggling UHC or bedwars chat
        if (this.specialToggle != null && this.specialToggle.isEnabled() && this.specialToggle.shouldToggle(message)) {
            return false;
        }

        Matcher matcher = this.chatPattern.matcher(message);

        return matcher.matches() && isNotOtherChat(matcher);
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Turns all general player",
                "chat on or off",
                "",
                "These are the formats",
                "&7Player: Hi",
                "&a[VIP] Player&r: Hi",
                "&a[VIP&6+&a] Player&r: Hi",
                "&b[MVP] Player&r: Hi",
                "&b[MVP&c+&b] Player&r: Hi",
                "",
                "Useful to prevent spam",
                "or any unwanted chat",
                "messages"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.CHAT;
    }

    private boolean isNotOtherChat(Matcher input) {
        String rank = null;

        try {
            rank = input.group("rank");
        } catch (IllegalStateException | IllegalArgumentException ex) {
            return true;
        }

        // If the matcher returns null then we
        // need to stop before we cause a NPE :)
        if (rank == null) {
            return true;
        }

        switch (rank) {
            case "[TEAM] ":
            case "[SHOUT] ":
            case "[SPECTATOR] ":
                return false;
        }
        return true;
    }
}
