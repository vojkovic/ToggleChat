

package au.priv.togglechat.toggles.custom.conditions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import au.priv.togglechat.toggles.custom.ToggleCondition;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * This code was created by OrangeMarshall and used with permission by boomboompower.
 * <p>
 * Full credit to OrangeMarshall
 *
 * @author OrangeMarshall
 */
public class ConditionRegex extends ToggleCondition {
    
    private static final Logger logger = LogManager.getLogger("ToggleChat - Regex");
    private static final String REGEX = "https://regex101.com";

    private Pattern pattern;

    public ConditionRegex(String input) {
        super(input);
        try {
            this.pattern = Pattern.compile(input);
        } catch (PatternSyntaxException ex) {
            logger.error("Invalid Regex: \"{}\", try using {} to fix it!", input, ConditionRegex.REGEX, ex);
            this.pattern = null;
        }
    }

    @Override
    public Boolean apply(String input) {
        if (isEmpty(input) || this.pattern == null) {
            return false;
        }

        return this.pattern.matcher(input).matches();
    }

    @Override
    public ConditionType getConditionType() {
        return ConditionType.REGEX;
    }
}
