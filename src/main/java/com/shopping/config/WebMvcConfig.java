package com.shopping.config;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = "com.shopping.*")
@PropertySource(value = { "classpath:data-jpa.properties" })
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;

	// Return View in Controller
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	// Add static resources
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// Css,js, img for SSR
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");

		// Swagger
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

		// Image
		registry.addResourceHandler("/images/**")
				.addResourceLocations(env.getProperty("spring.resources.static-locations"));
	}

	// Model mapper
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

	// Azure Blob
	@Bean
	public CloudBlobContainer cloudBlobContainer() throws InvalidKeyException, URISyntaxException, StorageException {
		CloudStorageAccount storageAccount = CloudStorageAccount
				.parse(env.getProperty("azure.storage.ConnectionString"));
		CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
		CloudBlobContainer container = blobClient
				.getContainerReference(env.getProperty("azure.storage.container.name"));
		return container;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		int IMAGE_MAX_UPLOAD_SIZE = 10000000;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(IMAGE_MAX_UPLOAD_SIZE);
		return multipartResolver;
	}

}
