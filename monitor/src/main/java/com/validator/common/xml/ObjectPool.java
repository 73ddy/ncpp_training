package com.validator.common.xml;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

/**
 * Object Pool is a generic pool of objects. An object pool can be configured to
 * have a default initial size.
 * 
 * 
 * 
 * @author gaurav1935
 * 
 * @param <T>
 */
// TODO: Make the default initial size configurable
public abstract class ObjectPool<T> {

	private static final Logger LOG = Logger.getLogger(ObjectPool.class);

	private static final int DEFAULT_INITIAL_SIZE = 100;

	private boolean initialized;
	private final int initialSize;
	// Queue of the objects in the object pool
	private final ConcurrentLinkedQueue<T> queue = new ConcurrentLinkedQueue<T>();

	/**
	 * Constructor using the default initial pool size
	 */
	public ObjectPool() {
		this(DEFAULT_INITIAL_SIZE);
	}

	/**
	 * Constructor using the given initial pool size
	 * 
	 * @param initialSize
	 */
	public ObjectPool(int initialSize) {
		this.initialSize = initialSize;
		this.initialized = false;
	}

	/**
	 * Initialize the pool if it isn't already initialized. This method isn't
	 * called from the constructor since the subclass implementation of create()
	 * shouldn't be called before the subclass constructor has been invoked.
	 */
	protected void initPool() {
		if (!initialized) {
			long start = System.currentTimeMillis();
			for (int i = 0; i < initialSize; i++) {
				queue.offer(create());
			}
			initialized = true;
			if (LOG.isDebugEnabled()) {
				LOG.debug("OPENBET " + this.getClass().getSimpleName() + ".initPool: "
						+ (System.currentTimeMillis() - start) + " ms");
			}
		}
	}

	/**
	 * Get an object from the pool. If the pool is empty, a new object is
	 * created and returned.
	 * 
	 * @return
	 */
	public T borrow() {
		initPool();
		T object = queue.poll();
		if (object == null) {
			object = create();
		}
		return object;
	}

	/**
	 * Return an object to the pool
	 * 
	 * @param object
	 */
	public void release(T object) {
		queue.offer(object);
	}

	/**
	 * Get the current pool size
	 * 
	 * @return
	 */
	public int size() {
		return queue.size();
	}

	/**
	 * Create a new object for the pool
	 * 
	 * @return
	 */
	protected abstract T create();
}
