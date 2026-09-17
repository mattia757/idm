package com.nttdata.idmccnobe.configuration;

import com.nttdata.idmccnobe.interceptor.LoginInterceptor;
import com.nttdata.idmccnobe.interceptor.CsrfInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author smartinenghi
 */
@Configuration
public class AppWebMvcConf implements WebMvcConfigurer {
      
    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public CsrfInterceptor getCsrfInterceptor() {
        return new CsrfInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Nota: L'ordine di registrazione degli interceptor implica il loro ordine di esecuzione
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/**","/logout/**","/resources/**");
        registry.addInterceptor(getCsrfInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/**", "/logout/**", "/resources/**");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/");
    }
    
}
