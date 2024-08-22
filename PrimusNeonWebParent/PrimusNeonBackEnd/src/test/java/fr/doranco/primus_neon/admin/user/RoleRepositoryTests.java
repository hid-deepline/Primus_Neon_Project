package fr.doranco.primus_neon.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import fr.doranco.primus_neon.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository repo;

	@Test
	public void testCreateFirstRole() {

		Role roleAdmin = new Role("Admin", "gére tout");
		Role savedRole = repo.save(roleAdmin);
		assertThat(savedRole.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateRestRoles() {

		Role roleSalesperson = new Role("Commercial",
				"gére le prix des produits, " + "les clients, les expéditions, les commandes et les rapports de ventes");
		Role roleEditor = new Role("Editeur", "gérer les catégories, " + "produits et menus");
		Role roleShipper = new Role("Expéditeur", "Afficher les produits, Afficher les commandes " + "et mettre à jour l’état de la commande");
		Role roleAssistant = new Role("Assistant", "gérer les questions et les commentaires");

		repo.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
	}
}
