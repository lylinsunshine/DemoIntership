//package com.shopping.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//	
//	private static final String CLIEN_ID = "hello";
//	private static final String CLIENT_SECRET = "fDw7Mpkk5czHNuSRtmhGmAGL42CaxQB9";
//	private static final int THIRTY_MINUTES = 30 * 60;
//	private static final int ONE_WEEK = 7 * 24 * 60 * 60;
//	
//	//http://www.zakariaamine.com/2018-03-01/using-oauth2-in-spring-scopes
//	//http://www.zakariaamine.com/2018-01-27/using-oauth2-in-spring
//	//https://developer.okta.com/blog/2019/03/12/oauth2-spring-security-guide
//	//https://howtodoinjava.com/spring-boot2/oauth2-auth-server/
//	//https://www.devglan.com/spring-security/spring-boot-security-oauth2-example
//	//https://www.baeldung.com/rest-api-spring-oauth2-angular
//	//https://www.tutorialspoint.com/spring_boot/spring_boot_oauth2_with_jwt.htm
//	@Autowired
//    @Qualifier("authenticationManagerBean")
//    private AuthenticationManager authenticationManager;
//	
//	
//	//https://www.baeldung.com/spring-security-oauth-jwt#authServer
//	@Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//	
//	@Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("123");
//        return converter;
//    }
//	
//	@Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//            .withClient(CLIEN_ID)
//            .secret(CLIENT_SECRET)
//            .authorizedGrantTypes("password", "refresh_token")
//            .scopes("read", "write", "trust")
//			.accessTokenValiditySeconds(THIRTY_MINUTES)
//			.refreshTokenValiditySeconds(ONE_WEEK);
//    }
//	
//	 @Override
//	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
//	      .accessTokenConverter(accessTokenConverter());
//	   }
//	
//	
//}
