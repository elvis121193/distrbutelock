package org.mohe.lock.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {
	/** 业务key*/
	String businessKey() default "";
	/** 锁类别*/
	LockType type()  default LockType.REMOTE_SHARE_FILE;

	enum LockType {
		LOCAL_FILE,DATABASE ,REMOTE_SHARE_FILE;
	}
}
