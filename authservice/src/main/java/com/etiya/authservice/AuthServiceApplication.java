package com.etiya.authservice;

import com.etiya.common.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSecurity
public class AuthServiceApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {

        SpringApplication.run(AuthServiceApplication.class, args);

//        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
//        keyGenerator.init(256);
//        SecretKey secretKey = keyGenerator.generateKey();
//        String base64 = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println(base64);
	}

}
