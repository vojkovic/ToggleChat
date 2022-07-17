/*
 *     Copyright (C) 2021 boomboompower
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

package wtf.boomy.togglechat.toggles.defaults.gamemode;

import wtf.boomy.togglechat.toggles.Categories;
import wtf.boomy.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeMysteryBox extends ToggleBase {

    private final Pattern mysteryPattern = Pattern
        // ✦ [RANK] Player found a ✰✰✰✰✰ Mystery Box!
        .compile("\u2726 (?<player>.*)found a (?<star>.....) Mystery Box!");
    private final Pattern mysteryFoundPattern = Pattern
        // ✦ [RANK] Player found an Item in a Mystery Box! but can also be a "Holiday Mystery Box".
        .compile("\u2726 (?<player>.*) found (a|an) (?<thing>.*) in a(?<varient>.*| ) Mystery Box!");

    @Override
    public String getName() {
        return "Mystery box";
    }

    @Override
    public String getDisplayName() {
        return "Mystery Box: %s";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.mysteryPattern.matcher(message).matches() || this.mysteryFoundPattern
                .matcher(message).matches();
    }

    @Override
    public String[] getDescription() {
        return new String[] {
                "Turns finding mystery box",
                "messages on or off",
                "",
                "&b\u2726 &7I &rfound a &e\u2730\u2730 &bMystery Box&r!",
                "&b\u2726 &7I &rfound a &6Dab&r!",
                "",
                "Useful to prevent those",
                "weird box opening messages"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.GAMES;
    }
}
