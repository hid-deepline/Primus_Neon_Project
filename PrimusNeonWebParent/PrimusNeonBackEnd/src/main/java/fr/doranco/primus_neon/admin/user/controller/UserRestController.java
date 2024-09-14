package fr.doranco.primus_neon.admin.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.doranco.primus_neon.admin.user.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService service;

	@PostMapping("/users/check_email")
	public String checkDuplicateEmail(Integer id, String email) {

		return service.isEmailUnique(id, email) ? "OK" : "Dupliqué";

	}
}
