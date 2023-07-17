package com.sprta.deliveryproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing      //Timestamped 시간을 체크해주는 기능을 사용하기위한 애너테이션
public class DeliveryProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryProjectApplication.class, args);
    }

}
