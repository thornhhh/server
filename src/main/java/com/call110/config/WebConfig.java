package com.call110.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class WebConfig {
	private final static Log log = LogFactory.getLog(WebConfig.class);
/*	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		log.info("+++++++++++++++ShiroFilterProxy++++++++++++++++");
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter")); //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理  
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("*//*");
        filterRegistration.setOrder(1);
        return filterRegistration;
    }*/
	@Bean
	public FilterRegistrationBean encodingFilter() {
		log.info("+++++++++++++++EncodingFilter++++++++++++++++");
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setBeanName("encoding");
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);// request 和 response 设置字符集
		FilterRegistrationBean mappingEncodingFilter = new FilterRegistrationBean(encodingFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");
		mappingEncodingFilter.setUrlPatterns(urlPatterns);
//		mappingEncodingFilter.setOrder(2);
		return mappingEncodingFilter;
	}
	
	@Bean
	public FilterRegistrationBean webStatFilter() {
		log.info("+++++++++++++++WebStatFilter++++++++++++++++");
		// 监控
		WebStatFilter webStatFilter = new WebStatFilter();
		// webStatFilter.setProfileEnable(profileEnable);
		// webStatFilter.setSessionStatEnable(true);//session统计
		// webStatFilter.setWebAppStat(webAppStat);
		FilterRegistrationBean mappingDruid = new FilterRegistrationBean(webStatFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");
		mappingDruid.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,*.jsp,/druid/*");
		mappingDruid.setUrlPatterns(urlPatterns);
//		mappingDruid.setOrder(3);
		return mappingDruid;
	}
	
	@Bean
	public ServletRegistrationBean mappingStatViewServlet() {
		log.info("+++++++++++++++StatViewServlet++++++++++++++++");
		StatViewServlet statViewServlet = new StatViewServlet();
		ServletRegistrationBean mappingStatViewServlet = new ServletRegistrationBean(
				statViewServlet, "/druid/*");
		return mappingStatViewServlet;
	}
	/** 
	 * @Title: localeResolver 
	 * @Description: 解析用户区域
	 * @return: CookieLocaleResolver
	 */
	@Bean(name="localeResolver")
	public CookieLocaleResolver localeResolver(){
		log.info("+++++++++++++++CookieLocaleResolver++++++++++++++++");
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("locale");
		resolver.setCookieMaxAge(31536000);//Cookie持续一年
		return resolver;
	}

	/** 
	 * @Title: messageSource 
	 * @Description: 国际化消息处理
	 * @return: ReloadableResourceBundleMessageSource
	 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		log.info("+++++++++++++++MessageSource++++++++++++++++");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}
	
	/** 
     * @Title: localeChangeInterceptor 
     * @Description: 本地化拦截器
     * @return: LocaleChangeInterceptor
     */
    @Bean  
    public LocaleChangeInterceptor localeChangeInterceptor(){
    	log.info("+++++++++++++++LocaleChangeInterceptor++++++++++++++++");
        return new LocaleChangeInterceptor();  
    }  
	
    /**
     * @Title: addInterceptors
     * @Description: 添加拦截器
     * @param registry 
     */
    
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("+++++++++++++++addInterceptors++++++++++++++++");
    	registry.addInterceptor(localeChangeInterceptor());
	}
	
}
