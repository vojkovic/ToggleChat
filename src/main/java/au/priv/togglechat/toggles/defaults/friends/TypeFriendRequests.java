

package au.priv.togglechat.toggles.defaults.friends;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeFriendRequests extends ToggleBase {

    private final Pattern friendPattern = Pattern.compile(
            "----------------------------------------------------\n" +
                    "Friend request from (?<rank>\\[.+] )?(?<player>\\S{1,16})\n" +
                    "\\[ACCEPT] - \\[DENY] - \\[IGNORE]\n" +
                    "----------------------------------------------------");

    // This is used for expiry messages
    private final Pattern oldPattern = Pattern
            .compile(Pattern.quote("Friend request from "), Pattern.CASE_INSENSITIVE);

    // Removal message
    private final Pattern friendRemovedMePattern = Pattern
            .compile("(?<rank>\\[.+?] )?(?<player>\\S{1,16}) removed you from their friends list!");

    // Added message
    private final Pattern areNowFriendsPattern = Pattern
            .compile("You are now friends with (?<rank>\\[.+?] )?(?<player>\\S{1,16})");

    @Override
    public String getName() {
        return "Friend requests";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.oldPattern.matcher(message).find() || this.friendPattern.matcher(message)
                .matches() || this.friendRemovedMePattern
                .matcher(message).matches() || this.areNowFriendsPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles the ability to see",
                "new friend requests from",
                "other players.",
                "",
                "It can be useful if you",
                "wish to keep friend",
                "requests open, but don't",
                "want to see notifications"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.FRIENDS;
    }
}
