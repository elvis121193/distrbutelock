package org.mohe.lock;

/**
 * 分布式锁接口
 * @author mohe
 *
 */
public interface DistributeLock {

	public boolean lock();
	
	public boolean unlock();
}
