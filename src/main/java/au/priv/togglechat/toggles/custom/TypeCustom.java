

package au.priv.togglechat.toggles.custom;

import au.priv.togglechat.toggles.Categories;
import au.priv.togglechat.toggles.ToggleBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A custom Toggle, has all appropriate methods for user customization
 *
 * @author boomboompower
 * @version 1.0
 */
public class TypeCustom extends ToggleBase implements ICustomToggle {
    
    private boolean enabled = true;

    private boolean dirty; // If true, this will be saved on the next save request

    private final String togglename; // The name of the toggle
    
    private final List<ToggleCondition> conditions; // The conditions of the toggle
    private List<String> comments; // The comments displayed on the top of the file
    
    public TypeCustom(String name, ToggleCondition condition) {
        //noinspection ArraysAsListWithZeroOrOneArgument
        this.conditions = Arrays.asList(condition);
        this.togglename = name;
        this.comments = new ArrayList<>();
    }
    
    public TypeCustom(String name, List<ToggleCondition> condition, List<String> comments) {
        this.togglename = name;
        this.conditions = condition;
        this.comments = comments;
    }
    
    @Override
    public String getName() {
        return this.togglename;
    }
    
    @Override
    public boolean shouldToggle(String message) {
        for (ToggleCondition condition : this.conditions) {
            if (condition.apply(message)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String[] getDescription() {
        return new String[] {
                "A custom toggle",
                "",
                "&b" + _getName(),
                "",
                "This feature is still",
                "in &cbeta&r and may be",
                "modified at any time!",
                "",
                "Custom toggles were",
                "created with help from",
                "&6OrangeMarshall"
        };
    }
    
    @Override
    public Categories getCategory() {
        return Categories.CUSTOM;
    }
    
    /**
     * The real name of the toggle, will never be null
     *
     * @return the name of the toggle
     */
    public String _getName() {
        return this.togglename != null ? this.togglename : "Unknown";
    }
    
    /**
     * The conditions of this toggle
     *
     * @return the conditions
     */
    public List<ToggleCondition> _getConditions() {
        return this.conditions;
    }
    
    /**
     * Adds a condition to this toggle
     *
     * @param condition the condition to add
     */
    public void _addCondition(ToggleCondition condition) {
        markDirty();

        this.conditions.add(condition);
    }
    
    /**
     * Returns the comments that will be displayed on top of the file
     *
     * @return the comments on top of the file
     */
    public List<String> _getComments() {
        return this.comments;
    }
    
    /**
     * Sets the comments of the file
     *
     * @param comments the comments of the file
     */
    public TypeCustom _setComments(List<String> comments) {
        markDirty();

        this.comments = comments;
        
        return this;
    }

    /**
     * Indicates that this toggle has been modified and requires saving.
     *
     * When this is true, the save file will be overwritten with the new contents.
     */
    public void markDirty() {
        this.dirty = true;
    }

    /**
     * When this toggle is saved, it will no longer require saving (until it is modified again)
     *
     * We'll disable the save trigger
     */
    public void clean() {
        this.dirty = false;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public boolean isDirty() {
        return this.dirty;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
