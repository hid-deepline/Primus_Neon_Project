package fr.doranco.primus_neon.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

	@Test
	public void testEncodePassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "Kss693300800507!";
		String encodedPassord = passwordEncoder.encode(rawPassword);

		System.out.println(encodedPassord);

		boolean matches = passwordEncoder.matches(rawPassword, encodedPassord);
		assertThat(matches).isTrue();
	}
}
