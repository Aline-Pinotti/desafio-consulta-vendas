package com.devsuperior.dsmeta;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DsmetaApplication implements CommandLineRunner {
	
	private static final LocalDate TODAY = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	private static final LocalDate LAST_YEAR = TODAY.minusYears(1L);

	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Today: " + TODAY.toString());
		System.out.println("Last Year: " + LAST_YEAR);

	}
}
