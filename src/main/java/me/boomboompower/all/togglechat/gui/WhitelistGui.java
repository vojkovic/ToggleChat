/*
 *     Copyright (C) 2016 boomboompower
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.boomboompower.all.togglechat.gui;

import me.boomboompower.all.togglechat.ToggleChat;
import me.boomboompower.all.togglechat.gui.utils.GuiUtils;
import me.boomboompower.all.togglechat.utils.Writer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.io.IOException;

//        - 94
//        - 70
//        - 46
//        - 22
//        + 2
//        + 26
//        + 50
//        + 74

public class WhitelistGui {

    public static class WhitelistMain extends GuiScreen {

        private GuiTextField text;
        private String input = "";

        public WhitelistMain() {
            this("");
        }

        public WhitelistMain(String input) {
            this.input = input;
        }

        @Override
        public void initGui() {
            text = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 75, this.height / 2 - 58, 150, 20);
            this.buttonList.add(new GuiButton(1, this.width / 2 - 75, this.height / 2 - 22, 150, 20, "Add"));
            this.buttonList.add(new GuiButton(2, this.width / 2 - 75, this.height / 2 + 2, 150, 20, "Remove"));
            this.buttonList.add(new GuiButton(3, this.width / 2 - 75, this.height / 2 + 26, 150, 20, "Clear"));
            this.buttonList.add(new GuiButton(4, this.width / 2 - 75, this.height / 2 + 50, 150, 20, "List"));
//            this.buttonList.add(new GuiButton(5, this.width / 2 - 75, this.height / 2 + 74, 150, 20, "Settings"));

            if (ToggleChat.tutorialEnabled) this.buttonList.add(new GuiButton(9, this.width / 2 - 70, this.height - 25, 140, 20, "Tutorial"));
            this.buttonList.add(new GuiButton(10, 5, this.height - 25, 75, 20, "Back"));

            text.setText(input);
            text.setMaxStringLength(16);
            text.setFocused(true);
        }

        public void display() {
            FMLCommonHandler.instance().bus().register(this);
        }

        @SubscribeEvent
        public void onClientTick(TickEvent.ClientTickEvent event) {
            FMLCommonHandler.instance().bus().unregister(this);
            Minecraft.getMinecraft().displayGuiScreen(this);
        }

        @Override
        public void drawScreen(int x, int y, float ticks) {
            drawDefaultBackground();
            text.drawTextBox();
            drawCenteredString(this.fontRendererObj, "Whitelist", this.width / 2, this.height / 2 - 82, Color.WHITE.getRGB());
            super.drawScreen(x, y, ticks);
        }

        @Override
        protected void keyTyped(char c, int key) throws IOException {
            if (c != ' ') {
                super.keyTyped(c, key);
                text.textboxKeyTyped(c, key);
            }
        }

        @Override
        protected void mouseClicked(int x, int y, int btn) throws IOException {
            super.mouseClicked(x, y, btn);
            text.mouseClicked(x, y, btn);
        }

        @Override
        public void updateScreen() {
            super.updateScreen();
            text.updateCursorCounter();
        }

        @Override
        protected void actionPerformed(GuiButton button) {
            switch (button.id) {
                case 0:
                    // Dont need to do anything
                    break;
                case 1:
                    if (text.getText().isEmpty()) {
                        sendChatMessage("No name given!");
                    } else if (!whitelistContains(text.getText())) {
                        ToggleChat.whitelist.add(text.getText());
                        sendChatMessage("Added " + goldify(text.getText()) + " to the whitelist!");
                    } else {
                        sendChatMessage("The whitelist already contained " + goldify(text.getText()) + "!");
                    }
                    Minecraft.getMinecraft().displayGuiScreen(null);
                    break;
                case 2:
                    if (text.getText().isEmpty()) {
                        sendChatMessage("No name given!");
                    } else if (!whitelistContains(text.getText())) {
                        sendChatMessage("The whitelist does not contain " + goldify(text.getText()) + "!");
                    } else {
                        removeFromWhitelist(text.getText());
                        sendChatMessage("Removed " + goldify(text.getText()) + " from the whitelist!");
                    }
                    Minecraft.getMinecraft().displayGuiScreen(null);
                    break;
                case 3:
                    if (!ToggleChat.whitelist.isEmpty()) {
                        new WhitelistClearConfirmation(this).display();
                    } else {
                        sendChatMessage("The whitelist is already empty!");
                        Minecraft.getMinecraft().displayGuiScreen(null);
                    }

                    break;
                case 4:
                    displayWhitelist();
                    Minecraft.getMinecraft().displayGuiScreen(null);
                    break;
//                case 5:
//                    new WhitelistSettings(this, 0).display();
//                    break;
                case 10:
                    new ToggleGui.ToggleChatMainGui(0).display();
                    break;
            }

            if (ToggleChat.tutorialEnabled) {
                try {
                    switch (button.id) {
                        case 9:
                            new me.boomboompower.all.togglechat.gui.tutorial.TutorialGui.WhitelistTutorial(this, 0).display();
                            break;
                    }
                } catch (Exception ex) {}
            }
        }



        @Override
        public void onGuiClosed() {
            Writer.execute(true);
        }

        @Override
        public boolean doesGuiPauseGame() {
            return false;
        }

        @Override
        public void sendChatMessage(String message) {
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "T" + EnumChatFormatting.BLUE + "C" + EnumChatFormatting.DARK_GRAY + " > " + EnumChatFormatting.GRAY + message));
        }

        private String goldify(String name) {
            return EnumChatFormatting.GOLD + name + EnumChatFormatting.GRAY;
        }

        private boolean whitelistContains(String message) {
            boolean contains = false;

            for (String s: ToggleChat.whitelist) {
                if (s.equalsIgnoreCase(message)) {
                    contains = true;
                    break;
                }
            }

            return contains;
        }

        private void removeFromWhitelist(String username) {
            for (String s: ToggleChat.whitelist) {
                if (s.equalsIgnoreCase(username)) {
                    ToggleChat.whitelist.remove(s);
                    break;
                }
            }
        }

        private void displayWhitelist() {
            if (ToggleChat.whitelist.size() > 0) {
                sendChatMessage("Displaying " + goldify(String.valueOf(ToggleChat.whitelist.size())) + (ToggleChat.whitelist.size() == 1 ? " entry" : " entries") + " from the whitelist!");
                for (String word: ToggleChat.whitelist) {
                    sendChatMessage(EnumChatFormatting.GOLD + "\u25CF " + word);
                }
            } else {
                sendChatMessage(EnumChatFormatting.RED + "There is nothing on your whitelist!");
            }
        }
    }

//    public static class WhitelistSettings extends GuiScreen {
//
//        private GuiScreen previousScreen;
//        private int pageNumber;
//
//        public WhitelistSettings(GuiScreen previous, int pageNumber) {
//            this.previousScreen = previous;
//            this.pageNumber = pageNumber;
//        }
//
//        @Override
//        public void initGui() {
//            createButtons();
//        }
//
//        private void createButtons() {
//            this.buttonList.clear();
//            if (pageNumber == 0) {
//                this.buttonList.add(new GuiButton(0, this.width / 2 - 75, this.height / 2 - 70, 150, 20, "Team: " + (Options.ignoreTeam ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(1, this.width / 2 - 75, this.height / 2 - 46, 150, 20, "Join: " + (Options.ignoreJoin ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(2, this.width / 2 - 75, this.height / 2 - 22, 150, 20, "Leave: " + (Options.ignoreLeave ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(3, this.width / 2 - 75, this.height / 2 + 2, 150, 20, "Guild: " + (Options.ignoreGuild ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(4, this.width / 2 - 75, this.height / 2 + 26, 150, 20, "Party: " + (Options.ignoreParty ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(5, this.width / 2 - 75, this.height / 2 + 50, 150, 20, "Shout: " + (Options.ignoreShout ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(6, this.width / 2 - 75, this.height / 2 + 74, 150, 20, "Message: " + (Options.ignoreTeam ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//            } else {
//                this.buttonList.add(new GuiButton(0, this.width / 2 - 75, this.height / 2 - 70, 150, 20, "UHC: " + (Options.ignoreJoin ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(1, this.width / 2 - 75, this.height / 2 - 46, 150, 20, "Party invites: " + (Options.ignoreLeave ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(2, this.width / 2 - 75, this.height / 2 - 22, 150, 20, "Friend requests: " + (Options.ignoreGuild ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(3, this.width / 2 - 75, this.height / 2 + 2, 150, 20, "Spectator: " + (Options.ignoreParty ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(4, this.width / 2 - 75, this.height / 2 + 26, 150, 20, "Colored team: " + (Options.ignoreShout ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(5, this.width / 2 - 75, this.height / 2 + 50, 150, 20, "Housing: " + (Options.ignoreHousing ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//                this.buttonList.add(new GuiButton(6, this.width / 2 - 75, this.height / 2 + 74, 150, 20, "Separators: " + (Options.ignoreSeparators ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled")));
//            }
//
//            this.buttonList.add(new GuiButton(7, 5, this.height - 25, 75, 20, "Whitelist"));
//            this.buttonList.add(new GuiButton(8, this.width - 135, this.height - 25, 65, 20, "Back"));
//            this.buttonList.add(new GuiButton(9, this.width - 70, this.height - 25, 65, 20, "Next"));
//        }
//
//        public void display() {
//            FMLCommonHandler.instance().bus().register(this);
//        }
//
//        @SubscribeEvent
//        public void onClientTick(TickEvent.ClientTickEvent event) {
//            FMLCommonHandler.instance().bus().unregister(this);
//            Minecraft.getMinecraft().displayGuiScreen(this);
//        }
//
//        @Override
//        public void drawScreen(int x, int y, float ticks) {
//            drawDefaultBackground();
//            drawCenteredString(this.fontRendererObj, "Whitelist Settings", this.width / 2, this.height / 2 - 100, Color.WHITE.getRGB());
//            drawCenteredString(this.fontRendererObj, "This feature is experimental and may be removed at any time!", this.width / 2, this.height / 2 - 90, Color.WHITE.getRGB());
//
//            buttonList.get(8).enabled = pageNumber == 1; // Back
//            buttonList.get(9).enabled = pageNumber == 0; // Next
//
//            super.drawScreen(x, y, ticks);
//        }
//
//        @Override
//        protected void keyTyped(char c, int key) throws IOException {
//            if (key == 1) {
//                mc.displayGuiScreen(previousScreen);
//            }
//        }
//
//        @Override
//        public void updateScreen() {
//            super.updateScreen();
//        }
//
//        @Override
//        protected void actionPerformed(GuiButton button) {
//            if (pageNumber == 0) {
//                switch (button.id) {
//                    case 0:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_TEAM);
//                        button.displayString = "Team: " + (Options.ignoreTeam ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 1:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_JOIN);
//                        button.displayString = "Join: " + (Options.ignoreJoin ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 2:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_LEAVE);
//                        button.displayString = "Leave: " + (Options.ignoreLeave ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 3:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_GUILD);
//                        button.displayString = "Guild: " + (Options.ignoreGuild ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 4:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_PARTY);
//                        button.displayString = "Party: " + (Options.ignoreParty ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 5:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_SHOUT);
//                        button.displayString = "Shout: " + (Options.ignoreShout ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 6:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_MESSAGE);
//                        button.displayString = "Message: " + (Options.ignoreMessage ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                }
//            } else {
//                switch (button.id) {
//                    case 0:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_UHC);
//                        button.displayString = "UHC: " + (Options.ignoreUHC ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 1:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_PARTYINV);
//                        button.displayString = "Party invites: " + (Options.ignorePartyInv ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 2:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_FRIENDREQ);
//                        button.displayString = "Friend requests: " + (Options.ignoreFriendReqs ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 3:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_SPECTATOR);
//                        button.displayString = "Spectator: " + (Options.ignoreSpec ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 4:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_COLORED_TEAM);
//                        button.displayString = "Colored team: " + (Options.ignoreColored ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 5:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_HOUSING);
//                        button.displayString = "Housing: " + (Options.ignoreHousing ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                    case 6:
//                        Options.getInstance().toggle(Options.ToggleType.WHITELIST_SEPARATOR);
//                        button.displayString = "Separators: " + (Options.ignoreSeparators ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled");
//                        break;
//                }
//            }
//            switch (button.id) {
//                case 7:
//                    new WhitelistMain().display();
//                    break;
//                case 8:
//                    if (pageNumber > 0) {
//                        new ToggleGui.ToggleChatMainGui(pageNumber--);
//                        createButtons();
//                    } else {
//                        new WhitelistMain().display();
//                    }
//                    break;
//                case 9:
//                    new ToggleGui.ToggleChatMainGui(pageNumber++);
//                    createButtons();
//                    break;
//            }
//        }
//
//        @Override
//        public void onGuiClosed() {
//            Writer.execute(false, true);
//        }
//
//        @Override
//        public boolean doesGuiPauseGame() {
//            return false;
//        }
//
//        @Override
//        public void sendChatMessage(String message) {
//            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "T" + EnumChatFormatting.BLUE + "C" + EnumChatFormatting.DARK_GRAY + " > " + EnumChatFormatting.GRAY + message));
//        }
//    }

    public static class WhitelistClearConfirmation extends GuiScreen {

        private GuiScreen previousScreen;

        public WhitelistClearConfirmation(GuiScreen previous) {
            this.previousScreen = previous;
        }

        @Override
        public void initGui() {
            this.buttonList.add(new GuiButton(0, this.width / 2 - 200, this.height / 2 + 30, 150, 20, "Cancel"));
            this.buttonList.add(new GuiButton(1, this.width / 2 + 50, this.height / 2 + 30, 150, 20, "Confirm"));
        }

        public void display() {
            FMLCommonHandler.instance().bus().register(this);
        }

        @SubscribeEvent
        public void onClientTick(TickEvent.ClientTickEvent event) {
            FMLCommonHandler.instance().bus().unregister(this);
            Minecraft.getMinecraft().displayGuiScreen(this);
        }

        @Override
        public void drawScreen(int x, int y, float ticks) {
            drawDefaultBackground();

            GuiUtils.writeInformation(this.width / 2, this.height / 2 - 60, 15,
                    String.format("Are you sure you wish to clear &6%s %s&r from your whitelist?", ToggleChat.whitelist.size(), (ToggleChat.whitelist.size() == 1 ? "entry" : "entries")),
                    "This action cannot be undone, use at your own risk!"
            );

            super.drawScreen(x, y, ticks);
        }

        @Override
        protected void keyTyped(char c, int key) throws IOException {
            if (key == 1) {
                mc.displayGuiScreen(previousScreen);
            }
        }

        @Override
        public void updateScreen() {
            super.updateScreen();
        }

        @Override
        protected void actionPerformed(GuiButton button) {
            switch (button.id) {
                case 0:
                    new WhitelistMain().display();
                    break;
                case 1:
                    ToggleChat.whitelist.clear();
                    sendChatMessage("Cleared the whitelist!");
                    mc.displayGuiScreen(null);
                    break;
            }
        }

        @Override
        public void onGuiClosed() {
            Writer.execute(false, true);
        }

        @Override
        public boolean doesGuiPauseGame() {
            return false;
        }

        @Override
        public void sendChatMessage(String message) {
            Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "T" + EnumChatFormatting.BLUE + "C" + EnumChatFormatting.DARK_GRAY + " > " + EnumChatFormatting.GRAY + message));
        }
    }
}