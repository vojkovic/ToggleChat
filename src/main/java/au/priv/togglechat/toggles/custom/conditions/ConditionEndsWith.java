

package au.priv.togglechat.toggles.custom.conditions;

import au.priv.togglechat.toggles.custom.ToggleCondition;

/**
 * This code was created by OrangeMarshall and used with permission by boomboompower.
 * <p>
 * Full credit to OrangeMarshall
 *
 * @author OrangeMarshall
 */
public class ConditionEndsWith extends ToggleCondition {

    public ConditionEndsWith(String input) {
        super(input);
    }

    @Override
    public Boolean apply(String input) {
        return input.endsWith(getText());
    }

    @Override
    public ConditionType getConditionType() {
        return ConditionType.ENDSWITH;
    }
}
