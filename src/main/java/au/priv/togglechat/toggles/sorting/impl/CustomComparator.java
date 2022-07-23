

package au.priv.togglechat.toggles.sorting.impl;

import au.priv.togglechat.toggles.ToggleBase;
import au.priv.togglechat.toggles.custom.ICustomToggle;
import au.priv.togglechat.toggles.sorting.ToggleComparator;

/**
 * Sort by:
 * -> Custom Toggles
 * -> String width
 * <p>
 * If they are both custom toggles or neither is a custom toggle, display size will be used
 */
public class CustomComparator extends ToggleComparator {

    @Override
    public int compare(ToggleBase firstIn, ToggleBase secondIn) {
        boolean isFirstCustom = firstIn instanceof ICustomToggle;
        boolean isSecondCustom = secondIn instanceof ICustomToggle;

        if (isFirstCustom && !isSecondCustom) {
            return -1;
        } else if (!isFirstCustom && isSecondCustom) {
            return 0;
        } else {
            return compareDefault(firstIn, secondIn);
        }
    }
}
