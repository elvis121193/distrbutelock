package org.mohe.lock;

import org.mohe.lock.annotation.Lock;

public class LockFactory {
	
	public static AbstractLock newInstance(Lock lock){
		if(Lock.LockType.REMOTE_SHARE_FILE.equals(lock.type())){
			return  new RemoteShareFileLock();
		}
		return  new AbstractLock();
	}

}
