/*
 *     Copyright (C) 2017 boomboompower
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

import me.boomboompower.togglechat.gui.modern.ModernButton;
import me.boomboompower.togglechat.gui.utils.GuiUtils;
import me.boomboompower.togglechat.toggles.ToggleBase;

public class TypeMessages extends ToggleBase {

    private boolean showPrivateMessages = true;

    @Override
    public String getName() {
        return "Messages";
    }

    @Override
    public boolean shouldToggle(String message) {
        return message.startsWith("To ") || message.startsWith("From ");
    }

    @Override
    public boolean isEnabled() {
        return this.showPrivateMessages;
    }

    @Override
    public void setToggled(boolean enabled) {
        this.showPrivateMessages = enabled;
    }

    @Override
    public void onClick(ModernButton button) {
        this.showPrivateMessages = !this.showPrivateMessages;
        button.displayString = String.format(getDisplayName(), isEnabled() ? GuiUtils.ENABLED : GuiUtils.DISABLED);
    }
}