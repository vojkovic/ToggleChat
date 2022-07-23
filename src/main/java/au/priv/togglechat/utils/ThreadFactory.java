

package au.priv.togglechat.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple class to handle running code async to the main thread.
 *
 * @author boomboompower
 * @version 1.1
 * @since 1.0.0
 */
public class ThreadFactory {
    
    // The current ThreadCount
    private final AtomicInteger threadNumber = new AtomicInteger(0);
    
    // Async task scheduler
    private final ExecutorService POOL;
    
    // Stores if this factory is dead
    private boolean shutdown = false;
    
    /**
     * Creates a new Thread Factory
     *
     * @param factoryName the name of this factory.
     */
    public ThreadFactory(String factoryName) {
        POOL = Executors.newFixedThreadPool(8, r -> new Thread(r, String.format("%s Thread %s", factoryName, this.threadNumber.incrementAndGet())));
    }
    
    /**
     * Runs a task async to the main thread
     *
     * @param runnable the runnable to run
     */
    public void runAsync(Runnable runnable) {
        if (this.shutdown) {
            throw new IllegalStateException("ThreadFactory has been destroyed.");
        }
        
        this.POOL.execute(runnable);
    }
    
    /**
     * Terminates this ThreadFactory and kills the ExecutorService
     */
    public void destroy() {
        if (this.shutdown) {
            return;
        }
        
        this.shutdown = true;
        
        this.POOL.shutdown();
    }
    
    /**
     * Returns true if this factory has been shutdown
     *
     * @return true if this ThreadFactory is dead.
     */
    public boolean isShutdown() {
        return this.shutdown;
    }
}
