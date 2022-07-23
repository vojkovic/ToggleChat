

package au.priv.togglechat.utils.options;

/**
 * Simple callback class
 *
 * @param <T> the type of data to receive
 *
 * @author boomboompower
 * @since 3.0.0
 */
public interface SimpleCallback<T> {
    
    /**
     * Runs the callback and provides the associated data
     *
     * @param data the data to receive once the callback is run
     */
    public abstract void run(T data);
    
    /**
     * Called when this callback is cancelled
     */
    public default void onCancel() {
    }
    
    /**
     * Called when an error occurs while running this callback
     *
     * @param message the message from the error
     */
    public default void onError(String message) {
    }
    
}
