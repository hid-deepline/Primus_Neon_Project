package fr.doranco.primus_neon.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"fr.doranco.primus_neon.common.entity", "fr.doranco.primus_neon.admin.user"})
public class PrimusNeonBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimusNeonBackEndApplication.class, args);
	}

}
