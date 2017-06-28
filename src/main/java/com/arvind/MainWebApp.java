package com.arvind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan("com.arvind")
public class MainWebApp extends WebMvcConfigurerAdapter 
{
    public static void main( String[] args)
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(MainWebApp.class, args);
    }
    @Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}
}
