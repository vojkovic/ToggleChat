

package au.priv.togglechat.toggles.defaults.parties;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypePartyInvites extends ToggleBase {

    private final Pattern expiredPattern = Pattern.compile(
            "The party invite (?<where>\\S{1,4}) (?<rank>\\[.+] )?(?<player>\\S{1,16}) has expired.");
    private final Pattern invitePattern = Pattern.compile(
            "(?<rank>\\[.+] )?(?<player>\\S{1,16}) has invited you to join (?<meme>\\[.+] )?(?<meme2>\\S{1,16}) party!");
    private final Pattern otherInvitePattern = Pattern.compile(
            "(?<inviteerank>\\[.+] )?(?<invitee>\\S{1,16}) invited (?<rank>\\[.+] )?(?<player>\\S{1,16}) to the party! They have 60 seconds to accept.");

    private final Pattern joinPattern = Pattern.compile(Pattern.quote("Click here to join! You have 60 seconds to accept."), Pattern.CASE_INSENSITIVE);
    
    private boolean wasLastMessageToggled;

        @Override
    public String getName() {
        return "Party invites";
    }

    @Override
    public boolean shouldToggle(String message) {
        if (this.wasLastMessageToggled && this.joinPattern.matcher(message).find()) {
            return true;
        }

        return this.wasLastMessageToggled =
                this.expiredPattern.matcher(message).matches() || this.invitePattern.matcher(message)
                        .matches() || this.otherInvitePattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles the ability to see",
                "party invites from",
                "other players.",
                "",
                "This goes well with",
                "separators toggled"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.PARTIES;
    }
}
