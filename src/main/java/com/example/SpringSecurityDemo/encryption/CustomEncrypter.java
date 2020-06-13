package com.example.SpringSecurityDemo.encryption;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConditionalOnProperty(prefix = "encrypt.jasypt", value = "enabled", havingValue = "true", matchIfMissing = true)
//@ConditionalOnClass(StringEncryptor.class)
public class CustomEncrypter {

	private static final String ENCRY_ALGO = "PBEWithMD5AndDES";
	private static final String ENCRY_KEY = "ENCRY_KEY";
	
	
	/**
	 * We are overriding the default bean with new bean name hence the same is same "jasyptStringEncryptor"
	 * if we change the name then we need to configure the property "jasypt.encryptor.bean" with new name
	 * in application.properties
	 * @return
	 */
	@Bean(name = "jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
		System.out.println("HELLO**********************");
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		
		// Key should be taken from environment variable
		//System.getProperty("ENV_ENC_KEY")
		config.setPassword(ENCRY_KEY);// encryption key
		config.setAlgorithm(ENCRY_ALGO);
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}
	
	public static void main(String[] args) {
		CustomEncrypter test = new CustomEncrypter();
		StringEncryptor encry = test.stringEncryptor();
		
		//System.out.println(PropertyValueEncryptionUtils.encrypt(ENCRY_KEY, encry));
		
		//
		System.out.println(PropertyValueEncryptionUtils.encrypt("test_sudhir", encry));
		System.out.println(PropertyValueEncryptionUtils.encrypt("Sara@2013", encry));
		System.out.println(PropertyValueEncryptionUtils.decrypt("ENC(nvOCeIt9gTT9oB2ZKSAxouNZZSjcYtSo)", encry));
		
	}
}
