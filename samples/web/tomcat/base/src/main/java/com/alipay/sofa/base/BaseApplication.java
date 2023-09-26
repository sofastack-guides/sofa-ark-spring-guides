package com.alipay.sofa.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource({ "classpath*:META-INF/spring/service.xml"})
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
public class BaseApplication {
	private static Logger logger = LoggerFactory.getLogger(BaseApplication.class);

	public static void main(String[] args) {
		// 设置内嵌方式启动多模块运行容器, 也可以放在启动参数中
		System.setProperty("sofa.ark.embed.enable", "true");
		System.setProperty("sofa.ark.plugin.export.class.enable", "true");

		ConfigurableApplicationContext context = SpringApplication.run(
				BaseApplication.class, args);
		context.getBean("sampleService");
		logger.info("BaseApplication start!");
		logger.info("Spring Boot Version: " + SpringApplication.class.getPackage().getImplementationVersion());
		logger.info("BaseApplication classLoader: " + BaseApplication.class.getClassLoader());
	}
}
