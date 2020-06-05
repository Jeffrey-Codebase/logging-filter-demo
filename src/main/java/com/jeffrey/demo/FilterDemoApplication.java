package com.jeffrey.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FilterDemoApplication {

	@Autowired
	private HttpLogFilter httpLogFilter;

	public static void main(String[] args) {
		SpringApplication.run(FilterDemoApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<HttpLogFilter> addLogFilter() {
		FilterRegistrationBean<HttpLogFilter> registrationBean = new FilterRegistrationBean<>();
		httpLogFilter.setBeforeMessagePrefix("[CLIENT REQUEST] ");
		httpLogFilter.setAfterMessagePrefix("[SERVICE RESPONSE] ");
		registrationBean.setFilter(httpLogFilter);
		registrationBean.setOrder(SecurityProperties.DEFAULT_FILTER_ORDER-1);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

}
