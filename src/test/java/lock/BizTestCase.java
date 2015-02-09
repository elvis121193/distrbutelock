package lock;

import org.mohe.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BizTestCase {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext contxt = new ClassPathXmlApplicationContext("classpath:application.xml");
		HelloService helloService = (HelloService)contxt.getBean("helloService");
		helloService.sayHello();
	}

}
