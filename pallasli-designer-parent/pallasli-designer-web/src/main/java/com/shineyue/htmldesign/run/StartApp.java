package com.shineyue.htmldesign.run;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.shineyue.htmldesign.contoller.CommonController;
import com.shineyue.htmldesign.contoller.ComponentController;
import com.shineyue.htmldesign.contoller.HtmlController;
import com.shineyue.htmldesign.contoller.MainController;
import com.shineyue.htmldesign.contoller.MenuController;
import com.shineyue.htmldesign.contoller.PageComponentController;
import com.shineyue.htmldesign.contoller.SimpleController;
import com.shineyue.htmldesign.html.Builder;
import com.shineyue.htmldesign.page.contoller.PageRouteController;

//@SpringApplicationConfiguration(classes = StartApp.class)

//@EnableWebMvc
@Configuration
@EnableAutoConfiguration
@EnableScheduling
// @ComponentScan
public class StartApp extends WebMvcConfigurerAdapter {

	// dataSource这里使用的是Hikari,你也可以使用其他的
	// @Bean
	// public DataSource dataSource() {
	// HikariConfig config = new
	// HikariConfig(getClass().getClassLoader().getResource("db.properties").getPath());
	// return new HikariDataSource(config);
	// }

	// 用于处理编码问题
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Bean
	public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		// Add supported media type returned by BI API.
		List supportedMediaTypes = new ArrayList();
		supportedMediaTypes.add(new MediaType("text", "plain"));
		supportedMediaTypes.add(new MediaType("application", "json"));
		jsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		return jsonMessageConverter;
	}

	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new TimeBasedAccessInterceptor())
		// .addPathPatterns("/common/**");
	}

	public static void main(String[] args) {
		SpringApplication.run(new Object[] { StartApp.class,
				MainController.class, SimpleController.class,
				MenuController.class, PageComponentController.class,
				ComponentController.class, PageRouteController.class,
				CommonController.class, HtmlController.class }, args);
		new Builder();
	}
}

class ApplicationContext extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(List converters) {
		converters.add(converter());
	}

	@Bean
	MappingJackson2HttpMessageConverter converter() {
		// Set HTTP Message converter using a JSON implementation.
		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		// Add supported media type returned by BI API.
		List supportedMediaTypes = new ArrayList();
		supportedMediaTypes.add(new MediaType("text", "plain"));
		supportedMediaTypes.add(new MediaType("application", "json"));
		jsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		return jsonMessageConverter;
	}
}
