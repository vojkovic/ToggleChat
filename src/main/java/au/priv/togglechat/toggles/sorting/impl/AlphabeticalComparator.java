

package au.priv.togglechat.toggles.sorting.impl;

import au.priv.togglechat.toggles.ToggleBase;
import au.priv.togglechat.toggles.sorting.ToggleComparator;

/**
 * Sort by:
 * -> Alphabetical
 * <p>
 * If the words are the same alphabetically, then 0 will be returned as it means they are the same word
 */
public class AlphabeticalComparator extends ToggleComparator {

    private boolean inverse = false;

    @Override
    public int compare(ToggleBase firstIn, ToggleBase secondIn) {
        int trackedCompare = firstIn.getName().compareToIgnoreCase(secondIn.getName());

        if (this.inverse && trackedCompare != 0) {
            return -trackedCompare;
        }

        return trackedCompare;
    }

    /**
     * Inverse this Comparator
     *
     * @return a comparator which is inversed.
     */
    public AlphabeticalComparator inverse() {
        this.inverse = !this.inverse;

        return this;
    }

}
