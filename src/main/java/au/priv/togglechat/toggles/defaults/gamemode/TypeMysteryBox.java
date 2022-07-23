

package au.priv.togglechat.toggles.defaults.gamemode;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeMysteryBox extends ToggleBase {

    private final Pattern mysteryPattern = Pattern
        // ✦ [RANK] Player found a ✰✰✰✰✰ Mystery Box!
        .compile("\u2726 (?<player>.*)found a (?<star>.....) Mystery Box!");
    private final Pattern mysteryFoundPattern = Pattern
        // ✦ [RANK] Player found an Item in a Mystery Box! but can also be a "Holiday Mystery Box".
        .compile("\u2726 (?<player>.*) found (a|an) (?<thing>.*) in a(?<varient>.*| ) Mystery Box!");

    @Override
    public String getName() {
        return "Mystery box";
    }

    @Override
    public String getDisplayName() {
        return "Mystery Box: %s";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.mysteryPattern.matcher(message).matches() || this.mysteryFoundPattern
                .matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Turns finding mystery box",
                "messages on or off",
                "",
                "&b\u2726 &7I &rfound a &e\u2730\u2730 &bMystery Box&r!",
                "&b\u2726 &7I &rfound a &6Dab&r!",
                "",
                "Useful to prevent those",
                "weird box opening messages"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.GAMES;
    }
}
