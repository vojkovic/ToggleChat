

package au.priv.togglechat.toggles.defaults.gamemode;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeHousing extends ToggleBase {

    private final Pattern worldJoinPattern = Pattern
            .compile("(?<rank>\\[.+] )?(?<player>\\S{1,16}) (?<action>.*) the world\\.");

    @Override
    public String getName() {
        return "Housing Join/Leave";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.worldJoinPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles housing join",
                "and leave messages"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.GAMES;
    }
}
