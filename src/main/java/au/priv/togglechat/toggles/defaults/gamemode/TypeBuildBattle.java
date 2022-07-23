

package au.priv.togglechat.toggles.defaults.gamemode;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;
import au.priv.togglechat.utils.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeBuildBattle extends ToggleBase {

    private final Pattern battlePattern = Pattern
            .compile("(?<battle>.*\\w) (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");

    @Override
    public String getName() {
        return "Build Battle";
    }

    @Override
    public String getDisplayName() {
        return "Build battle: %s";
    }

    // Rookie [MVP+] boomboompower: tt

    @Override
    public boolean shouldToggle(String message) {
        Matcher matcher = this.battlePattern.matcher(ChatColor.stripColor(message));

        return matcher.matches() && validBattleRank(matcher.group("battle"));
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Turns all build battle",
                "chat on or off",
                "",
                "You can now play build",
                "battle chat free!"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.GAMES;
    }

    private boolean validBattleRank(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        switch (input) {
            case "Rookie":
            case "Untrained":
            case "Amateur":
            case "Apprentice":
            case "Experienced":
            case "Seasoned":
            case "Trained":
            case "Skilled":
            case "Talented":
            case "Professional":
            case "Expert":
            case "Master":
            case "#1 Builder":
                return true;
            default:
                return false;
        }
    }
}
