/*
 *     Copyright (C) 2020 Isophene
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

package me.boomboompower.togglechat.toggles.defaults;

import lombok.Getter;
import lombok.Setter;

import me.boomboompower.togglechat.toggles.ToggleBase;

import java.util.LinkedList;

public class TypeLobbyJoin extends ToggleBase {

    @Setter
    @Getter
    private boolean enabled = true;

    @Override
    public String getName() {
        return "lobby_join";
    }

    @Override
    public String getDisplayName() {
        return "Lobby join: %s";
    }

    @Override
    public boolean shouldToggle(String message) {
        return message.endsWith("joined the lobby!") || (message.contains("joined the lobby") && message.startsWith(" >>>")) || 
            message.endsWith("sled into the lobby!") || (message.contains("sled into the lobby") && message.startsWith(" >>>"));
    }

    @Override
    public LinkedList<String> getDescription() {
        return asLinked(
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
        );
    }
}
