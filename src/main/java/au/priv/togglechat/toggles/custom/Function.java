

package au.priv.togglechat.toggles.custom;

/**
 * Custom function, used to give a type based on the input
 *
 * @param <F> the input
 * @param <T> the return type
 */
public interface Function<F, T> {

    T apply(F input);
}
