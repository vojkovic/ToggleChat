

package au.priv.togglechat.toggles.sorting.impl;

import au.priv.togglechat.toggles.ToggleBase;
import au.priv.togglechat.toggles.sorting.ToggleComparator;

/**
 * Sort by:
 * -> Favourites
 * -> String width (Default)
 * <p>
 * If they are both favourites or neither is a favourite, display size will be used
 */
public class FavouriteSortedComparator extends ToggleComparator {

    @Override
    public int compare(ToggleBase firstIn, ToggleBase secondIn) {
        if (firstIn.isFavourite() && !secondIn.isFavourite()) {
            return -1;
        } else if (!firstIn.isFavourite() && secondIn.isFavourite()) {
            return 0;
        } else {
            return compareDefault(firstIn, secondIn);
        }
    }
}
