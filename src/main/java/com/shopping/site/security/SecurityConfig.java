//package com.shopping.security;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
//import org.springframework.security.web.access.channel.ChannelProcessingFilter;
//
//import com.shopping.filter.CORSFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Resource(name = "userService")
//	@Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//           .passwordEncoder(encoder())
//                ;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable()
////               .addFilterBefore(new CORSFilter(), SessionManagementFilter.class)
////                .anonymous().disable()
////                .cors().and()
////                .authorizeRequests()
////                .antMatchers("/oauth/token").permitAll();
//    	http.csrf().disable().addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class).authorizeRequests()
//		.mvcMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
//    	
//		http.authorizeRequests()
//		.antMatchers("manufacturers/**").hasRole("ADMIN")
//		.antMatchers("products/**").hasRole("ADMIN")
//		.and()
//		.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler()).and().anonymous().disable();
//    }
//    
//    @Bean
//    public BCryptPasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }
//    
//    @Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/images/**");
//	}
//   
////    @Bean
////    public CorsFilter corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowCredentials(true);
////        config.addAllowedOrigin("*");
////        config.addAllowedHeader("*");
////        config.addAllowedMethod("*");
////        source.registerCorsConfiguration("/**", config);
////        return new CorsFilter(source);
////    }
//    
//    
//    
//}
