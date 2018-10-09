package com.drelephant.elephantadmin.business.basedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author com.drelephant
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class ElephantadminBusinessBasedataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElephantadminBusinessBasedataApplication.class, args);
	}	
}
