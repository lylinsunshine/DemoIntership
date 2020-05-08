//package com.shopping.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
//import org.springframework.security.web.access.channel.ChannelProcessingFilter;
//
//import com.shopping.filter.CORSFilter;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//	// https://www.devglan.com/spring-security/spring-boot-security-oauth2-example
//	private static final String RESOURCE_ID = "resource_id";
//
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resources) {
//		resources.resourceId(RESOURCE_ID).stateless(false);
//	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
////		http
////			.anonymous()
////			.disable()
////			.authorizeRequests()
////			.antMatchers("/**").access("hasRole('ADMIN')").and()
////			.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
//		http.csrf().disable().addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class).authorizeRequests()
//		.mvcMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
//		http.authorizeRequests()
//		.antMatchers("manufacturers/**").hasRole("ADMIN")
//		.antMatchers("products/**").hasRole("ADMIN")
//		.and()
//		.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler()).and().anonymous().disable();
//	}
//}
