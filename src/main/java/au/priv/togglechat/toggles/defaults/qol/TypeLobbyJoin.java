

package au.priv.togglechat.toggles.defaults.qol;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeLobbyJoin extends ToggleBase {
    
    private final Pattern lobbyJoinPattern = Pattern.compile("( >>> )??\\[MVP(\\+|\\+\\+)] \\S{1,16} (sled into|joined) the lobby!( <<<)??");

        @Override
    public String getName() {
        return "Lobby Join";
    }

    @Override
    public String getDisplayName() {
        return "Lobby join: %s";
    }

    @Override
    public boolean shouldToggle(String message) {
        if (!message.contains("the lobby")) {
            return false;
        }
        
        return this.lobbyJoinPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Removes all &bMVP&c+",
                "and &6MVP&c++&r lobby join",
                "messages",
                "",
                "Such as:",
                "&b[MVP&c+&b] I &6joined the lobby!",
                "",
                "It also removes the &6MVP&c++",
                "join messages to make",
                "lobby chat more readable"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.QOL;
    }
}
