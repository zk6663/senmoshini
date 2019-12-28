package com.etoak;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@MapperScan(basePackages = {"com.etoak.mapper"})
public class Boot02Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot02Application.class, args);
	}

	/**
	 * SpringBoot注册过滤器
	 * 注解@Bean：相当于一个xml容器中<bean>标签
	 * 方法名默认就是id值
	 * 如何自定义id? @Bean("restFilter")
     *
     * HiddenHttpMethodFilter作用：为了支持表单可以提交put和delete等rest请求，
     *  spring mvc提供下面的过滤器用于提交put、delete请求，
     *  要求：1.form表单必须是post请求，
     *       2.form表单必须有一个隐藏域，name属性值是_method，value属性的值就是rest请求(put\delete)
	 */
	/*@Bean("restFilter")
	public
	FilterRegistrationBean<HiddenHttpMethodFilter> rest() {
		FilterRegistrationBean<HiddenHttpMethodFilter> restFilter
        = new FilterRegistrationBean<>();
		// filter-class
		restFilter.setFilter(new HiddenHttpMethodFilter());

		// url-pattern
		restFilter.addUrlPatterns("*//*");
		return restFilter;
	}*/



}
