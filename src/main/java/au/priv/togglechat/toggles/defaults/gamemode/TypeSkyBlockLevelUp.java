

package au.priv.togglechat.toggles.defaults.gamemode;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

public class TypeSkyBlockLevelUp extends ToggleBase {

    @Override
    public String getName() {
        return "Skyblock Levelup";
    }
    
    @Override
    public String getDisplayName() {
        return "Skyblock LevelUp: %s";
    }
    
    @Override
    public boolean shouldToggle(String message) {
        return (message.startsWith("COLLECTION") || message.startsWith("SKILL")) && message.contains("LEVEL UP");
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all SkyBlock",
                "levelup messages",
                "",
                "Toggle format",
                "&b&lSKILL LEVEL UP &dFarming &8II&7➜&dIII",
                "&6&lCOLLECTION LEVEL UP &eFarming &8III&e➜&dIV"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.GAMES;
    }
}
