

package au.priv.togglechat.toggles.defaults.friends;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeFriendLeave extends ToggleBase {

    private final Pattern leavePattern = Pattern.compile("Friend > (?<player>\\S{1,16})(\\s+)(left\\.)");
    private final Pattern legacyLeavePattern = Pattern.compile("(?<player>\\S{1,16})(\\s+)(left\\.)");

    @Override
    public String getName() {
        return "Friend Leave";
    }

    @Override
    public boolean shouldToggle(String message) {
        // keeping legacy in case other servers use the old matcher
        return this.leavePattern.matcher(message).matches() ||
                this.legacyLeavePattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all leave",
                "notification messages",
                "or anything matching",
                "this format",
                "",
                "&ePlayer left.",
                "&aFriend > &bPlayer &eleft.",
                "",
                "This is good for",
                "people with a large",
                "friends list"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.FRIENDS;
    }
}