

package au.priv.togglechat.toggles.defaults.qol;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

public class TypeAutoQuest extends ToggleBase {

    @Override
    public String getName() {
        return "Auto Quest";
    }

    @Override
    public String getDisplayName() {
        return "Auto Quest: %s";
    }

    @Override
    public boolean shouldToggle(String message) {
        return message.startsWith("Automatically activated: ");
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Removes messages that",
                "notify you about quests",
                "automatically started",
                "(feature only works with",
                "&b[MVP&c+&b] &frank)",
                "",
                "Such as:",
                "&aAutomatically activated:",
                "&6Weekly Quest: Break 25 beds"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.QOL;
    }
}
