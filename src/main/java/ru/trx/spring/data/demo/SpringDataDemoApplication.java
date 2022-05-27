package ru.trx.spring.data.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Alexander Vasiliev
 */
@SpringBootApplication(scanBasePackages = "ru.trx")
@EnableConfigurationProperties(value = SpringDataDemoProperties.class)
public class SpringDataDemoApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringDataDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringDataDemoApplication.class, args);
    }

}
