

package au.priv.togglechat.toggles.defaults.otherchat;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeEasy extends ToggleBase {

    private final Pattern chatPattern = Pattern
            .compile("(((Guild|G|Party|P) >)|\\[SHOUT] )?(?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");

    @Override
    public String getName() {
        return "Ez messages";
    }

    @Override
    public boolean shouldToggle(String message) {
        Matcher chatMatcher = this.chatPattern.matcher(message);
        
        return chatMatcher.matches() && containsEasyMessage(chatMatcher.group("message"));
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Removes all \"ez\"",
                "messages across every",
                "channel",
                "",
                "Finally, freedom from",
                "the \"ez\" spam!"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.CHAT;
    }

    private boolean containsEasyMessage(String input) {
        switch (input) {
            case "Hello everyone! I'm an innocent player who loves everything Hypixel.":
            case "Your personality shines brighter than the sun.":
            case "I had something to say, then I forgot it.":
            case "Your Clicks per second are godly. :eek:":
            case "Why can't the Ender Dragon read a book? Because he always starts at the End.":
            case "In my free time I like to watch cat videos on youtube":
            case "Wait... This isn't what I typed!":
            case "I like Minecraft pvp but you are truly better than me!":
            case "I like pineapple on my pizza":
            case "Pineapple doesn't go on pizza!":
            case "Blue is greener than purple for sure":
            case "Let's be friends instead of fighting okay?":
            case "Sometimes I sing soppy love songs in the car.":
            case "I like to eat pasta, do you prefer nachos?":
            case "I love the way your hair glistens in the light":
            case "ILY<3":
            case "You are very good at this game friend.":
            case "When nothing is going right, go left.":
            case "Anybody else really like Rick Astley?":
            case "If the world in Minecraft is infinite....how can the sun revolve around it?":
            case "I sometimes try to say bad things and then this happens :(":
            case "I heard you like minecraft, so I built a computer so you can minecraft, while minecrafting in your minecraft.":
            case "What happens if I add chocolate milk to macaroni and cheese?":
            case "You're a great person! Do you want to play some Hypixel games with me?":
            case "Pls give me doggo memes!":
            case "I enjoy long walks on the beach and playing Hypixel":
            case "I have really enjoyed playing with you! <3":
            case "Please go easy on me, this is my first game!":
            case "Doin a bamboozle fren.":
            case "Behold, the great and powerful, my magnificent and almighty nemisis!":
            case "When I saw the guy with a potion I knew there was trouble brewing.":
            case "Maybe we can have a rematch?":
            case "Can you paint with all the colors of the wind":
            case "I need help, teach me how to play!":
            case "Hey Helper, how play game?":
                return true;
            default:
                return false;

        }
    }
}
