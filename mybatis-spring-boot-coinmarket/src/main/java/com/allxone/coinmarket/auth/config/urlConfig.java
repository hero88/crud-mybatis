package com.allxone.coinmarket.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/*.env.example")
public class urlConfig {
	
	@Value("${BASE_URL}")
    private String apiUrl;
	
	@Value("${BASE_PORT}")
    private String port;
	
	@Value("${SPRING_DATASOURCE_URL}")
    private String springDatasourceURL;

	
	@Value("${SPRING_DATASOURCE_USERNAME}")
    private String springDatasourceUsername;

	
	@Value("${SPRING_DATASOURCE_PASSWORD}")
    private String springDatasourcePassword;
	
	@Value("${SPRING_DATASOURCE_DRIVER}")
    private String springDatasourceDriver;

    public String getApiUrl() {
        return apiUrl;
    }
    
}
