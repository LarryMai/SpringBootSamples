package com.larry.dog_service.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionHandlerInterceptor()).addPathPatterns("/user/**");
	}
	public void addCorsMappings(CorsRegistry registry) {
	}
	
	public void addFormatters(FormatterRegistry registry) {
		 registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
		
	}


	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index.html").setViewName("/index.btl");
		registry.addRedirectViewController("/**/*.do", "/index.html");
	}
	
	/**
	 * 
	 * 检查用户是否已经登录，如果未登录，重定向到登录页面
	 *	
	 */
	class SessionHandlerInterceptor implements HandlerInterceptor{
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {

			return true;
		}
	}
	
	

}
