package com.line.store.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CryptPassword {

	public static void main(String[] args) {
		String password = "123456";

		System.out.println("password: " + password);
		System.out.println("password encriptado: " + codificar(password));

	}
	
	public static String codificar(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	public String encode(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

}
