package com.example.SpringSecurityDemo;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class AppConfiguration {

	
	/**
	 * USELESS method
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new CustomPropertyPlaceHolder();
		/*
		 * Resource[] resources = new ClassPathResource[] { new
		 * ClassPathResource("foo.properties") }; pspc.setLocations(resources);
		 * pspc.setIgnoreUnresolvablePlaceholders(true);
		 */
		return pspc;
	}
	
	
	@ConditionalOnProperty()
	public String testConditionalOnProperty() {
		System.out.println("Nothing");
		return null;
		
	}

//	@Bean(name = "encryptorBean")
//	public StringEncryptor stringEncryptor() {
//		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//		
//		// Key should be taken from environment variable
//		//System.getProperty("ENV_ENC_KEY")
//		config.setPassword(ENCRY_KEY);// encryption key
//		config.setAlgorithm(ENCRY_ALGO);
//		//config.setKeyObtentionIterations("1000");
//		//config.setPoolSize("1");
//		//config.setProviderName("SunJCE");
//		//config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
//		//config.setStringOutputType("base64");
//		encryptor.setConfig(config);
//		return encryptor;
//	}

}

class CustomPropertyPlaceHolder extends PropertySourcesPlaceholderConfigurer {

	@Override
	protected String convertPropertyValue(String originalValue) {
		// TODO Auto-generated method stub
		System.out.println("CustomPropertyPlaceHolder --> " + originalValue);
		return super.convertPropertyValue(originalValue);
	}
}
