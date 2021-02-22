package com.ragnax.ragnaxoauthusuario.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ragnax.ragnaxutilidades.servicio.ZapalonImpl;


@Configuration
public class ServiceBeanConf {
	
	@Bean
    public ZapalonImpl obtenerZapalonImpl() {
        return new ZapalonImpl();
    }
}
