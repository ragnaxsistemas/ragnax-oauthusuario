package com.ragnax.ragnaxoauthusuario.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

/**
 * Created by pechofrio on 13-08-19.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/*** http://localhost:8088/swagger-ui.html# *****/
	private static final String PACKAGE_CONTROLLER = "com.ragnax.ragnaxoauthusuario.controller";
	
	@Value("${configuracion.swagger.contacto.empresa}")
	private String contactoEmpresa;
	
	@Value("${configuracion.swagger.contacto.mail}")
	private String contactoMail;
	
	@Value("${configuracion.swagger.apiinfo.titulo}")
	private String apiinfoTitulo;
	
	@Value("${configuracion.swagger.apiinfo.descripcion}")
	private String apiinfoDescripcion;
	
	@Value("${configuracion.swagger.apiinfo.version}")
	private String apiinfoVersion;
	
	@Value("${configuracion.swagger.apiinfo.termsOfServiceUrl}")
	private String apiinfoTermsOfServiceUrl;
	
	@Value("${configuracion.swagger.apiinfo.license}")
	private String apiinfoLicense;
	
	@Value("${configuracion.swagger.apiinfo.licenseUrl}")
	private String apiinfoLicenseUrl;
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.POST, new ArrayList<>())
                .globalResponseMessage(RequestMethod.GET, new ArrayList<>())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_CONTROLLER))
                .paths(PathSelectors.any())
                .build();
    }
	
	private ApiInfo apiInfo() {
        Contact contacto = new Contact(contactoEmpresa, apiinfoLicenseUrl,contactoMail);
        
        return new ApiInfo(apiinfoTitulo, apiinfoDescripcion,
                apiinfoVersion, apiinfoTermsOfServiceUrl, contacto, apiinfoLicense,
                apiinfoLicenseUrl, new ArrayList<VendorExtension>());
    }

}