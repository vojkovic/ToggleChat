

package au.priv.togglechat.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface SaveableField {

    /**
     * Should this save/load with a different id?
     *
     * @return the id this will be saved with
     */
    String saveId() default "";
}
