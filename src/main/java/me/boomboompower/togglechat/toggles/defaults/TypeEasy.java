package me.boomboompower.togglechat.toggles.defaults;

import me.boomboompower.togglechat.gui.modern.ModernButton;
import me.boomboompower.togglechat.gui.modern.ModernGui;
import me.boomboompower.togglechat.toggles.ToggleBase;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeEasy extends ToggleBase {

    private Pattern chatPattern = Pattern.compile("(?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");
    private Pattern guildPattern = Pattern.compile("Guild > (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");
    private Pattern shortGuildPattern = Pattern.compile("G > (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");
    private Pattern partyPattern = Pattern.compile("Party > (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");
    private Pattern shortPartyPattern = Pattern.compile("P > (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");
    private Pattern shoutPattern = Pattern.compile("\\[SHOUT] (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");

    private boolean showEasy = true;

    @Override
    public String getName() {
        return "Ez messages";
    }

    @Override
    public boolean shouldToggle(String message) {
        Matcher chatMatcher = this.chatPattern.matcher(message);
        if (chatMatcher.matches() && containsEasyMessage(chatMatcher.group("message"))) {
            return true;
        }

        Matcher guildMatcher = this.guildPattern.matcher(message);
        if (guildMatcher.matches() && containsEasyMessage(guildMatcher.group("message"))) {
            return true;
        }

        Matcher partyMatcher = this.partyPattern.matcher(message);
        if (partyMatcher.matches() && containsEasyMessage(partyMatcher.group("message"))) {
            return true;
        }

        Matcher shoutMatcher = this.shoutPattern.matcher(message);
        if (shoutMatcher.matches() && containsEasyMessage(shoutMatcher.group("message"))) {
            return true;
        }

        Matcher shortGuildMatcher = this.shortGuildPattern.matcher(message);
        if (shortGuildMatcher.matches() && containsEasyMessage(shortGuildMatcher.group("message"))) {
            return true;
        }

        Matcher shortPartyMatcher = this.shortPartyPattern.matcher(message);
        return shortPartyMatcher.matches() && containsEasyMessage(shortPartyMatcher.group("message"));

    }

    @Override
    public boolean isEnabled() {
        return this.showEasy;
    }

    @Override
    public void setToggled(boolean enabled) {
        this.showEasy = enabled;
    }

    @Override
    public void onClick(ModernButton button) {
        this.showEasy = !this.showEasy;
        button.setText(String.format(getDisplayName(), ModernGui.getStatus(isEnabled())));
    }

    @Override
    public LinkedList<String> getDescription() {
        return asLinked(
                "Removes all \"ez\"",
                "messages across every",
                "channel",
                "",
                "Finally, freedom from",
                "the \"ez\" spam!"
        );
    }

    private boolean containsEasyMessage(String input) {
        switch (input) {
            case "Hello everyone! I'm an innocent who loves everything Hypixel.":
            case "Your personality shines brighter than the sun.":
            case "I had something to say, then I forgot it.":
            case "Your Clicks per second are godly. :eek:":
            case "Why can't Ender Dragon read a book? Because he always starts at the End.":
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
