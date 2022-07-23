

package au.priv.togglechat.toggles.sorting;

import au.priv.togglechat.ToggleChatMod;
import au.priv.togglechat.toggles.ToggleBase;
import net.minecraft.client.Minecraft;

import java.util.Comparator;

/**
 * Compare by fontrender length, rather than relying on a linked list
 */
public class ToggleComparator implements Comparator<ToggleBase> {

    private final ToggleChatMod mode = ToggleChatMod.getInstance();
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public int compare(ToggleBase firstIn, ToggleBase secondIn) {
        return compareDefault(firstIn, secondIn);
    }

    public int compareDefault(ToggleBase firstIn, ToggleBase secondIn) {
        Integer first = this.mc.fontRendererObj.getStringWidth(firstIn.getDisplayName());
        Integer second = this.mc.fontRendererObj.getStringWidth(secondIn.getDisplayName());

        return first.compareTo(second);
    }
}
