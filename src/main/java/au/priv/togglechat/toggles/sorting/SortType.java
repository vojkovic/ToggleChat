

package au.priv.togglechat.toggles.sorting;

import au.priv.togglechat.toggles.ToggleBase;
import au.priv.togglechat.toggles.sorting.impl.AlphabeticalComparator;
import au.priv.togglechat.toggles.sorting.impl.CustomComparator;
import au.priv.togglechat.toggles.sorting.impl.FavouriteSortedComparator;

import org.apache.commons.lang3.text.WordUtils;

import java.util.Comparator;

/**
 * A compilation of different types of {@link ToggleComparator}, used to sort the Toggles in the Toggle menu
 */
public enum SortType {

    WIDTH("Width", "Sorts by the width\nof the name", null),
    ALPHABETICAL("A-Z", "Sorts the toggles\nalphabetically (A-Z)", new AlphabeticalComparator()),
    INVERSE_ALPHABETICAL("Z-A", "Sorts the toggles\nunalphabetically (A-Z)", new AlphabeticalComparator().inverse()),
    CUSTOM("Custom", "Puts custom toggles\nfirst then sorts\nby width", new CustomComparator()),
    FAVOURITES("Favourite", "Puts favourite toggles\nfirst then sorts\nby width", new FavouriteSortedComparator());

    private final String displayName;
    private final String description;
    private final Comparator<ToggleBase> sorter;

    SortType() {
        this(null, null);
    }

    SortType(Comparator<ToggleBase> sorter) {
        this(null, sorter);
    }

    SortType(String displayName, Comparator<ToggleBase> sorter) {
        this(displayName, null, sorter);
    }

    SortType(String displayName, String description, Comparator<ToggleBase> sorter) {
        this.displayName = displayName == null ? WordUtils.capitalize(name().toLowerCase()) : displayName;
        this.description = description;
        this.sorter = sorter == null ? new ToggleComparator() : sorter;
    }

    public Comparator<ToggleBase> getSorter() {
        return this.sorter;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getDescription() {
        return this.description;
    }

    public SortType getNextSortType() {
        int nextSort = ordinal() + 1;
        
        if (nextSort >= values().length) {
            nextSort = 0;
        }
        
        return values()[nextSort];
    }
}
