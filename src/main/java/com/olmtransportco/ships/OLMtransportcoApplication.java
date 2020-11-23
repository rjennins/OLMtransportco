package com.olmtransportco.ships;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// http://localhost:8080/swagger-ui.html#

@Configuration
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = "com.olmtransportco.ships, com.olmtransportco.ships.json, com.olmtransportco.ships.persistence")
public class OLMtransportcoApplication {

  public static void main(String[] args) {
    SpringApplication.run(OLMtransportcoApplication.class, args);
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointsInfo());
  }

  private ApiInfo apiEndPointsInfo() {
    return new ApiInfoBuilder().title("Spring Boot REST API").description("OLM transportco Ships REST API")
        .contact(new Contact("Production Support", "www.javaguides.net", "prodsupport@gmail.com"))
        .license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0")
        .build();
  }
}