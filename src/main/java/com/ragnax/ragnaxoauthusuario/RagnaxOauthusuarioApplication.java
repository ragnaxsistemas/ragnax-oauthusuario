package com.ragnax.ragnaxoauthusuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



//@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.ragnax.domain.oauthusuario.entidad", "com.ragnax.domain.response.error"})
public class RagnaxOauthusuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(RagnaxOauthusuarioApplication.class, args);
	}

}