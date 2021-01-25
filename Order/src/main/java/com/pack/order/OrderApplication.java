package com.pack.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import com.pack.order.config.MyFilter;



@SpringBootApplication
@EnableCaching
@EnableEurekaClient
@EnableFeignClients("com.pack.order")
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        MyFilter myFilter = new MyFilter();
        filterRegistrationBean.setFilter(myFilter);
        return filterRegistrationBean;
    }

}
