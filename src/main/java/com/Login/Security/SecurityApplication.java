package com.Login.Security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Key;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {

		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		String base64Key = Encoders.BASE64.encode(key.getEncoded());
		System.out.println("Generated HS256 JWT Secret Key:");
		System.out.println(base64Key);
		SpringApplication.run(SecurityApplication.class, args);

	}


}
