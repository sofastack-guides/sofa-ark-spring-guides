package com.alipay.sofa.sofaarkspringguides;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource({ "classpath*:META-INF/spring/service.xml" })
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SofaArkSpringGuidesApplication {

	public static final Logger logger = LoggerFactory.getLogger(SofaArkSpringGuidesApplication.class);

	public static void main(String[] args) {

		logger.info("xxxx");

		ConfigurableApplicationContext context = SpringApplication.run(SofaArkSpringGuidesApplication.class, args);
//		SampleService sampleService = (SampleService) context.getBean("sampleService");
//		sampleService.service();

//		SampleController sampleController = context.getBean(SampleController.class);
//		sampleController.hello();

		System.out.println("SofaArkSpringGuidesApplication start!");
		System.out.println("Spring Boot Version: " + SpringApplication.class.getPackage().getImplementationVersion());
		System.out.println("SofaArkSpringGuidesApplication classLoader: " + SofaArkSpringGuidesApplication.class.getClassLoader());

	}

}
