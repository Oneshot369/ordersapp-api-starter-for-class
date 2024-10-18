package com.shadsluiter.ordersapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.shadsluiter.ordersapp")
public class OrdersappApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersappApplication.class, args);
	}

}
