package com.qui.shopping.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;


@SpringBootApplication
@EnableSidecar
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  SpringApplication.run(Application.class, args);
	}

}
