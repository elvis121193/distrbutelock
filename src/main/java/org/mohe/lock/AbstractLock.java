package org.mohe.lock;

public class AbstractLock implements DistributeLock {

	@Override
	public boolean lock() {
		System.out.println("lock");
		return false;
	}

	@Override
	public boolean unlock() {
		System.out.println("unlock");
		return false;
	}

}
