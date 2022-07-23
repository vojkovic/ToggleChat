

package au.priv.togglechat.toggles.defaults.friends;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeFriendJoin extends ToggleBase {

    private final Pattern joinPattern = Pattern.compile("Friend > (?<player>\\S{1,16})(\\s+)(joined\\.)");
    private final Pattern legacyJoinPattern = Pattern.compile("(?<player>\\S{1,16})(\\s+)(joined\\.)");

    @Override
    public String getName() {
        return "Friend Join";
    }

    @Override
    public boolean shouldToggle(String message) {
        // Keeping legacy in case other servers use the old matcher
        return this.joinPattern.matcher(message).matches() ||
                this.legacyJoinPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all join",
                "notification messages",
                "or anything matching",
                "this format",
                "",
                "&ePlayer joined.",
                "&aFriend > &bPlayer &ejoined.",
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