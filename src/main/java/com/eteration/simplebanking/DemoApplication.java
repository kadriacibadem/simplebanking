package com.eteration.simplebanking;

import com.eteration.simplebanking.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return runner -> {
			test();
		};
	}

	private void test() throws InsufficientBalanceException {
		Account account = new Account("John Doe", "1000");
		account.post(new DepositTransaction(1000));
		account.post(new PhoneBillPaymentTransaction("Turkcell", "5321234567", 100));
		System.out.println(account);
	}



}
