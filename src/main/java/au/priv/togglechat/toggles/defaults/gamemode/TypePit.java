

package au.priv.togglechat.toggles.defaults.gamemode;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypePit extends ToggleBase {

    private final Pattern pitPattern = Pattern
            .compile("(?<pitrank>\\[((XC|XL|L?X{0,4})(IX|IV|V?I{0,4})-)?\\d{0,3}])( ✬)? (?<rank>\\[.+?] )?(?<player>\\S{1,16}): (?<message>.*)");
    private final Pattern streakPattern =
            Pattern.compile("((BOUNTY|STREAK)! (((bump|of) (\\d+)g on (\\[((XC|XL|L?X{0,4})(IX|IV|V?I{0,3})-)?\\d{0,3}]) ([0-9A-Za-z_]{1,16}) for high streak)|(of \\d+ kil{2}s by (\\[((XC|XL|L?X{0,4})(IX|IV|V?I{0,3})-)?\\d{0,3}]) ([0-9A-Za-z_]{1,16}))))");
    
    @Override
    public String getName() {
        return "The Pit";
    }

    @Override
    public String getDisplayName() {
        return "The Pit: %s";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.pitPattern.matcher(message).matches() || this.streakPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all pit",
                "chat from players and",
                "The bounty and kill",
                "streak messages",
                "",
                "Message format",
                "&7[1] Player&r: Hi",
                "&7[&5&b90&7] &a[VIP] Player&r: Hi",
                "&7[&b&l120&7] &b[MVP] Player&r: Hi"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.GAMES;
    }
}
