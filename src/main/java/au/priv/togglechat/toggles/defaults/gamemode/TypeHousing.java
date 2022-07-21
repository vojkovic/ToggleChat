/*
 *     Copyright (C) 2022 Brock Vojkovic
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

package au.priv.togglechat.toggles.defaults.gamemode;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeHousing extends ToggleBase {

    private final Pattern worldJoinPattern = Pattern
            .compile("(?<rank>\\[.+] )?(?<player>\\S{1,16}) (?<action>.*) the world\\.");

    @Override
    public String getName() {
        return "Housing Join/Leave";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.worldJoinPattern.matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Toggles housing join",
                "and leave messages"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.GAMES;
    }
}
