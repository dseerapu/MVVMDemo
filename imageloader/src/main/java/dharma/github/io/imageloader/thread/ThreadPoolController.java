package dharma.github.io.imageloader.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread pool controller
 */
public class ThreadPoolController {

	/**
	 * Thread Pool
	 */
	private static final ExecutorService threadPool = Executors.newFixedThreadPool(5);

	/**
	 * Get thread pool
	 * @return threadPool
	 */
	public static Executor getThreadPool() {
		return threadPool;
	}

}