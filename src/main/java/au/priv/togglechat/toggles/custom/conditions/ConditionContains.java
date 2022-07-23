

package au.priv.togglechat.toggles.custom.conditions;

import au.priv.togglechat.toggles.custom.ToggleCondition;

/**
 * This code was created by OrangeMarshall and used with permission by boomboompower.
 * <p>
 * Full credit to OrangeMarshall
 *
 * @author OrangeMarshall
 */
public class ConditionContains extends ToggleCondition {

    private int matchCount = 1;

    public ConditionContains(String input) {
        super(input);
    }

    public ConditionContains(String input, int matchCount) {
        super(input);

        this.matchCount = Math.max(1, matchCount);
    }

    @Override
    public Boolean apply(String input) {
        return countMatches(input, getText()) == this.matchCount;
    }

    @Override
    public ConditionType getConditionType() {
        return ConditionType.CONTAINS;
    }

    /**
     * Counts the amount of matches in the string
     *
     * @param str the string to test
     * @param sub what to match
     * @return 0 if nothing, or the count
     */
    private int countMatches(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }

        int count = 0;
        int idx = 0;

        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }
}
