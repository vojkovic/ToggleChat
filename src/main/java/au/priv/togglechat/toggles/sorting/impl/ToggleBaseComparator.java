

package au.priv.togglechat.toggles.sorting.impl;

import au.priv.togglechat.ToggleChatMod;
import au.priv.togglechat.toggles.ToggleBase;
import net.minecraft.client.Minecraft;

import java.util.Comparator;
import java.util.Map.Entry;

/**
 * Compare by fontrender length, rather than relying on a linked list
 */
public class ToggleBaseComparator implements Comparator<Entry<String, ToggleBase>> {

    private final ToggleChatMod mode = ToggleChatMod.getInstance();
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public int compare(Entry<String, ToggleBase> firstIn, Entry<String, ToggleBase> secondIn) {
        Integer first = this.mc.fontRendererObj.getStringWidth(firstIn.getValue().getDisplayName());
        Integer second = this.mc.fontRendererObj.getStringWidth(secondIn.getValue().getDisplayName());

        return first.compareTo(second);
    }
}
