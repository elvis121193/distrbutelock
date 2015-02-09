package org.mohe.service;

import org.mohe.lock.annotation.Lock;
import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloServiceImpl implements HelloService{
	
	@Lock(businessKey="asdf")
	public void sayHello(){
		System.out.println("hello world.");
	}

}
