package fr.doranco.primus_neon.admin.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestParam;

import fr.doranco.primus_neon.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@RequestParam(name = "email ", required = true) String email);

	public Long countById(Integer id);
	
	@Query("SELECT u FROM User u WHERE CONCAT(u.id, ' ', u.email, ' ', u.firstName, ' ', u.lastName) LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);

	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled);

}
