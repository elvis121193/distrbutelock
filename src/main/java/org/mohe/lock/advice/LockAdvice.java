package org.mohe.lock.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.mohe.lock.AbstractLock;
import org.mohe.lock.LockFactory;
import org.mohe.lock.annotation.Lock;

public class LockAdvice {

	static Log logger = LogFactory.getLog(LockAdvice.class);

	public Object doAround(ProceedingJoinPoint pjp, Lock lock) throws Throwable {
		if (lock == null) {
			logger.warn("It's not a Lock annotation.");
			return pjp.proceed();
		}
		Object retVal = new Object();
		AbstractLock abstractLock = LockFactory.newInstance(lock);
		
		
		int times = 0;
		long start = System.currentTimeMillis();
		while(!abstractLock.lock()){
			if(times == 0){
				logger.info(" waiting for syn business " + lock.businessKey());
				
				try {
					Thread.currentThread().sleep(10);
				} catch (Exception e) {
					logger.warn("jp wait error:"+e.getMessage(), e.getCause());
				}
				
				times++;//重试的次数
				long end = System.currentTimeMillis();
				
				if ((end-start) >= 5000) {//重试的耗时超过5秒 ，具体实现时可以改为可配置的
					logger.info(" system busy  "+ lock.businessKey());
				}

				
			}
		}
		
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(" have gotted a lock {" + lock.businessKey() + "}");
			}
			abstractLock.lock();
			
			retVal = pjp.proceed();
			
		} catch (Exception e) {
			logger.error(" occur a exception ", e);
			throw new Throwable(e);

		} finally {
			abstractLock.unlock();
			if (logger.isDebugEnabled()) {
				logger.debug(" have released a lock {" + lock.businessKey() + "}");
			}
		}
		return retVal;
	}

}
