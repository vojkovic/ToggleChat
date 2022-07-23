

package au.priv.togglechat.toggles.custom.conditions;

import au.priv.togglechat.toggles.custom.ToggleCondition;

/**
 * This code was created by OrangeMarshall and used with permission by boomboompower.
 * <p>
 * Full credit to OrangeMarshall
 *
 * @author OrangeMarshall
 */
public class ConditionEqualsIgnoreCase extends ToggleCondition {

    public ConditionEqualsIgnoreCase(String input) {
        super(input);
    }

    @Override
    public Boolean apply(String input) {
        return input.equalsIgnoreCase(getText());
    }

    @Override
    public ConditionType getConditionType() {
        return ConditionType.EQUALSIGNORECASE;
    }
}
