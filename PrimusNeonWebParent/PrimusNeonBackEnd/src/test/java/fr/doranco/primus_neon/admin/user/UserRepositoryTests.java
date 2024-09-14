package fr.doranco.primus_neon.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import fr.doranco.primus_neon.common.entity.Role;
import fr.doranco.primus_neon.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateNewUserWithOneRole() {

		Role roleAdmin = entityManager.find(Role.class, 1);
		User userArabeHid = new User("Arabe", "Hidouche", "hkass@hotmail.fr", "Kss693300800507!", true);
		userArabeHid.addRole(roleAdmin);

		User savedUser = repo.save(userArabeHid);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateNewUserWithTwoRoles() {

		User userKassi = new User("Kass", "Hido", "hkass21@gmil.com", "93300Kss6!123456789", false);
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);

		userKassi.addRole(roleEditor);
		userKassi.addRole(roleAssistant);

		User savedUser = repo.save(userKassi);
		assertThat(savedUser.getId()).isGreaterThan(0);

	}

	@Test
	public void testListAllUsers() {

		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}

	@Test
	public void testGetUserById() {

		User userArabe = repo.findById(1).get();
		System.out.println(userArabe);
		assertThat(userArabe).isNotNull();
	}

	@Test
	public void testUpdateUserDetails() {

		User userArabe = repo.findById(1).get();
		userArabe.setEnabled(true);
		userArabe.setEmail("hkass@hotmail.fr");

		repo.save(userArabe);
	}

	@Test
	public void testUpdateUserRoles() {

		User userKassi = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);

		userKassi.getRoles().remove(roleEditor);
		userKassi.addRole(roleSalesperson);

		repo.save(userKassi);

	}

	@Test
	public void testDeleteUser() {

		Integer userId = 32;
		repo.deleteById(userId);
	}

	@Test
	public void testGetUserByEmail() {

		String email = "klpomloi.com";
		User user = repo.getUserByEmail(email);

		assertThat(user).isNotNull();

	}

	@Test
	public void testCountById() {

		Integer id = 100;
		Long countById = repo.countById(id);

		assertThat(countById).isNotNull().isGreaterThan(0);
	}

	@Test
	public void testDisableUser() {

		Integer id = 2;
		repo.updateEnabledStatus(id, false);

	}

	@Test
	public void testEnableUser() {

		Integer id = 1;
		repo.updateEnabledStatus(id, true);

	}

	@Test
	public void testListFirstPage() {

		int pageNumber = 0;
		int pageSize = 4;

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(pageable);

		List<User> listUsers = page.getContent();

		listUsers.forEach(user -> System.out.println(user));
		assertThat(listUsers.size()).isEqualTo(pageSize);
	}

	@Test
	public void testSearchUsers() {

		String keyword = "Gerson";

		int pageNumber = 0;
		int pageSize = 4;

		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(keyword, pageable);

		List<User> listUsers = page.getContent();

		listUsers.forEach(user -> System.out.println(user));
		assertThat(listUsers.size()).isGreaterThan(0);
	}
}
