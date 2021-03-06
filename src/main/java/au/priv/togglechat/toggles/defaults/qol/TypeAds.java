

package au.priv.togglechat.toggles.defaults.qol;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeAds extends ToggleBase {

    private final Pattern networkBoosterPattern = Pattern.compile(
            "\nBuying a (?<game>.*) Network Booster activates (?<coinboost>.*) for (?<count>.*) players & supports the server!\nClick to browse Network Boosters! (?<thing>.) (?<site>.*)\n");
    private final Pattern mysteryPattern = Pattern.compile(
            "\nMystery Boxes contain tons of awesome collectibles! Unlock Housing items, find legendary Pets and more!\nClick to browse Mystery Boxes! (?<symbol>.) (?<site>.*)\n");
    private final Pattern mediaPattern1 = Pattern.compile(
            "\nSee all the posts shared by Hypixel on (?<name>.*)!\nLike the Hypixel page! (?<special>.) (?<link>.*)\n");
    private final Pattern mediaPattern2 = Pattern.compile(
            "\nKeep up with the latest from Hypixel on (?<name>.*)!\nFollow @HypixelNetwork! (?<special>.) (?<link>.+)\n");
    private final Pattern mediaPattern3 = Pattern.compile(
            "\nBe the first to watch Hypixel (?<media>.+) videos!\nSubscribe to Hypixel! (?<special>.) (?<link>.+)\n");
    private final Pattern mediaPattern4 = Pattern.compile(
            "\nGet deals and news sent to your email!\nSignup for the Newsletter! (?<special>.) (?<link>.+)\n");

    @Override
    public String getName() {
        return "Ads";
    }

    @Override
    public String getDisplayName() {
        return "Ads: %s";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.networkBoosterPattern.matcher(message).find() ||
                this.mysteryPattern.matcher(message).find() ||
                this.mediaPattern1.matcher(message).matches() ||
                this.mediaPattern2.matcher(message).matches() ||
                this.mediaPattern3.matcher(message).matches() ||
                this.mediaPattern4.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles all server chat",
                "advertisements such as",
                "things prompting the",
                "store page",
                "",
                "This cleans up the chat",
                "whilst you are afk",
                "so you don't miss",
                "important messages"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.QOL;
    }
}
