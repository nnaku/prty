/*package fi.thepaardihub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{
	
	   @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
	        registry.addResourceHandler("/public/static/**").addResourceLocations("classpath:/public/static");
	        super.addResourceHandlers(registry);
	    }

	    @Bean
	    public ViewResolver viewResolver() {
	        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
	        viewResolver.setViewClass(InternalResourceView.class);
	        return viewResolver;
	    }
	
	

}*/