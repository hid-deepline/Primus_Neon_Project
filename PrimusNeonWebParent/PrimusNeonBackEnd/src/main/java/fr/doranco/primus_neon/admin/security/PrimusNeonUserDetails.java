package fr.doranco.primus_neon.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.doranco.primus_neon.common.entity.Role;
import fr.doranco.primus_neon.common.entity.User;

public class PrimusNeonUserDetails implements UserDetails {

	private User user;
	
	public PrimusNeonUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		
		
		List<GrantedAuthority> authories = new ArrayList<>();
		
		for (Role role : roles) {
			authories.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		
		return authories;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
	
	public String getFullName() {
		return user.getFirstName() + " " + user.getLastName();
	}
	
	public void setFirstName(String firstName) {
		this.user.setFirstName(firstName);
	}

	public void setLastName(String lastName) {
		this.user.setLastName(lastName);
	}

}
