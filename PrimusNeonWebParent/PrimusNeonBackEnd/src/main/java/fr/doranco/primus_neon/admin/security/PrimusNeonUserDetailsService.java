package fr.doranco.primus_neon.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.doranco.primus_neon.admin.user.UserRepository;
import fr.doranco.primus_neon.common.entity.User;

public class PrimusNeonUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepo.getUserByEmail(email);
		if (user != null) {
			return new PrimusNeonUserDetails(user);
		}
		
		throw new UsernameNotFoundException("Impossible de trouver l’utilisateur avec l'e-mail: " + email);
	}

}
