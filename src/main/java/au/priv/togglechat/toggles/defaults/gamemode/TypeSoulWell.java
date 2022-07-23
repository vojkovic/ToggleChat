

package au.priv.togglechat.toggles.defaults.gamemode;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeSoulWell extends ToggleBase {

    private final Pattern soulPattern = Pattern
            .compile("(?<player>\\S{1,16}) has found (?<message>.*) in the Soul Well!");

    @Override
    public String getName() {
        return "Soul";
    }

    @Override
    public String getDisplayName() {
        return "Soul Well: %s";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.soulPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Turns off perk and kit",
                "messages for skywars",
                "",
                "&bSk1er &7has found &6Slime",
                "&6Cage &7in the &bSoul Well&r!",
                "",
                "Good for recording",
                "in a skywars lobby"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.GAMES;
    }
}
