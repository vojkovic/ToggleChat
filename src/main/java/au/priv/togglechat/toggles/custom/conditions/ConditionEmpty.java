

package au.priv.togglechat.toggles.custom.conditions;

import au.priv.togglechat.toggles.custom.ToggleCondition;

/**
 * This code was created by OrangeMarshall and used with permission by boomboompower.
 * <p>
 * Full credit to OrangeMarshall
 *
 * @author OrangeMarshall
 */
public class ConditionEmpty extends ToggleCondition {

    public ConditionEmpty() {
        super("");
    }

    @Override
    public Boolean apply(String input) {
        return false;
    }

    @Override
    public ConditionType getConditionType() {
        return ConditionType.EMPTY;
    }
}
