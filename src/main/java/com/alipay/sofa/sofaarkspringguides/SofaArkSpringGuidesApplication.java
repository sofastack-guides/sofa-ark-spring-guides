package com.alipay.sofa.sofaarkspringguides;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource({ "classpath*:META-INF/spring/service.xml" })
@SpringBootApplication
public class SofaArkSpringGuidesApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SofaArkSpringGuidesApplication.class, args);
		context.getBean("sampleService");

	}

}
