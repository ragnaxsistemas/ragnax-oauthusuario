package com.ragnax.ragnaxoauthusuario.configuration;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@EnableCaching
@ComponentScan("com.ragnax.ragnaxoauthusuario.servicio")
public class CachingConfig {

    @Bean
    @Primary
    public CacheManager cacheFeeComision() {
        final SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
        		new ConcurrentMapCache("buscarUsuario")));
        
        return cacheManager;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    @Bean
//    public CacheManager cacheListaFeeComision() {
//        final SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("directorio"), new ConcurrentMapCache("listaTipoFeeComision")));
//        return cacheManager;
//    }

}